package models;

import exceptions.MyException;

public class Jacket extends ClothingItem {
    private boolean isWaterproof;

    public Jacket(int id, String name, double price, boolean isWaterproof) throws MyException {
        super(id, name, price);
        this.isWaterproof = isWaterproof;
    }

    @Override
    public String getCategory() {
        return "Jacket";
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Jacket | Waterproof: " + (isWaterproof ? "Yes" : "No");
    }
}