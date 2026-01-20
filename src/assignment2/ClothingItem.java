package assignment2;

import exception.MyException;

public abstract class ClothingItem {

    protected int id;
    protected String name;
    protected double price;

    // Constructor
    public ClothingItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        setPrice(price); // Validates price immediately
    }

    // Validation logic
    public void setPrice(double price) {
        if (price < 0) {
            throw new MyException("Price cannot be negative!");
        }
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    // Child classes must implement this
    public abstract String getCategory();

    public abstract void wear();

    @Override
    public String toString() {
        return name + " (Price: " + price + ")";
    }
}