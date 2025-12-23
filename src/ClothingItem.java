public class ClothingItem {
    private int itemId;
    private String name;
    private String size;   // for example S, M, L...
    private String brand;
    private double price;
    private int stock;

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
        return "ClothingItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
