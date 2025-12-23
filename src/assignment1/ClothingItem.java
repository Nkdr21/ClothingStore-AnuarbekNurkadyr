package assignment1;

public class ClothingItem {
    private int itemId;
    private String name;
    private String size;   // Like S, M, L...
    private String brand;
    private double price;
    private int stock;

    // default constructor (required)
    public ClothingItem() {
        this.itemId = 0;
        this.name = "Unknown";
        this.size = "Unknown";
        this.brand = "NoBrand";
        this.price = 0.0;
        this.stock = 0;
    }

    // parameterized constructor
    public ClothingItem(int itemId, String name, String size, String brand, double price, int stock) {
        this.itemId = itemId;
        this.name = name;
        this.size = size;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    // logic method #1
    public void applyDiscount(double percent) {
        if (percent <= 0 || percent > 80) {
            return;
        }
        price = price - (price * percent / 100.0);
    }

    // logic method #2
    public boolean isPremium() {
        return price >= 50000;
    }

    public boolean sell(int qty) {
        if (qty <= 0) return false;
        if (stock >= qty) {
            stock -= qty;
            return true;
        }
        return false;
    }

    //week2.Customer a = new
    //week2.Customer();  default

    //week2.Customer a = new c1(1,NurKadyr)2
    // getters/setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "week2.ClothingItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
