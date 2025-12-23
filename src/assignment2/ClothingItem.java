package assignment2;

// Parent
public class ClothingItem {

    // required: 4+ protected fields
    protected int id;
    protected String name;
    protected String brand;
    protected double price;
    protected int stock;

    public ClothingItem() {
        this.id = 0;
        this.name = "Unknown";
        this.brand = "NoBrand";
        this.price = 0.0;
        this.stock = 0;
    }

    public ClothingItem(int id, String name, String brand, double price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        setPrice(price);
        setStock(stock);
    }

    // small validation
    public void setPrice(double price) {
        this.price = (price < 0) ? 0.0 : price;
    }

    public void setStock(int stock) {
        this.stock = (stock < 0) ? 0 : stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // method for polymorphism (children override)
    public void wear() {
        System.out.println(name + " is a basic item.");
    }

    // method for polymorphism (children override)
    public String getType() {
        return "ClothingItem";
    }

    @Override
    public String toString() {
        return getType() + " {id=" + id + ", name='" + name + "', brand='" + brand
                + "', price=" + price + ", stock=" + stock + "}";
    }
}
