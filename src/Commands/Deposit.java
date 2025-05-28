package Commands;

import Account.Account;
import BankingSystem.LoginManager;
import java.util.Scanner;

public class Deposit implements Command {

    private LoginManager loginManager;
    private Scanner scanner;

    public Deposit(LoginManager loginManager, Scanner scanner) {
        this.loginManager = loginManager;
        this.scanner = scanner;
    }


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

    @Override
    public boolean exit() {
        return false;
    }
}
