# Learnings - Banking App with JUnit Testing

## Hardest Bug
When testing withdrawal of more than balance,
the test was not catching the exception properly.

## How I Fixed It
Used `assertThrows(IllegalArgumentException.class, () -> ...)`
which correctly catches the exception thrown by withdraw() ✅

## What I Learned
- JUnit 5 lifecycle (@Test, @BeforeEach)
- assertEquals for checking values
- assertThrows for testing exceptions
- BigDecimal for financial calculations
- Transaction history with timestamps
- TDD (Test Driven Development) concept
- Maven dependency management
- Negative testing (testing error cases)