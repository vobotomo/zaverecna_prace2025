package Commands;


import Account.Account;
import Account.LoginManager;
import BankingSystem.Transaction;
import BankingSystem.Database;

import java.util.Scanner;

public class Transfer implements Command {

    private LoginManager loginManager;
    private Scanner scanner;
    private Database database;

    public Transfer(LoginManager loginManager, Database database , Scanner scanner) {
        this.loginManager = loginManager;
        this.scanner = scanner;
        this.database = database;
    }

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

    @Override
    public boolean exit() {
        return false;
    }
}
