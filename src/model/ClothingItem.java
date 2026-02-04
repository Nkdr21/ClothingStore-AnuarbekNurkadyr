package model;

public abstract class ClothingItem implements Discountable {
    protected int id;
    protected String name;
    protected double price;

    // Constructor
    public ClothingItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setters (needed for UPDATE operations)
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Abstract method - must be implemented by child classes
    public abstract void displayInfo();

    // Discountable interface implementation
    @Override
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            this.price = this.price - (this.price * percentage / 100);
            System.out.println("Discount applied! New price: " + this.price);
        } else {
            System.out.println("Invalid discount percentage!");
        }
    }

    // toString for easy printing
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Price: " + price + " KZT";
    }
}