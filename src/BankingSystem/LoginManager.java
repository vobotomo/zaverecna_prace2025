package BankingSystem;

import Account.Account;
import Account.AccountType;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginManager {

    private Account loggedInAccount;
    private HashMap<String, String> credentials;
    private HashMap<String, Account> accounts;


    public LoginManager() {
        credentials = new HashMap<>();
        accounts = new HashMap<>();
        startLogin();
    }

    public boolean login(String username, String password) {
        if (credentials.containsKey(username) && credentials.get(password).equals(password)) {
            loggedInAccount = accounts.get(username);
            return true;
        }
        return false;
    }

    public String logout() {
        loggedInAccount = null;
        return "Logged out successfully";
    }

    public void register(String name, String surname, String username, String email,String phoneNumber, String password, AccountType accountType) {
        Account account = new Account(name, surname, username, email, phoneNumber, password, accountType);
        credentials.put(username, password);
        accounts.put(username, loggedInAccount);
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }


    public String startLogin() {
        System.out.println("Do you want to create a new account(1) or login your existing account?(2)");
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please enter your name: ");
                    String name = scanner.nextLine();

                    System.out.println("Please enter your surname: ");
                    String surname = scanner.nextLine();

                    System.out.println("Please enter your email (test@example.com):");
                    String email = scanner.nextLine();

                    System.out.println("Please enter your phone number (+420123456789 / 123456789): ");
                    String phoneNumber = scanner.nextLine();

                    System.out.println("Please enter the type of account you want (CHECKING, SAVINGS, INVESTMENT): ");
                    String accountTypeInput = scanner.nextLine();

                    AccountType accountTypeEnum;
                    try {
                        accountTypeEnum = AccountType.valueOf(accountTypeInput.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid account type.");
                        return "Registration failed.";
                    }

                    System.out.println("Please enter your username: ");
                    String username = scanner.nextLine();

                    System.out.println("Please enter your password (abc12345, A1b2c3d4, password1): ");
                    String password = scanner.nextLine();


                    register(name, surname, username, email, phoneNumber, password, accountTypeEnum);
                    return "Registered Successfully";
                case 2:

                    System.out.println("Please enter your username: ");
                    String username1 = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    String password1 = scanner.nextLine();

                    if(login(username1, password1)){
                        return "Logged in successfully";
                    }else {
                        return "Login failed";
                    }

            }
        }catch (InputMismatchException e) {
            return "Please enter a valid number";
        }
        return "Login failed";
    }
}
