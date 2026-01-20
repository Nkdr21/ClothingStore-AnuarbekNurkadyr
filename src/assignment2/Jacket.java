package assignment2;

public class Jacket extends ClothingItem {

    private boolean isWaterproof;

    // CONSTRUCTOR:
    public Jacket(int id, String name, double price, boolean isWaterproof) {
        super(id, name, price); // <--- ҚАЗІР ДҰРЫС БОЛДЫ (3 зат)
        this.isWaterproof = isWaterproof;
    }

    public boolean isWaterproof() {
        return isWaterproof;
    }

    @Override
    public String getCategory() {
        return "Outerwear";
    }

    @Override
    public void wear() {
        System.out.println("Wearing jacket. Waterproof: " + isWaterproof);
    }

    @Override
    public String toString() {
        return "Jacket: " + getName() + " (Waterproof: " + isWaterproof + ")";
    }
}