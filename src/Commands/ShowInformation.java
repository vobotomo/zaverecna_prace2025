package Commands;

import Account.Account;
import BankingSystem.LoginManager;

/**
 * This command shows information about the currently logged-in user.
 */
public class ShowInformation implements Command {

    private LoginManager loginManager;

    /**
     * Creates a new ShowInformation command.
     *
     * @param loginManager the login manager to get the logged-in account
     */
    public ShowInformation(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    /**
     * Returns information about the logged-in user.
     * If no user is logged in, it returns a message.
     *
     * @return user information as string or a message if not logged in
     */
    @Override
    public String execute() {
        Account account = loginManager.getLoggedInAccount();
        if (account == null) {
            return "You are not logged in.";
        }
        return account.toString();
    }

    /**
     * This command does not exit the program.
     *
     * @return false always
     */
    @Override
    public boolean exit() {
        return false;
    }
}