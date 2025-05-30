package Commands;

import BankingSystem.Database;

public class Help implements Command {

    private Database database;

    public Help(Database database) {
        this.database = database;
    }

    @Override
    public String execute() {
        return database.loadCommands();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
