package BankingSystem;

import Account.Account;

import java.time.LocalDateTime;

public class Transaction {
    private static int idCounter = 0;

    //Used LocalDateTime instead of LocalDate, beacuse it measures not only years, months and days
    private LocalDateTime date;
    private double amount;
    private String description;
    private int id;
    private Account fromAccount;
    private Account toAccount;
    private TransactionType transactionType;

    public Transaction(LocalDateTime date, double amount, String description, Account fromAccount, Account toAccount, TransactionType transactionType) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.id = idCounter++;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transactionType = transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Transaction.idCounter = idCounter;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }
}
