package BankingSystem;

import Commands.*;

import java.util.HashMap;
import java.util.Scanner;

public class Console {

    private Scanner sc = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> commands;

    public void initialization(LoginManager loginManager, Database database) {
        commands = new HashMap<>();
        commands.put("exit", new Exit(database));
        commands.put("logout", new Logout(loginManager, database));
        commands.put("transfer", new Transfer(loginManager, database, sc));
        commands.put("withdraw", new Withdraw(sc, loginManager));
        commands.put("deposit", new Deposit(loginManager, sc));
        commands.put("interest", new ApplyInterest(loginManager, database));
        commands.put("database", new ShowDatabase(database, loginManager, sc));
        commands.put("info", new ShowInformation(loginManager));
        commands.put("help", new Help(database));
        commands.put("delete", new Delete(database, loginManager));
    }


    public void executeCommand() {
        System.out.println(">>");
        String command = sc.nextLine();
        if (commands.containsKey(command)) {
            System.out.println(commands.get(command).execute());
            exit = commands.get(command).exit();
        } else {
            System.out.println("Invalid command");
        }
    }


    public void start() {
        Database database = new Database();
        LoginManager loginManager = new LoginManager();

        while (!loginManager.startLogin(database)) {
            System.out.println("Try again.");
        }

        initialization(loginManager, database);

        do {
            if (loginManager.getLoggedInAccount() == null) {
                while (!loginManager.startLogin(database)) {
                    System.out.println("Try again.");
                }
            }
            executeCommand();
        } while (!exit);
    }

}
