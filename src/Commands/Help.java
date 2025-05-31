package Commands;

import BankingSystem.Database;

/**
 * This command shows a list of available commands with their descriptions.
 */
public class Help implements Command {

    private Database database;

    /**
     * Creates a new Help command.
     *
     * @param database the database used to load the help message
     */
    public Help(Database database) {
        this.database = database;
    }

    /**
     * Loads and returns the list of available commands from the database.
     *
     * @return a help message with available commands
     */
    @Override
    public String execute() {
        return database.loadCommands();
    }

    /**
     * This command does not cause the program to exit.
     *
     * @return false always
     */
    @Override
    public boolean exit() {
        return false;
    }
}