## Tier 2 Concurrency Learnings

### Hardest Bug
Two threads withdrawing at the same time could
cause balance to go below zero (Race Condition).

### How I Fixed It
Added `synchronized` keyword to deposit() and
withdraw() methods. This ensures only one thread
can access the method at a time, preventing
data corruption.

### What I Learned
- Race Condition problem in multi-threading
- synchronized keyword in Java
- Thread class and Runnable
- t.start() and t.join() methods
- Writing concurrent JUnit tests
- Why Fintech apps need thread safety