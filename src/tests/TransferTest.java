package tests;

import Account.Account;
import BankingSystem.Database;
import BankingSystem.LoginManager;
import Commands.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static Account.AccountType.CHECKING;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    private Database database;
    private LoginManager loginManager;
    private Account sender;
    private Account recipient;

    @BeforeEach
    void setUp() {
        database = new Database();
        loginManager = new LoginManager();

        sender = new Account("Jan", "Novak", "jnovak", "j@e.cz", "+420123456789", "Heslo123", 1000, CHECKING, 0.01);
        recipient = new Account("Petr", "Svoboda", "psvoboda", "p@e.cz", "+420987654321", "Heslo456", 500, CHECKING, 0.01);

        database.getAccounts().add(sender);
        database.getAccounts().add(recipient);

        loginManager.login(database, "jnovak", "Heslo123");
    }

    @Test
    void testSuccessfulTransfer() {
        String simulatedInput = "psvoboda\n200\nkebab\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        Transfer transfer = new Transfer(loginManager, database, scanner);

        String result = transfer.execute();

        assertEquals("Transfer of 200.0 to psvoboda was successful. New balance: 800.0 Description: kebab", result);
        assertEquals(800.0, sender.getBalance());
        assertEquals(700.0, recipient.getBalance());
        assertEquals(1, sender.getTransactions().size());
        assertEquals(1, recipient.getTransactions().size());
    }
}