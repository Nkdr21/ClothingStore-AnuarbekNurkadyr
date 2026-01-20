package assignment2;

public class Shirt extends ClothingItem implements Foldable {

    private String sleeveType;

    public Shirt(int id, String name, double price, String sleeveType) {
        super(id, name, price);
        this.sleeveType = sleeveType;
    }

    public String getSleeveType() {
        return sleeveType;
    }

    @Override
    public String getCategory() {
        return "Topwear";
    }

    @Override
    public void wear() {
        System.out.println("Wearing " + getName());
    }

    @Override
    public void fold() {
        System.out.println("Shirt is folded neatly.");
    }

    @Override
    public String toString() {
        return "Shirt: " + getName() + " (Sleeve: " + sleeveType + ")";
    }
}