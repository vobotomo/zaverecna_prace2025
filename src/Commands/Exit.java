package Commands;

import BankingSystem.Database;

public class Exit implements Command {

    private Database database;

    public Exit(Database database) {
        this.database = database;
    }

    @Override
    public String execute() {
        database.saveAccounts();
        return "The program has been closed.";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
