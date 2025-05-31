package Commands;

/**
 * The Command interface is used for defining commands in the application.
 * Each command should be able to execute an action and say if the program should exit.
 */
public interface Command {

    /**
     * Executes the command and returns a result as a message.
     *
     * @return a message describing the result of the command
     */
    public String execute();

    /**
     * Checks if the command should cause the program to exit.
     *
     * @return true if the program should exit after this command, false otherwise
     */
    public boolean exit();

}
