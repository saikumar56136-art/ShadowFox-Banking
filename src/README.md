# ShadowFox Banking App

Bank Account Management System with JUnit Testing
built as part of ShadowFox Java Internship - Part 2 Task 1.

## Features

### Baseline
- Create bank accounts
- Deposit money with validation
- Withdraw money with validation
- Check account balance
- Mini statement with timestamps
- Transaction history

### Tier 1 - Transaction History
- Complete transaction log with date and time
- Each transaction shows type and amount
- Mini statement prints all transactions
- Balance shown after each transaction

### Tier 2 - Concurrency Safety
- synchronized deposit() method
- synchronized withdraw() method
- synchronized getBalance() method
- Race condition prevention
- Two threads tested simultaneously

## JUnit Tests (12 Tests ✅)
| Test | Description |
|------|-------------|
| testInitialBalance | Checks starting balance |
| testDeposit | Verifies deposit works |
| testWithdraw | Verifies withdrawal works |
| testInsufficientFunds | Checks overdraft prevention |
| testNegativeDeposit | Validates negative amount |
| testNegativeInitialBalance | Validates account creation |
| testTransactionHistoryAfterDeposit | Checks history size |
| testTransactionHistoryAfterMultiple | Checks multiple transactions |
| testZeroDeposit | Validates zero amount |
| testZeroWithdraw | Validates zero amount |
| testConcurrentDeposits | Tests thread safety |
| testConcurrentWithdrawals | Tests race condition |

## How to Run

### Banking App
1. Open in IntelliJ IDEA
2. Run `BankingApp.java`

### Run JUnit Tests
1. Open in IntelliJ IDEA
2. Right-click `BankAccountTest.java`
3. Click Run Tests
4. All 12 tests should pass ✅

## Technologies Used
- Java 25
- JUnit 5
- BigDecimal
- Maven
- Multi-threading (synchronized)

## Project Structure
## Author
Sai Kumar - ShadowFox Java Internship 2026