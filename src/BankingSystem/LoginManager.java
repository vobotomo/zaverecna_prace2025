package BankingSystem;

import Account.Account;
import Account.AccountType;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The LoginManager class handles user login, logout, and registration.
 * It allows users to log in to an existing account or create a new one.
 */
public class LoginManager {

    /**
     * The currently logged-in account.
     */
    private Account loggedInAccount;

    /**
     * Default constructor.
     */
    public LoginManager() {
    }

    /**
     * Tries to log in a user by checking the username and password.
     *
     * @param database the database with user accounts
     * @param username the entered username
     * @param password the entered password
     * @return true if login is successful, false otherwise
     */
    public boolean login(Database database, String username, String password) {
        Account account = database.accByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            setLoggedInAccount(account);
            return true;
        }
        return false;
    }

    /**
     * Logs out the current user.
     *
     * @return a message confirming the logout
     */
    public String logout() {
        setLoggedInAccount(null);
        return "Logged out successfully";
    }

    /**
     * Registers a new user by creating an account and saving it to the database.
     *
     * @param database     the database to save the account
     * @param name         user's first name
     * @param surname      user's last name
     * @param username     chosen username
     * @param email        user's email address
     * @param phoneNumber  user's phone number
     * @param password     chosen password
     * @param accountType  type of the account
     */
    public void register(Database database, String name, String surname, String username, String email, String phoneNumber, String password, AccountType accountType) {
        Account account = new Account(name, surname, username, email, phoneNumber, password, accountType);
        setLoggedInAccount(account);
        database.getAccounts().add(account);
        database.saveAccounts();
        database.saveTransactions();
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    /**
     * Starts the login or registration process through the console.
     * It asks the user to choose between login and registration.
     *
     * @param database the database with user accounts
     * @return true if the process (login or registration) is successful, false otherwise
     */
    public boolean startLogin(Database database) {
        System.out.println("Do you want to create a new account (1) or login to your existing account (2)?");
        Scanner scanner = new Scanner(System.in);

        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Please enter a valid number.");
            return false;
        }

        switch (choice) {
            case 1:
                String name;
                while (true) {
                    System.out.println("Please enter your name (Start with capital letter, only letters): ");
                    name = scanner.nextLine();
                    if (name.matches("^[A-Z][a-z]+$")) break;
                    System.out.println("Invalid name format.");
                }

                String surname;
                while (true) {
                    System.out.println("Please enter your surname (Start with capital letter, only letters): ");
                    surname = scanner.nextLine();
                    if (surname.matches("^[A-Z][a-z]+$")) break;
                    System.out.println("Invalid surname format.");
                }

                String email;
                while (true) {
                    System.out.println("Please enter your email (e.g. test@example.com): ");
                    email = scanner.nextLine();
                    if (email.matches("^[^@\\s]+@[^@\\s]+\\.[a-z]{2,}$")) break;
                    System.out.println("Invalid email format.");
                }

                String phoneNumber;
                while (true) {
                    System.out.println("Please enter your phone number (+420123456789 or 123456789): ");
                    phoneNumber = scanner.nextLine();
                    if (phoneNumber.matches("^(\\+420)?\\d{9}$")) break;
                    System.out.println("Invalid phone number format.");
                }

                AccountType accountTypeEnum;
                while (true) {
                    System.out.println("Please enter account type (CHECKING, SAVINGS, INVESTMENT): ");
                    String input = scanner.nextLine();
                    try {
                        accountTypeEnum = AccountType.valueOf(input.toUpperCase());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid account type.");
                    }
                }

                String username;
                while (true) {
                    System.out.println("Please enter your username: ");
                    username = scanner.nextLine();
                    if (database.accByUsername(username) == null) break;
                    System.out.println("Username already taken.");
                }

                String password;
                while (true) {
                    System.out.println("Please enter your password (min. 8 characters, letters and digits): ");
                    password = scanner.nextLine();
                    if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) break;
                    System.out.println("Invalid password format.");
                }

                try {
                    register(database, name, surname, username, email, phoneNumber, password, accountTypeEnum);
                } catch (Exception e) {
                    System.out.println("Registration failed: " + e.getMessage());
                    return false;
                }

                System.out.println("Registered successfully");
                return true;

            case 2:
                System.out.println("Please enter your username: ");
                String username1 = scanner.nextLine();

                System.out.println("Please enter your password: ");
                String password1 = scanner.nextLine();

                if (login(database, username1, password1)) {
                    System.out.println("Logged in successfully");
                    return true;
                } else {
                    System.out.println("Login failed");
                    return false;
                }

            default:
                System.out.println("Invalid option. Please select 1 or 2.");
                return false;
        }
    }
}