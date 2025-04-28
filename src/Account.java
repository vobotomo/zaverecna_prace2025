import java.util.ArrayList;

public class Account {

    private String accountNumber;
    private String ownerName;
    private double balance;
    private AccountType accountType;
    private ArrayList<Transaction> transactions;
    private double interestRate;


    // method for aplying interests on differnt types of accounts
    public void applyInterest(){

    }

    public Account(String accountNumber, String ownerName, double balance, AccountType accountType, double interestRate) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.accountType = accountType;
        this.interestRate = interestRate;
        transactions = new ArrayList<>();

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
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
}
