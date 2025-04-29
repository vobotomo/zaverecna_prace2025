package Account;

import java.util.TreeSet;

public class Database {

    private TreeSet<Account>accounts;


    public Database() {
        accounts = new TreeSet<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
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
