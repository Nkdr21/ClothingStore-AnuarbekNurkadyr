package assignment2;

// Child #1
public class Shirt extends ClothingItem {

    private String sleeve; // short/long

    public Shirt() {
        super(); // super first
        this.sleeve = "short";
    }

    public Shirt(int id, String name, String brand, double price, int stock, String sleeve) {
        super(id, name, brand, price, stock); // super first
        this.sleeve = sleeve;
    }

    public String getSleeve() { return sleeve; }

    // override #1
    @Override
    public void wear() {
        System.out.println(name + " shirt, sleeve = " + sleeve);
    }

    // override #2
    @Override
    public String getType() {
        return "Shirt";
    }

    // unique method #1
    public void fold() {
        System.out.println("Folding the shirt.");
    }

    // unique method #2
    public boolean isFormal() {
        return "long".equalsIgnoreCase(sleeve);
    }
}

