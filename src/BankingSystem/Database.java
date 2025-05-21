package BankingSystem;

import Account.Account;

import java.io.*;
import java.util.TreeSet;

public class Database {

    private TreeSet<Account>accounts;


    public Database() {
        accounts = new TreeSet<>();
        loadAccounts();
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


}
