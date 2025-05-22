package Commands;

import java.util.scanner;
import BankingSystem.LoginManager;
import Account.Account;
import BankingSystem.Database;

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

        //StringBuilder - ChatGPT
        StringBuilder output = new StringBuilder();
        int number = Integer.MAX_VALUE;

        while (number != 0) {
            System.out.println("\n--- ADMIN DATABASE MENU ---");
            System.out.println("1. Show all accounts");
            System.out.println("2. Show richest account");
            System.out.println("3. Show poorest account");
            System.out.println("0. Exit to main menu");
            System.out.print("Choose option: ");

            String number = scanner.nextLine();

            switch (number) {
                case 1:
                
                    output.setLength(0);
                    output.append("--- ALL ACCOUNTS ---\n");
                    for (Account acc : database.getAccounts()) {
                        output.append(acc).append("\n\n");
                    }
                    System.out.println(output);
                    break;
                case 2:
                    Account richest = database.richestAcc();
                    System.out.println("\n--- RICHEST ACCOUNT ---");
                    System.out.println(richest);
                    break;
                case 3:
                    Account poorest = database.poorestAcc();
                    System.out.println("\n--- POOREST ACCOUNT ---");
                    System.out.println(poorest);
                    break;
                case 4:
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
