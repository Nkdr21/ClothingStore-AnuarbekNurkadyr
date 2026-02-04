package model;

public class Jacket extends ClothingItem {
    private String fabric; // Leather, Denim, Cotton, etc.

    // Constructor
    public Jacket(int id, String name, double price, String fabric) {
        super(id, name, price);
        this.fabric = fabric;
    }

    // Getter
    public String getFabric() {
        return fabric;
    }

    // Setter (needed for UPDATE)
    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    // Display jacket information
    @Override
    public void displayInfo() {
        System.out.println("Jacket: " + name + " | Fabric: " + fabric + " | Price: " + price + " KZT");
    }

    // Return category
    @Override
    public String getCategory() {
        return "JACKET";
    }

    // Enhanced toString
    @Override
    public String toString() {
        return "ID: " + id + " | Jacket: " + name + " | Fabric: " + fabric + " | Price: " + price + " KZT";
    }
}