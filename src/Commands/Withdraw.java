package Commands;

import java.util.Scanner;
import Account.Account;

public class Withdraw implements Command {


    private Scanner scanner;
    private Account loggedInAccount;

    public Withdraw(Scanner scanner, Account loggedInAccount) {
        this.scanner = scanner;
        this.loggedInAccount = loggedInAccount;
    }



    @Override
    public String execute() {
        if (loggedInAccount == null) {
            return "You must be logged in to withdraw money.";
        }

        try {
            System.out.print("Enter amount to withdraw: ");
            
            double amount = Double.parseDouble(scanner.nextLine().trim());

            if (amount <= 0) {
                return "Amount must be positive.";
            }

            if (amount > loggedInAccount.getBalance()) {
                return "Insufficient balance.";
            }

            return loggedInAccount.withdraw(amount);
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
