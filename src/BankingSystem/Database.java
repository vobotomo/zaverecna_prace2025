package BankingSystem;

import Account.Account;

import java.io.*;
import java.util.TreeSet;

public class Database {

    private TreeSet<Account>accounts;
    private ArrayList<Transaction>transactions;


    public Database() {
        accounts = new TreeSet<>();
        loadAccounts();
        addTransactions;
    }

    public void addTransactions()[
        for(Account acc : accounts){
            transaction.add(acc.getTransactions);
        }
    ]

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Account accByUsername(String username){
        for(Account acc : accounts){
            if(acc.getUsername().equals(username)){
                return acc;
            }
        }
        return null;
    }

    public boolean loadAccounts(){
        accounts.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/res/accounts.dat"))) {
            Object obj = ois.readObject();
            if (obj instanceof TreeSet<?>) {
                TreeSet<?> loaded = (TreeSet<?>) obj;
                for (Object o : loaded) {
                    if (o instanceof Account) {
                        accounts.add((Account) o);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }


    public boolean saveAccounts(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/res/accounts.csv"))) {
            oos.writeObject(accounts);
            return true;
        } catch (IOException e) {
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

    public Account richestAcc(){
        Account acc = accounts.first();
        for(Account a : accounts){
            if(a.getBalance()>acc.getBalance()){
                acc = a;
            }
        }
        return acc;
    }

    public Account poorestAcc(){
        Account acc = accounts.first();
        for(Account a : accounts){
            if(a.getBalance()<acc.getBalance()){
                acc = a;
            }
        }
        return acc;
    }

    public double averageBalance() {
        if (accounts.isEmpty()){
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
