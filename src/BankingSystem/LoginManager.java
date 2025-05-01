package BankingSystem;

import Account.Account;

import java.util.HashMap;

public class LoginManager {

    private Account loggedInAccount;
    private HashMap<String, String> credentials;
    private HashMap<String, Account> accounts;


    public LoginManager(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
        credentials = new HashMap<>();
        accounts = new HashMap<>();
    }

    public boolean login(String username, String password) {
        if (credentials.containsKey(username) && credentials.get(password).equals(password)) {
            loggedInAccount = accounts.get(username);
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInAccount = null;
    }

    public void register(String username, String password) {
        credentials.put(username, password);
        accounts.put(username, loggedInAccount);
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }
}
