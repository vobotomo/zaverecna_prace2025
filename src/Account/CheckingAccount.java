package Account;

public class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, String ownerName, String email, String phoneNumber, String password, double balance, AccountType accountType, double interestRate) {
        super(accountNumber, ownerName, email, phoneNumber, password, balance, accountType, interestRate);
    }

    @Override
    public void applyInterest() {

    }
}
