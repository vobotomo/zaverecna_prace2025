package Account;

public class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, String ownerName, double balance, AccountType accountType, double interestRate) {
        super(accountNumber, ownerName, balance, accountType, interestRate);
    }

    @Override
    public void applyInterest() {

    }
}
