package Account;

/**
 * Represents a savings account that has a higher interest rate than a checking account.
 * Intended for users who want to save money and earn interest over time.
 */
public class SavingsAccount extends Account {

    /**
     * Creates a new SavingsAccount with a fixed interest rate of 0.02.
     *
     * @param ownerName      the first name of the account owner
     * @param ownerSurname   the surname of the account owner
     * @param username       the account's username
     * @param email          the account owner's email address
     * @param phoneNumber    the account owner's phone number
     * @param password       the account's password
     * @param balance        the initial balance of the account
     * @param accountType    the type of the account (should be SAVINGS)
     * @param interestRate   ignored, as the interest rate is set to 0.02 by default
     */
    public SavingsAccount(String ownerName, String ownerSurname, String username, String email, String phoneNumber, String password, double balance, AccountType accountType, double interestRate) {
        super(ownerName, ownerSurname, username, email, phoneNumber, password, balance, accountType, interestRate);
        setInterestRate(0.02);
    }

    /**
     * Applies interest to the account balance using the fixed rate of 0.02.
     */
    @Override
    public void applyInterest() {
        double interest = getBalance() * getInterestRate();
        setBalance(getBalance() + interest);
    }
}
