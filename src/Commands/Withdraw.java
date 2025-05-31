package Commands;

import java.util.Scanner;
import BankingSystem.LoginManager;

/**
 * This command allows a logged-in user to withdraw money from their account.
 */
public class Withdraw implements Command {

    private LoginManager loginManager;
    private Scanner scanner;

    /**
     * Creates a new Withdraw command.
     *
     * @param scanner the scanner to read user input
     * @param loginManager the login manager to get the logged-in account
     */
    public Withdraw(Scanner scanner, LoginManager loginManager) {
        this.scanner = scanner;
        this.loginManager = loginManager;
    }

    /**
     * Executes the withdrawal process.
     * It asks the user for the amount and a description.
     * Then it tries to withdraw money from the logged-in account.
     *
     * @return a message about success or failure of the withdrawal
     */
    @Override
    public String execute() {
        if (loginManager.getLoggedInAccount() == null) {
            return "You must be logged in to withdraw money.";
        }

        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            if (amount <= 0) {
                return "Amount must be positive.";
            }

            System.out.print("Enter description for the withdrawal: ");
            String description = scanner.nextLine().trim();

            return loginManager.getLoggedInAccount().withdraw(amount, description, loginManager);
        } catch (NumberFormatException e) {
            return "Invalid amount format.";
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
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
