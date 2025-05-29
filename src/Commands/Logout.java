package Commands;

import Account.Account;
import BankingSystem.LoginManager;
import BankingSystem.Database;

public class Logout implements Command {

    LoginManager loginManager;
    Database database;


    public Logout(LoginManager loginManager, Database database) {
        this.loginManager = loginManager;
        this.database = database;
    }

    @Override
    public String execute() {
        loginManager.logout();
        return loginManager.startLogin(database);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
