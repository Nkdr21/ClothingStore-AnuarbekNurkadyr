package model;

public class Shirt extends ClothingItem {
    private String size; // S, M, L, XL, XXL

    // Constructor
    public Shirt(int id, String name, double price, String size) {
        super(id, name, price);
        this.size = size;
    }

    // Getter
    public String getSize() {
        return size;
    }

    // Setter (needed for UPDATE)
    public void setSize(String size) {
        this.size = size;
    }

    // Display shirt information
    @Override
    public void displayInfo() {
        System.out.println("Shirt: " + name + " | Size: " + size + " | Price: " + price + " KZT");
    }

    // Return category
    @Override
    public String getCategory() {
        return "SHIRT";
    }

    // Enhanced toString
    @Override
    public String toString() {
        return "ID: " + id + " | Shirt: " + name + " | Size: " + size + " | Price: " + price + " KZT";
    }
}