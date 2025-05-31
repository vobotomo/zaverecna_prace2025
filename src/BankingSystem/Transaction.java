package BankingSystem;

import Account.Account;

import java.time.LocalDateTime;

/**
 * Represents a transaction between two accounts.
 * A transaction has a date, amount, description, sender and receiver accounts, type, and a unique ID.
 */
public class Transaction {

    // Counter to generate unique transaction IDs
    private static int idCounter = 0;

    //Used LocalDateTime instead of LocalDate, beacuse it measures not only years, months and days
    private LocalDateTime date;
    private double amount;
    private String description;
    private int id;
    private Account fromAccount;
    private Account toAccount;
    private TransactionType transactionType;

    /**
     * Creates a new Transaction with the given details.
     * The transaction ID is automatically generated.
     *
     * @param date            the date and time of the transaction
     * @param amount          the amount of money in the transaction
     * @param description     a short text about the transaction (optional)
     * @param fromAccount     the account sending the money (can be null)
     * @param toAccount       the account receiving the money (can be null)
     * @param transactionType the type of the transaction (e.g. DEPOSIT, TRANSFER)
     */
    public Transaction(LocalDateTime date, double amount, String description, Account fromAccount, Account toAccount, TransactionType transactionType) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.id = idCounter++;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transactionType = transactionType;
    }

    /**
     * Returns a string with information about the transaction.
     *
     * @return formatted string with ID, date, type, amount, sender, receiver, and description
     */
    @Override
    public String toString() {
        return "Transaction ID: " + id +
                " | Date: " + date +
                " | Type: " + transactionType +
                " | Amount: " + amount +
                " | From: " + (fromAccount != null ? fromAccount.getUsername() : "N/A") +
                " | To: " + (toAccount != null ? toAccount.getUsername() : "N/A") +
                " | Description: " + (description != null ? description : "None");
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