package Commands;

import BankingSystem.Database;

/**
 * This command saves all data and closes the program.
 */
public class Exit implements Command {

    private Database database;

    /**
     * Creates a new Exit command.
     *
     * @param database the database that will be saved before exit
     */
    public Exit(Database database) {
        this.database = database;
    }

    /**
     * Saves all accounts and transactions to the database.
     *
     * @return a message confirming the program has been closed
     */
    @Override
    public String execute() {
        database.saveAccounts();
        database.saveTransactions();
        return "The program has been closed.";
    }

    /**
     * This command tells the program to exit.
     *
     * @return true always
     */
    @Override
    public boolean exit() {
        return true;
    }
}