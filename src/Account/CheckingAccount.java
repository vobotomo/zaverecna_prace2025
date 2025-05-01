package Account;

public class CheckingAccount extends Account {
    public CheckingAccount(String ownerName, String ownerSurname, String username, String email, String phoneNumber, String password, double balance, AccountType accountType, double interestRate) {
        super(ownerName, ownerSurname, username, email, phoneNumber, password, balance, accountType, interestRate);
    }

    @Override
    public void applyInterest() {

    }
}
