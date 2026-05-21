package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private BigDecimal amount;
    private LocalDateTime dateTime;

    public Transaction(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH:mm:ss");
        return "[" + dateTime.format(formatter) + "] "
                + type + " : ₹" + amount;
    }
}