package Account;

/**
 * Represents a checking account type that has a small amount of interest.
 * This account is intended for frequent transactions and applies a fixed interest rate.
 */
public class CheckingAccount extends Account {

    /**
     * Creates a new CheckingAccount with a fixed interest rate of 0.001.
     *
     * @param ownerName      the first name of the account owner
     * @param ownerSurname   the surname of the account owner
     * @param username       the account's username
     * @param email          the account owner's email address
     * @param phoneNumber    the account owner's phone number
     * @param password       the account's password
     * @param balance        the initial balance of the account
     * @param accountType    the type of the account (should be CHECKING)
     * @param interestRate   ignored, as the interest rate is set to 0.001 by default
     */
    public CheckingAccount(String ownerName, String ownerSurname, String username, String email, String phoneNumber, String password, double balance, AccountType accountType, double interestRate) {
        super(ownerName, ownerSurname, username, email, phoneNumber, password, balance, accountType, interestRate);
        setInterestRate(0.001);
    }

    /**
     * Applies interest to the account balance using the fixed rate of 0.001.
     */
    @Override
    public void applyInterest() {
        double interest = getBalance() * getInterestRate();
        setBalance(getBalance() + interest);
    }
}