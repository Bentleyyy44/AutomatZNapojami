package model;

import java.time.LocalDateTime;

public class Transaction {
    private String drinkName;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(String drinkName, double amount) {
        this.drinkName = drinkName;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return dateTime + " - " + drinkName + ": " + amount + " PLN";
    }
}
