package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class BankingApp {
    private static HashMap<String, BankAccount> accounts
            = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== ShadowFox Banking =====");
            System.out.println("[1] Create Account");
            System.out.println("[2] Deposit");
            System.out.println("[3] Withdraw");
            System.out.println("[4] Check Balance");
            System.out.println("[5] Mini Statement");
            System.out.println("[0] Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> checkBalance();
                case 5 -> miniStatement();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        if (accounts.containsKey(accNum)) {
            System.out.println("❌ Account already exists!");
            return;
        }
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial balance: ");
        BigDecimal balance = sc.nextBigDecimal();
        sc.nextLine();
        try {
            BankAccount account = new BankAccount(
                    accNum, name, balance);
            accounts.put(accNum, account);
            System.out.println("✅ Account created!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void deposit() {
        BankAccount account = getAccount();
        if (account == null) return;
        System.out.print("Enter deposit amount: ");
        BigDecimal amount = sc.nextBigDecimal();
        sc.nextLine();
        try {
            account.deposit(amount);
            System.out.println("New Balance: ₹"
                    + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void withdraw() {
        BankAccount account = getAccount();
        if (account == null) return;
        System.out.print("Enter withdrawal amount: ");
        BigDecimal amount = sc.nextBigDecimal();
        sc.nextLine();
        try {
            account.withdraw(amount);
            System.out.println("New Balance: ₹"
                    + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void checkBalance() {
        BankAccount account = getAccount();
        if (account == null) return;
        System.out.println("Balance: ₹"
                + account.getBalance());
    }

    private static void miniStatement() {
        BankAccount account = getAccount();
        if (account == null) return;
        account.printStatement();
    }

    private static BankAccount getAccount() {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        BankAccount account = accounts.get(accNum);
        if (account == null) {
            System.out.println("❌ Account not found!");
        }
        return account;
    }
}