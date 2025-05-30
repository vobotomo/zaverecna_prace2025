package Commands;

import Account.Account;
import BankingSystem.LoginManager;

public class ShowInformation implements Command {

    private LoginManager loginManager;


    public ShowInformation(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public String execute() {
        Account account = loginManager.getLoggedInAccount();
        if (account == null) {
            return "You are not logged in.";
        }
        return account.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
