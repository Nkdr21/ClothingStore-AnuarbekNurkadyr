package assignment1;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<ClothingItem> items;
    private String status; // NEW, COMPLETED, CANCELED
    private double total;

    //default constructor (required)
    public Order() {
        this.orderId = 0;
        this.customer = new Customer();
        this.items = new ArrayList<>();
        this.status = "NEW";
        this.total = 0.0;
    }

    //parameterized constructor
    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "NEW";
        this.total = 0;
    }

    // logic method #1
    public void addItem(ClothingItem item, int qty) {
        if (!status.equals("NEW")) return;
        if (item == null) return;

        boolean sold = item.sell(qty);
        if (!sold) return;

        for (int i = 0; i < qty; i++) {
            items.add(item);
        }
        total += item.getPrice() * qty;
    }

    // logic method #2
    public void complete() {
        if (!status.equals("NEW")) return;
        status = "COMPLETED";

        customer.addPurchase(total);
    }

    public void cancel() {
        if (!status.equals("NEW")) return;
        status = "CANCELED";
    }

    // getters/setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public ArrayList<ClothingItem> getItems() { return items; }
    public void setItems(ArrayList<ClothingItem> items) { this.items = items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "week2.Order{" +
                "orderId=" + orderId +
                ", customer=" + customer.getName() +
                ", itemsCount=" + items.size() +
                ", status='" + status + '\'' +
                ", total=" + total +
                '}';
    }
}
