package Commands;

import java.util.Scanner;
import BankingSystem.LoginManager;

public class Withdraw implements Command {

    private LoginManager loginManager;
    private Scanner scanner;

    public Withdraw(Scanner scanner, LoginManager loginManager) {
        this.scanner = scanner;
        this.loginManager = loginManager;
    }



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

            if (amount > loginManager.getLoggedInAccount().getBalance()) {
                return "Insufficient balance.";
            }

            return loginManager.getLoggedInAccount().withdraw(amount);
        } catch (NumberFormatException e) {
            return "Invalid amount format.";
        } catch (Exception e) {
           return "An unexpected error occurred: " + e.getMessage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
