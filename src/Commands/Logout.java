package Commands;

import Account.Account;
import BankingSystem.LoginManager;
import BankingSystem.Database;

/**
 * This command logs out the current user and allows them to log in again.
 */
public class Logout implements Command {

    LoginManager loginManager;
    Database database;

    /**
     * Creates a new Logout command.
     *
     * @param loginManager the login manager used to handle login and logout
     * @param database     the database used to save data before logout
     */
    public Logout(LoginManager loginManager, Database database) {
        this.loginManager = loginManager;
        this.database = database;
    }

    /**
     * Logs out the current user, saves account and transaction data,
     * and starts the login process again.
     *
     * @return an empty string
     */
    @Override
    public String execute() {
        database.saveAccounts();
        database.saveTransactions();
        System.out.println(loginManager.logout());
        loginManager.startLogin(database);
        return "";
    }

    /**
     * This command does not cause the program to exit.
     *
     * @return false always
     */
    @Override
    public boolean exit() {
        return false;
    }
}