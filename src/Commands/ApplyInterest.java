package Commands;

import Account.Account;
import Account.AccountType;
import BankingSystem.Database;
import BankingSystem.LoginManager;

/**
 * This command applies interest to all accounts in the database.
 * Only administrators are allowed to do this.
 */
public class ApplyInterest implements Command {

    private LoginManager loginManager;
    private Database database;

    /**
     * Creates a new ApplyInterest command.
     *
     * @param loginManager the login manager that holds the currently logged-in account
     * @param database     the database that contains all accounts
     */
    public ApplyInterest(LoginManager loginManager, Database database) {
        this.loginManager = loginManager;
        this.database = database;
    }

    /**
     * Applies interest to all accounts in the database.
     * The user must be logged in and have ADMINISTRATION rights.
     *
     * @return a message saying whether the interest was applied or why it failed
     */
    @Override
    public String execute() {
        Account loggedIn = loginManager.getLoggedInAccount();

        if (loggedIn == null) {
            return "You must be logged in to apply interest.";
        }

        if (loggedIn.getAccountType() != AccountType.ADMINISTRATION) {
            return "Access denied: Only administrators can apply interest.";
        }

        int count = 0;
        for (Account acc : database.getAccounts()) {
            acc.applyInterest();
            count++;
        }

        return "Interest successfully applied to " + count + " accounts.";
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