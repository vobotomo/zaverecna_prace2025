package Commands;

import BankingSystem.Database;
import BankingSystem.LoginManager;

public class Delete implements Command {

    private Database database;
    private LoginManager loginManager;

    public Delete(Database database, LoginManager loginManager) {
        this.database = database;
        this.loginManager = loginManager;
    }

    @Override
    public String execute() {
        return database.removeAccount(loginManager.getLoggedInAccount(), loginManager);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
