package assignment2;

// Child #2
public class Jacket extends ClothingItem {

    private boolean waterproof;

    public Jacket() {
        super(); // super first
        this.waterproof = false;
    }

    public Jacket(int id, String name, String brand, double price, int stock, boolean waterproof) {
        super(id, name, brand, price, stock); // super first
        this.waterproof = waterproof;
    }

    public boolean isWaterproof() { return waterproof; }

    // override #1
    @Override
    public void wear() {
        System.out.println(name + " jacket, waterproof = " + waterproof);
    }

    // override #2
    @Override
    public String getType() {
        return "Jacket";
    }

    // unique method #1
    public void zip() {
        System.out.println("Zip the jacket.");
    }

    // unique method #2
    public boolean isWinterReady() {
        return waterproof; // simple rule
    }
}
