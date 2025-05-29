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
        database.saveAccounts();
        System.out.println(loginManager.logout());
        loginManager.startLogin(database);
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
