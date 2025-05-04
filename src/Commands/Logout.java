package Commands;

import Account.Account;
import BankingSystem.LoginManager;

public class Logout implements Command {

    LoginManager loginManager;


    public Logout(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public String execute() {
        return loginManager.logout();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
