package Account;

import BankingSystem.Transaction;

import java.util.ArrayList;

public class Account implements Comparable<Account> {

    private String accountNumber;
    private String ownerName;
    private String email;
    private String phoneNumber;
    private String password;
    private double balance;
    private AccountType accountType;
    private ArrayList<Transaction> transactions;
    private double interestRate;


    // method for aplying interests on differnt types of accounts
    public void applyInterest(){

    }

    public Account(String accountNumber, String ownerName, String email, String phoneNumber, String password, double balance, AccountType accountType,  double interestRate) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.balance = balance;
        this.accountType = accountType;
        this.interestRate = interestRate;
        transactions = new ArrayList<>();
    }

    // dodelat!!!
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", transactions=" + transactions +
                ", interestRate=" + interestRate +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public int compareTo(Account o) {
        return this.ownerName.compareTo(o.ownerName);
    }
}
