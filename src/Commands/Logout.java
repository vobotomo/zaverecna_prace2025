package Commands;

import Account.Account;
import BankingSystem.LoginManager;

public class Logout implements Command {

    Account account;
    LoginManager loginManager;


    public Logout(Account account, LoginManager loginManager) {
        this.account = account;
        this.loginManager = loginManager;
    }

    @Override
    public void execute() {
        loginManager.logout();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
