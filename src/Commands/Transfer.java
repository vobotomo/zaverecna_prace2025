package Commands;

import Account.Account;
import BankingSystem.Database;
import BankingSystem.LoginManager;

import java.util.Scanner;

/**
 * This command allows a logged-in user to transfer money to another account.
 */
public class Transfer implements Command {

    private LoginManager loginManager;
    private Scanner scanner;
    private Database database;

    /**
     * Creates a new Transfer command.
     *
     * @param loginManager the login manager to get the logged-in account
     * @param database the database to find accounts
     * @param scanner the scanner to read user input
     */
    public Transfer(LoginManager loginManager, Database database, Scanner scanner) {
        this.loginManager = loginManager;
        this.scanner = scanner;
        this.database = database;
    }

    /**
     * Executes the transfer process.
     * It asks the user for recipient username, amount, and description.
     * Then it tries to transfer money from the logged-in user to the recipient.
     *
     * @return a message about success or failure of the transfer
     */
    @Override
    public String execute() {
        Account sender = loginManager.getLoggedInAccount();
        if (sender == null) {
            return "You must be logged in to make a transfer.";
        }

        System.out.print("Enter recipient's username: ");
        String recipientUsername = scanner.nextLine().trim();

        Account recipient = database.accByUsername(recipientUsername);
        if (recipient == null) {
            return "Recipient not found.";
        }

        System.out.print("Enter amount to transfer: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return "Invalid amount.";
        }

        System.out.print("Enter a description for the transaction: ");
        String description = scanner.nextLine().trim();

        return sender.transfer(loginManager, recipient, amount, description);
    }

    /**
     * This command does not exit the program.
     *
     * @return false always
     */
    @Override
    public boolean exit() {
        return false;
    }
}