public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, String ownerName, double balance, AccountType accountType, double interestRate) {
        super(accountNumber, ownerName, balance, accountType, interestRate);
    }

    @Override
    public void applyInterest() {

    }
}
