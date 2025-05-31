package Commands;

import BankingSystem.Database;
import BankingSystem.LoginManager;

/**
 * This command deletes the currently logged-in user's account.
 */
public class Delete implements Command {

    private Database database;
    private LoginManager loginManager;

    /**
     * Creates a new Delete command.
     *
     * @param database      the database that stores user accounts
     * @param loginManager  the login manager that has the logged-in account
     */
    public Delete(Database database, LoginManager loginManager) {
        this.database = database;
        this.loginManager = loginManager;
    }

    /**
     * Deletes the account of the currently logged-in user.
     *
     * @return a message saying if the account was deleted or if there was an error
     */
    @Override
    public String execute() {
        return database.removeAccount(loginManager.getLoggedInAccount(), loginManager);
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