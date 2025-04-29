package Account;


public class AdministrationAccount extends Account {
    public AdministrationAccount(String accountNumber, String ownerName, double balance, AccountType accountType, double interestRate) {
        super(accountNumber, ownerName, balance, accountType, interestRate);
    }

    private Database database;

    public AdministrationAccount(String accountNumber, String ownerName, double balance, AccountType accountType, double interestRate, Database database) {
        super(accountNumber, ownerName, balance, accountType, interestRate);
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
