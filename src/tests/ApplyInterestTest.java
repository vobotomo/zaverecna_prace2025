package tests;

import Account.Account;
import Account.AccountType;
import BankingSystem.Database;
import BankingSystem.LoginManager;
import Commands.ApplyInterest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ApplyInterest command.
 * Checks if interest is applied correctly only by administrators.
 */
public class ApplyInterestTest {

    static class TestLoginManager extends LoginManager {
        private Account loggedIn;

        public void setLoggedInAccount(Account acc) {
            this.loggedIn = acc;
        }

        @Override
        public Account getLoggedInAccount() {
            return loggedIn;
        }
    }

    @Test
    void testApplyInterestAsAdmin() {
        Database database = new Database();
        TestLoginManager loginManager = new TestLoginManager();

        Account admin = new Account("Admin", "User", "admin1", "admin@example.com", "123456789", "Password1", AccountType.ADMINISTRATION);
        Account acc1 = new Account("Jan", "Novak", "novak1", "jan@example.com", "123456789", "Password1", 1000, AccountType.CHECKING, 0.05);
        Account acc2 = new Account("Petr", "Svoboda", "petr1", "petr@example.com", "123456789", "Password1", 2000, AccountType.SAVINGS, 0.10);

        database.getAccounts().add(acc1);
        database.getAccounts().add(acc2);

        loginManager.setLoggedInAccount(admin);

        ApplyInterest applyInterest = new ApplyInterest(loginManager, database);
        String result = applyInterest.execute();

        assertEquals("Interest successfully applied to "+ database.getAccounts().size() +" accounts.", result);
        assertEquals(1050, acc1.getBalance(), 0.001);  // 1000 + 5% = 1050
        assertEquals(2200, acc2.getBalance(), 0.001);  // 2000 + 10% = 2200
    }

    @Test
    void testApplyInterestWithoutLogin() {
        Database database = new Database();
        TestLoginManager loginManager = new TestLoginManager();

        ApplyInterest applyInterest = new ApplyInterest(loginManager, database);
        String result = applyInterest.execute();

        assertEquals("You must be logged in to apply interest.", result);
    }

    @Test
    void testApplyInterestAsNonAdmin() {
        Database database = new Database();
        TestLoginManager loginManager = new TestLoginManager();

        Account user = new Account("Karel", "Dvorak", "karel123", "karel@email.com", "123456789", "heslo123", AccountType.CHECKING);
        loginManager.setLoggedInAccount(user);

        ApplyInterest applyInterest = new ApplyInterest(loginManager, database);
        String result = applyInterest.execute();

        assertEquals("Access denied: Only administrators can apply interest.", result);
    }
}