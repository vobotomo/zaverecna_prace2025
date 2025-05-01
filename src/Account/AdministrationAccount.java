package Account;


import BankingSystem.Database;

public class AdministrationAccount extends Account {


    private Database database;

    public AdministrationAccount(String accountNumber, String ownerName, String email, String phoneNumber, String password, double balance, AccountType accountType, double interestRate, Database database) {
        super(accountNumber, ownerName, email, phoneNumber, password, balance, accountType, interestRate);
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
