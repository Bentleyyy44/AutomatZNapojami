package model;

public class Drink {
    private String name;
    private double price;
    private int quantity;

    public Drink(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Drink(String name) {
        this(name, 0.0, 0);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }


    @Override
    public String toString() {
        return name + " | Cena: " + price + " PLN | Ilość: " + quantity;
    }
}
