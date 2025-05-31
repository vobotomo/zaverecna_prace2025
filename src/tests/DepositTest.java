package tests;

import Account.Account;
import BankingSystem.Database;
import BankingSystem.LoginManager;
import Commands.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static Account.AccountType.CHECKING;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit tests for the  Deposit command.
 * Tests deposit functionality for valid and invalid inputs.
 */
public class DepositTest {

    private Database database;
    private LoginManager loginManager;
    private Account account;

    @BeforeEach
    void setUp() {
        database = new Database();
        loginManager = new LoginManager();

        account = new Account("Anna", "Dvorakova", "advorakova", "a@e.cz", "+420111222333", "Heslo123", 500.0, CHECKING, 0.01);
        database.getAccounts().add(account);

        loginManager.login(database ,"advorakova", "Heslo123");
    }

    @Test
    void testSuccessfulDeposit() {
        String input = "250\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Deposit deposit = new Deposit(loginManager, scanner);
        String result = deposit.execute();

        assertEquals("Deposit successful. New balance: 750.0", result);
        assertEquals(750.0, account.getBalance());
    }

    @Test
    void testInvalidAmountInput() {
        String input = "abc\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Deposit deposit = new Deposit(loginManager, scanner);
        String result = deposit.execute();

        assertEquals("Invalid amount.", result);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testZeroOrNegativeAmount() {
        String input = "-100\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Deposit deposit = new Deposit(loginManager, scanner);
        String result = deposit.execute();

        assertEquals("Amount must be greater than 0.", result);
        assertEquals(500.0, account.getBalance());
    }
}