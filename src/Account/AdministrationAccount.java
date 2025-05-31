package Account;

import BankingSystem.Database;
import BankingSystem.Transaction;

import java.util.ArrayList;

/**
 * Represents a special type of account used by administrators.
 * This account type has access to a database.
 */
public class AdministrationAccount extends Account {

    private Database database;

    /**
     * Creates an AdministrationAccount with all properties specified.
     *
     * @param ownerName      the first name of the account owner
     * @param ownerSurname   the surname of the account owner
     * @param username       the account's username
     * @param email          the account owner's email address
     * @param phoneNumber    the account owner's phone number
     * @param password       the account's password
     * @param balance        the initial balance of the account
     * @param accountType    the type of the account (should be ADMINISTRATION)
     * @param interestRate   the interest rate (not used for this account type)
     * @param database       the database associated with this account
     */
    public AdministrationAccount(String ownerName, String ownerSurname, String username, String email, String phoneNumber, String password, double balance, AccountType accountType, double interestRate, Database database) {
        super(ownerName, ownerSurname, username, email, phoneNumber, password, balance, accountType, interestRate);
        this.database = database;
    }


    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * Overrides interest application. Administration accounts do not have interest.
     */
    @Override
    public void applyInterest() {
    }
}

