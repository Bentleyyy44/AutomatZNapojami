package model;

import exception.InsufficientFundsException;
import service.TransactionManager;

import java.util.List;

public class VendingMachine {
    private List<Drink> drinks;
    private TransactionManager transactionManager;

    public VendingMachine(List<Drink> drinks, TransactionManager transactionManager) {
        this.drinks = drinks;
        this.transactionManager = transactionManager;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void removeDrink(String name) {
        drinks.removeIf(d -> d.getName().equalsIgnoreCase(name));
    }

    public void buyDrink(String name, double insertedAmount) throws InsufficientFundsException {
        for (Drink d : drinks) {
            if (d.getName().trim().equalsIgnoreCase(name.trim())) {
                if (d.getQuantity() <= 0) {
                    throw new InsufficientFundsException("Brak produktu na stanie.");
                }
                if (insertedAmount < d.getPrice()) {
                    throw new InsufficientFundsException("Za mało pieniędzy! Napój kosztuje: " + d.getPrice() + " PLN");
                }
                d.decreaseQuantity(); // zmniejszenie ilości
                transactionManager.addTransaction(new model.Transaction(name, d.getPrice()));
                System.out.println("Wydano resztę: " + (insertedAmount - d.getPrice()) + " PLN");
                return;
            }
        }
        throw new InsufficientFundsException("Nie znaleziono napoju o podanej nazwie.");
    }
}
