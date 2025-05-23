package BankingSystem;

import Commands.*;

import java.util.HashMap;
import java.util.Scanner;

public class Console {

    private Scanner sc = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> commands;

    public void initialization(LoginManager loginManager, Database database){
        commands = new HashMap<>();
        commands.put("exit", new Exit(database));
        commands.put("logout", new Logout(loginManager));
        commands.put("transfer", new Transfer(loginManager, database, sc));
        commands.put("withdraw", new Withdraw());
        commands.put("deposit", new Deposit());
        commands.put("interest", new ApplyInterest());
        commands.put("database", new ShowDatabase());
        commands.put("info", new ShowInformation());
    }


    public void executeCommand(){
        System.out.println(">>");
        String command = sc.nextLine();
        if(commands.containsKey(command)){
            System.out.println(commands.get(command).execute());
            exit = commands.get(command).exit();
        }else{
            System.out.println("Invalid command");
        }
    }



    public void start(){
        Database database = new Database();
        LoginManager loginManager = new LoginManager(database);
        initialization(loginManager, database);

        do{
            executeCommand();
        }while(!exit);
    }

}
