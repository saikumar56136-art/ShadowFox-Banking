package org.example;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(
                "ACC001", "Sai Kumar",
                new BigDecimal("10000"));
    }

    @Test
    void testInitialBalance() {
        assertEquals(new BigDecimal("10000"),
                account.getBalance());
    }

    @Test
    void testDeposit() {
        account.deposit(new BigDecimal("5000"));
        assertEquals(new BigDecimal("15000"),
                account.getBalance());
    }

    @Test
    void testWithdraw() {
        account.withdraw(new BigDecimal("3000"));
        assertEquals(new BigDecimal("7000"),
                account.getBalance());
    }

    @Test
    void testInsufficientFunds() {
        assertThrows(IllegalArgumentException.class, () ->
                account.withdraw(new BigDecimal("99999")));
    }

    @Test
    void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () ->
                account.deposit(new BigDecimal("-100")));
    }

    @Test
    void testNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () ->
                new BankAccount("ACC002", "Test",
                        new BigDecimal("-1000")));
    }

    @Test
    void testTransactionHistoryAfterDeposit() {
        account.deposit(new BigDecimal("5000"));
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void testTransactionHistoryAfterMultiple() {
        account.deposit(new BigDecimal("5000"));
        account.withdraw(new BigDecimal("2000"));
        assertEquals(2, account.getTransactions().size());
    }

    @Test
    void testZeroDeposit() {
        assertThrows(IllegalArgumentException.class, () ->
                account.deposit(BigDecimal.ZERO));
    }

    @Test
    void testZeroWithdraw() {
        assertThrows(IllegalArgumentException.class, () ->
                account.withdraw(BigDecimal.ZERO));
    }

    @Test
    void testConcurrentDeposits() throws InterruptedException {
        Thread t1 = new Thread(() ->
                account.deposit(new BigDecimal("1000")));
        Thread t2 = new Thread(() ->
                account.deposit(new BigDecimal("1000")));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertEquals(new BigDecimal("12000"),
                account.getBalance());
    }

    @Test
    void testConcurrentWithdrawals() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                account.withdraw(new BigDecimal("3000"));
            } catch (IllegalArgumentException e) {
                System.out.println("Thread 1: " + e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                account.withdraw(new BigDecimal("3000"));
            } catch (IllegalArgumentException e) {
                System.out.println("Thread 2: " + e.getMessage());
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertTrue(account.getBalance()
                .compareTo(BigDecimal.ZERO) >= 0);
        System.out.println("Final Balance: ₹"
                + account.getBalance());
    }
}