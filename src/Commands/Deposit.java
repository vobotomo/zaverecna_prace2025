package Commands;

import Account.Account;
import BankingSystem.LoginManager;

import java.util.Scanner;

/**
 * This command allows the logged-in user to deposit money into their account.
 */
public class Deposit implements Command {

    private LoginManager loginManager;
    private Scanner scanner;

    /**
     * Creates a new Deposit command.
     *
     * @param loginManager the login manager to get the current logged-in account
     * @param scanner      the scanner used to read user input
     */
    public Deposit(LoginManager loginManager, Scanner scanner) {
        this.loginManager = loginManager;
        this.scanner = scanner;
    }

    /**
     * Executes the deposit action.
     * Asks the user to enter an amount, checks if it is valid, and adds it to the balance.
     *
     * @return a message with the result of the deposit
     */
    @Override
    public String execute() {
        Account account = loginManager.getLoggedInAccount();
        if (account == null) {
            return "You must be logged in to deposit money.";
        }

        System.out.print("Enter amount to deposit: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return "Invalid amount.";
        }

        if (amount <= 0) {
            return "Amount must be greater than 0.";
        }

        account.setBalance(account.getBalance() + amount);
        return "Deposit successful. New balance: " + account.getBalance();
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