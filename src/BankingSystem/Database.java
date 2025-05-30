package BankingSystem;

import Account.Account;
import Account.AccountType;
import Account.CheckingAccount;
import Account.SavingsAccount;
import Account.AdministrationAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Database {

    private TreeSet<Account> accounts;
    private ArrayList<Transaction> transactions;


    public Database() {
        accounts = new TreeSet<>();
        transactions = new ArrayList<>();
        loadAccounts();
        addTransactions();
    }

    public void addTransactions() {
        for (Account acc : accounts) {
            transactions.addAll(acc.getTransactions());
        }
    }


    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Account accByUsername(String username) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) {
                return acc;
            }
        }
        return null;
    }


    public String loadCommands() {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/res/commands.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: commands file could not be loaded.";
        }
        return result;
    }

    public boolean loadAccounts() {
        accounts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/res/accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 10) continue;

                int id = Integer.parseInt(parts[0]);
                String ownerName = parts[1];
                String ownerSurname = parts[2];
                String username = parts[3];
                String email = parts[4];
                String phone = parts[5];
                String password = parts[6];
                double balance = Double.parseDouble(parts[7]);
                AccountType type = AccountType.valueOf(parts[8]);
                double interestRate = Double.parseDouble(parts[9]);

                Account acc;
                switch (type) {
                    case CHECKING:
                        acc = new CheckingAccount(ownerName, ownerSurname, username, email, phone, password, balance, type, interestRate);
                        break;
                    case SAVINGS:
                        acc = new SavingsAccount(ownerName, ownerSurname, username, email, phone, password, balance, type, interestRate);
                        break;
                    case ADMINISTRATION:
                        acc = new AdministrationAccount(ownerName, ownerSurname, username, email, phone, password, balance, type, interestRate, this);
                        break;
                    default:
                        acc = new Account(ownerName, ownerSurname, username, email, phone, password, balance, type, interestRate);
                }

                acc.setId(id);
                accounts.add(acc);
            }
            return true;
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/res/accounts.txt"))) {
            for (Account acc : accounts) {
                writer.println(String.join(";",
                        String.valueOf(acc.getId()),
                        acc.getOwnerName(),
                        acc.getOwnerSurname(),
                        acc.getUsername(),
                        acc.getEmail(),
                        acc.getPhoneNumber(),
                        acc.getPassword(),
                        String.valueOf(acc.getBalance()),
                        acc.getAccountType().name(),
                        String.valueOf(acc.getInterestRate())
                ));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean removeAccount(Account account) {
        for (Account a : accounts) {
            if (a.equals(account)) {
                accounts.remove(a);
                return true;
            }
        }
        return false;
    }

    public TreeSet<Account> getAccounts() {
        return accounts;
    }

    public Account richestAcc() {
        Account acc = accounts.first();
        for (Account a : accounts) {
            if (a.getBalance() > acc.getBalance()) {
                acc = a;
            }
        }
        return acc;
    }

    public Account poorestAcc() {
        Account acc = accounts.first();
        for (Account a : accounts) {
            if (a.getBalance() < acc.getBalance()) {
                acc = a;
            }
        }
        return acc;
    }

    public double averageBalance() {
        if (accounts.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        return total / accounts.size();
    }

    public ArrayList<Account> accountsWithZeroBalance() {
        ArrayList<Account> temp = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getBalance() == 0) {
                temp.add(acc);
            }
        }
        return temp;
    }

    public int countAccountsByType(AccountType type) {
        int count = 0;
        for (Account acc : accounts) {
            if (acc.getAccountType() == type) {
                count++;
            }
        }
        return count;
    }
}
