package BankingSystem;

import Commands.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Console class is the main interface for users to work with the banking system.
 * It allows users to log in and use different commands like transfer, withdraw, or deposit.
 */
public class Console {

    private Scanner sc = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> commands;

    /**
     * Initializes all available commands and connects them with the system.
     *
     * @param loginManager the manager that handles login and logout
     * @param database the database that stores account and transaction data
     */
    public void initialization(LoginManager loginManager, Database database) {
        commands = new HashMap<>();
        commands.put("exit", new Exit(database));
        commands.put("logout", new Logout(loginManager, database));
        commands.put("transfer", new Transfer(loginManager, database, sc));
        commands.put("withdraw", new Withdraw(sc, loginManager));
        commands.put("deposit", new Deposit(loginManager, sc));
        commands.put("interest", new ApplyInterest(loginManager, database));
        commands.put("database", new ShowDatabase(database, loginManager, sc));
        commands.put("info", new ShowInformation(loginManager));
        commands.put("help", new Help(database));
        commands.put("delete", new Delete(database, loginManager));
    }

    /**
     * Reads user input and runs the matching command if it exists.
     * If the command is not valid, it shows an error message.
     */
    public void executeCommand() {
        System.out.println(">>");
        String command = sc.nextLine();
        if (commands.containsKey(command)) {
            System.out.println(commands.get(command).execute());
            exit = commands.get(command).exit();
        } else {
            System.out.println("Invalid command");
        }
    }

    /**
     * Starts the console program.
     * Users must log in before they can use commands.
     * The loop runs until the user uses the "exit" command.
     */
    public void start() {
        Database database = new Database();
        LoginManager loginManager = new LoginManager();

        while (!loginManager.startLogin(database)) {
            System.out.println("Try again.");
        }

        initialization(loginManager, database);

        do {
            if (loginManager.getLoggedInAccount() == null) {
                while (!loginManager.startLogin(database)) {
                    System.out.println("Try again.");
                }
            }
            executeCommand();
        } while (!exit);
    }
}