package models;

import exceptions.MyException;
import interfaces.Discountable;

public class Shirt extends ClothingItem implements Discountable {
    private String size; // S, M, L

    public Shirt(int id, String name, double price, String size) throws MyException {
        super(id, name, price);
        this.size = size;
    }

    @Override
    public String getCategory() {
        return "Shirt";
    }

    @Override
    public double calculateDiscount(double percent) {
        return getPrice() - (getPrice() * (percent / 100));
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Shirt | Size: " + size;
    }
}