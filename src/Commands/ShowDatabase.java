package Commands;

import BankingSystem.LoginManager;
import Account.Account;
import BankingSystem.Database;
import BankingSystem.Transaction;
import Account.AccountType;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowDatabase implements Command {

    private Scanner scanner;
    private LoginManager loginManager;
    private Database database;

    public ShowDatabase(Database database, LoginManager loginManager, Scanner scanner) {
        this.database = database;
        this.loginManager = loginManager;
        this.scanner = scanner;
    }

  @Override
public String execute() {
    Account admin = loginManager.getLoggedInAccount();
    if (admin == null) {
        return "You must be logged in to view the database.";
    }

    if (admin.getAccountType() != AccountType.ADMINISTRATION) {
        return "Access denied: Only administrators can view the database.";
    }

    StringBuilder output = new StringBuilder();
    boolean running = true;

    while (running) {
        System.out.println("\n--- ADMIN DATABASE MENU ---");
        System.out.println("1. Show all accounts");
        System.out.println("2. Show richest account");
        System.out.println("3. Show poorest account");
        System.out.println("4. Show average balance");
        System.out.println("5. Show accounts with zero balance");
        System.out.println("6. Show count of each account type");
        System.out.println("7. Show all transactions");
        System.out.println("0. Exit to main menu");
        System.out.print("Choose option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                output.setLength(0);
                output.append("--- ALL ACCOUNTS ---\n");
                for (Account acc : database.getAccounts()) {
                    output.append(acc).append("\n\n");
                }
                System.out.println(output);
                break;

            case "2":
                Account richest = database.richestAcc();
                System.out.println("\n--- RICHEST ACCOUNT ---");
                System.out.println(richest);
                break;

            case "3":
                Account poorest = database.poorestAcc();
                System.out.println("\n--- POOREST ACCOUNT ---");
                System.out.println(poorest);
                break;

            case "4":
                double avg = database.averageBalance();
                System.out.println("\nAverage balance: " + avg + " CZK\n");
                break;

            case "5":
                ArrayList<Account> zeroBalance = database.accountsWithZeroBalance();
                if (zeroBalance.isEmpty()) {
                    System.out.println("\nNo accounts with zero balance.");
                } else {
                    System.out.println("\n--- ACCOUNTS WITH ZERO BALANCE ---");
                    for (Account acc : zeroBalance) {
                        System.out.println(acc + "\n");
                    }
                }
                break;

            case "6":
                System.out.println("\n--- SELECT ACCOUNT TYPE ---");
                for (AccountType type : AccountType.values()) {
                    System.out.println("- " + type);
                }
                System.out.print("Enter account type: ");
                String inputType = scanner.nextLine().trim().toUpperCase();

                try {
                    AccountType selectedType = AccountType.valueOf(inputType);
                    int count = database.countAccountsByType(selectedType);
                    System.out.println("\nNumber of accounts of selected type:");
                    System.out.println(selectedType + ": " + count);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid account type entered.");
                }
                break;

            case "7":
                ArrayList<Transaction> transactions = database.getTransactions();
                if (transactions.isEmpty()) {
                    System.out.println("\nNo transactions found.");
                } else {
                    System.out.println("\n--- ALL TRANSACTIONS ---");
                    for (Transaction t : transactions) {
                        System.out.println(t + "\n");
                    }
                }
                break;

            case "0":
                running = false;
                break;

            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    return "Exited admin database view.";
}

    @Override
    public boolean exit() {
        return false;
    }
}
