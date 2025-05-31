package tests;

import Account.Account;
import BankingSystem.Database;
import BankingSystem.LoginManager;
import Commands.Withdraw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static Account.AccountType.CHECKING;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Unit tests for the Withdraw command.
 * Tests withdrawal scenarios including valid, invalid input, negative amount, and insufficient balance.
 */
public class WithdrawTest {

    private Database database;
    private LoginManager loginManager;
    private Account account;

    @BeforeEach
    void setUp() {
        database = new Database();
        loginManager = new LoginManager();

        account = new Account("Jan", "Novak", "jnovak", "j@e.cz", "+420123456789", "Heslo123", 500.0, CHECKING, 0.01);
        database.getAccounts().add(account);

        loginManager.login(database ,"jnovak", "Heslo123");
    }

    @Test
    void testSuccessfulWithdrawal() {
        String input = "200\nNakup\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Withdraw withdraw = new Withdraw(scanner, loginManager);
        String result = withdraw.execute();

        assertEquals("Withdrawal of 200.0 was successful. New balance: 300.0", result);
        assertEquals(300.0, account.getBalance());
    }

    @Test
    void testInvalidAmountFormat() {
        String input = "abc\nPopis\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Withdraw withdraw = new Withdraw(scanner, loginManager);
        String result = withdraw.execute();

        assertEquals("Invalid amount format.", result);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testNegativeAmount() {
        String input = "-50\nPopis\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Withdraw withdraw = new Withdraw(scanner, loginManager);
        String result = withdraw.execute();

        assertEquals("Amount must be positive.", result);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testInsufficientBalance() {
        String input = "1000\nNakup\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Withdraw withdraw = new Withdraw(scanner, loginManager);
        String result = withdraw.execute();

        assertEquals("Withdrawal failed: insufficient balance.", result);
        assertEquals(500.0, account.getBalance());
    }
}