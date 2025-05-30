package Commands;

import Account.Account;
import Account.AccountType;
import BankingSystem.Database;
import BankingSystem.LoginManager;

public class ApplyInterest implements Command {

    private LoginManager loginManager;
    private Database database;

    public ApplyInterest(LoginManager loginManager, Database database) {
        this.loginManager = loginManager;
        this.database = database;
    }

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


    @Override
    public boolean exit() {
        return false;
    }
}
