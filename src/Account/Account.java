package Account;

import BankingSystem.Transaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

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


    // method for aplying interests on differnt types of accounts
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

    public String withdraw(double amount){
        setBalance(getBalance()-amount);
        return "Withdrawal successful. New balance: " + getBalance();
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

    // Převod peněz
    this.balance -= amount;
    recipient.balance += amount;

    // Přidání transakcí do historie obou účtů
    Transaction transaction = new Transaction(LocalDateTime.now() ,amount, description,loginManager, recipient);
    this.transactions.add(transaction);
    recipient.transactions.add(transaction);

    return "Transfer of " + amount + " to " + recipient.getUsername() + " was successful. New balance: " + this.balance + "Description: " + description;
    }

    // dodelat!!!
    @Override
    public String toString() {
        return "\n--- Account Information ---" +
            "\nID: " + id +
            "\nName: " + ownerName + " " + ownerSurname +
            "\nUsername: " + username +
            "\nEmail: " + email +
            "\nPhone: " + phoneNumber +
            "\nBalance: " + balance +
            "\nAccount Type: " + accountType +
            "\nInterest Rate: " + interestRate +
            "\nTransactions: " + transactions.size() + " total" +
            "\n---------------------------";
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

    public boolean setEmail(String email) {
        if (email.matches("^[^@\\s]+@[^@\\s]+\\.[a-z]{2,}$")) {
            this.email = email;
            return true;
        }
        return false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^(\\+420)?\\d{9}$")) {
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            this.password = password;
            return true;
        }
        return false;
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

    public boolean setOwnerSurname(String ownerSurname) {
        if (ownerSurname.matches("^[A-Z][a-z]+$")) {
            this.ownerSurname = ownerSurname;
            return true;
        }
        return false;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean setOwnerName(String ownerName) {
        if (ownerName.matches("^[A-Z][a-z]+$")) {
            this.ownerName = ownerName;
            return true;
        }
        return false;
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
