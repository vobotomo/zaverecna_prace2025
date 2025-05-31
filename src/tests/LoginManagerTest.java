package tests;

import static org.junit.jupiter.api.Assertions.*;

import Account.Account;
import Account.AccountType;
import BankingSystem.Database;
import BankingSystem.LoginManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * Unit tests for the LoginManager class.
 * Tests user registration, login, and input validation during login process.
 *
 * ChatGPT helped
 */
public class LoginManagerTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testStartLogin_register_success() {
        String input = String.join(System.lineSeparator(),
                "1",
                "Pepa",
                "Novak",
                "test@test.com",
                "+420123456789",
                "CHECKING",
                "pepanovak123",
                "heslo1234"
        ) + System.lineSeparator();

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LoginManager manager = new LoginManager();
        Database db = new Database();

        boolean result = manager.startLogin(db);
        assertTrue(result);
        assertNotNull(manager.getLoggedInAccount());

        Account account = db.accByUsername("pepanovak123");
        assertNotNull(account);
        assertEquals("Pepa", account.getOwnerName());
        assertEquals(AccountType.CHECKING, account.getAccountType());
    }

    @Test
    void testStartLogin_login_success() {
        Database db = new Database();
        LoginManager manager = new LoginManager();

        Account account = new Account("Eva", "Novotna", "eva", "e@e.com", "123456789", "heslo123", AccountType.SAVINGS);
        db.getAccounts().add(account);

        String input = String.join(System.lineSeparator(),
                "2",
                "eva",
                "heslo123"
        ) + System.lineSeparator();

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        boolean result = manager.startLogin(db);
        assertTrue(result);
        assertEquals(account, manager.getLoggedInAccount());
    }

    @Test
    void testStartLogin_invalidInput() {
        String input = "abc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LoginManager manager = new LoginManager();
        Database db = new Database();

        boolean result = manager.startLogin(db);
        assertFalse(result);
        assertTrue(outContent.toString().contains("Please enter a valid number."));
    }
}