package Account;

import BankingSystem.Transaction;
import BankingSystem.LoginManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static BankingSystem.TransactionType.TRANSFER;
import static BankingSystem.TransactionType.WITHDRAWAL;

public class Account implements Comparable<Account> {
    private static int idCounter = 0;

    private int id;
    private String ownerName;
    private String ownerSurname;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private double balance;
    private AccountType accountType;
    private ArrayList<Transaction> transactions;
    private double interestRate;


    // method for applying interests on differnt types of accounts
    public void applyInterest() {

    }


    public Account(String ownerName, String ownerSurname, String username, String email, String phoneNumber, String password, AccountType accountType) {
        id = idCounter++;
        setOwnerName(ownerName);
        setOwnerSurname(ownerSurname);
        this.username = username;
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        this.accountType = accountType;
        transactions = new ArrayList<>();
    }

    public Account(String ownerName, String ownerSurname, String username, String email, String phoneNumber, String password, double balance, AccountType accountType, double interestRate) {
        id = idCounter++;
        setOwnerName(ownerName);
        setOwnerSurname(ownerSurname);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        this.balance = balance;
        this.username = username;
        this.accountType = accountType;
        this.interestRate = interestRate;
        transactions = new ArrayList<>();
    }


    public String withdraw(double amount, String description, LoginManager loginManager) {
        if (amount <= 0) {
            return "Withdrawal failed: amount must be greater than 0.";
        }

        if (amount > this.balance) {
            return "Withdrawal failed: insufficient balance.";
        }

        this.balance -= amount;

        Transaction transaction = new Transaction(LocalDateTime.now(), amount, description, loginManager.getLoggedInAccount(), null, WITHDRAWAL);
        this.transactions.add(transaction);

        return "Withdrawal of " + amount + " was successful. New balance: " + this.balance;
    }


    public String transfer(LoginManager loginManager, Account recipient, double amount, String description) {
        if (recipient == null) {
            return "Transfer failed: recipient account not found.";
        }

        if (amount <= 0) {
            return "Transfer failed: amount must be greater than 0.";
        }

        if (amount > this.balance) {
            return "Transfer failed: insufficient balance.";
        }

        this.balance -= amount;
        recipient.balance += amount;

        Transaction transaction = new Transaction(LocalDateTime.now(), amount, description, loginManager.getLoggedInAccount(), recipient, TRANSFER);
        this.transactions.add(transaction);
        recipient.transactions.add(transaction);

        return "Transfer of " + amount + " to " + recipient.getUsername() + " was successful. New balance: " + this.balance + "Description: " + description;
    }

    @Override
    public String toString() {
        String result = "\n--- Account Information ---" +
                "\nID: " + id +
                "\nName: " + ownerName + " " + ownerSurname +
                "\nUsername: " + username +
                "\nEmail: " + email +
                "\nPhone: " + phoneNumber +
                "\nBalance: " + balance +
                "\nAccount Type: " + accountType +
                "\nInterest Rate: " + interestRate +
                "\nTransactions: " + transactions.size() + " total";

        if (!transactions.isEmpty()) {
            result += "\n--- Transaction History ---";
            for (Transaction t : transactions) {
                result += "\n" + t.toString();
            }
        }

        result += "\n---------------------------";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(balance, account.balance) == 0 && Double.compare(interestRate, account.interestRate) == 0 && Objects.equals(ownerName, account.ownerName) && Objects.equals(ownerSurname, account.ownerSurname) && Objects.equals(email, account.email) && Objects.equals(phoneNumber, account.phoneNumber) && Objects.equals(password, account.password) && accountType == account.accountType && Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, ownerSurname, email, phoneNumber, password, balance, accountType, transactions, interestRate);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format. Example: test@example.com");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^(\\+420)?\\d{9}$")) {
            throw new IllegalArgumentException("Invalid phone number. Must be 9 digits, optionally prefixed with +420.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            throw new IllegalArgumentException("Invalid password. Must be at least 8 characters long, contain letters and numbers.");
        }
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        if (ownerSurname == null || !ownerSurname.matches("^[A-Z][a-z]+$")) {
            throw new IllegalArgumentException("Invalid surname format. Must start with a capital letter and contain only lowercase letters.");
        }
        this.ownerSurname = ownerSurname;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || !ownerName.matches("^[A-Z][a-z]+$")) {
            throw new IllegalArgumentException("Invalid owner name format. Must start with capital letter and contain only letters.");
        }
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public int compareTo(Account o) {
        return this.ownerName.compareTo(o.ownerName);
    }
}
