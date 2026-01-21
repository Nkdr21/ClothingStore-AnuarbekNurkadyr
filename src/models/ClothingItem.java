package models;

import exceptions.MyException;

public abstract class ClothingItem {
    private int id;
    private String name;
    private double price;

    public ClothingItem(int id, String name, double price) throws MyException {
        setId(id);
        setName(name);
        setPrice(price);
    }

    // ABSTRACT METHOD
    public abstract String getCategory();

    // GETTERS
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // SETTERS
    public void setId(int id) throws MyException {
        if (id <= 0) throw new MyException("ID must be positive!");
        this.id = id;
    }

    public void setName(String name) throws MyException {
        if (name == null || name.trim().isEmpty()) throw new MyException("Name cannot be empty!");
        this.name = name;
    }

    public void setPrice(double price) throws MyException {
        if (price < 0) throw new MyException("Price cannot be negative!");
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Price: " + price;
    }
}