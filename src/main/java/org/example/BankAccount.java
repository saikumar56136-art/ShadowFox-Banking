package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber,
                       String accountHolder,
                       BigDecimal initialBalance) {
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative!");
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    // Deposit - synchronized for thread safety
    public synchronized void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    "Deposit amount must be positive!");
        balance = balance.add(amount);
        transactions.add(new Transaction("DEPOSIT", amount));
        System.out.println("✅ Deposited: ₹" + amount);
    }

    // Withdraw - synchronized for thread safety
    public synchronized void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    "Withdrawal amount must be positive!");
        if (amount.compareTo(balance) > 0)
            throw new IllegalArgumentException(
                    "Insufficient funds!");
        balance = balance.subtract(amount);
        transactions.add(new Transaction("WITHDRAWAL", amount));
        System.out.println("✅ Withdrawn: ₹" + amount);
    }

    // Get balance
    public synchronized BigDecimal getBalance() {
        return balance;
    }

    // Get transaction history
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Print mini statement
    public void printStatement() {
        System.out.println("\n===== Mini Statement =====");
        System.out.println("Account: " + accountNumber);
        System.out.println("Holder: " + accountHolder);
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet!");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
        System.out.println("Current Balance: ₹" + balance);
        System.out.println("==========================");
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
}