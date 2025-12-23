package assignment1;

public class Customer {
    private int customerId;
    private String name;
    private String preferredSize;
    private int points;
    private double totalSpent;

    // default constructor (required)
    public Customer() {
        this.customerId = 0;
        this.name = "Unknown";
        this.preferredSize = "Unknown";
        this.points = 0;
        this.totalSpent = 0.0;
    }

    // parameterized constructor
    public Customer(int customerId, String name, String preferredSize) {
        this.customerId = customerId;
        this.name = name;
        this.preferredSize = preferredSize;
        this.points = 0;
        this.totalSpent = 0;
    }

    // logic method #1
    public void addPurchase(double amount) {
        if (amount <= 0) return;
        totalSpent += amount;

        // like 1 point for every 1000 tenge
        points += (int)(amount / 1000);
    }

    // logic method #2
    public boolean isVIP() {
        return points >= 100 || totalSpent >= 200000;
    }

    // getters/setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPreferredSize() { return preferredSize; }
    public void setPreferredSize(String preferredSize) { this.preferredSize = preferredSize; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public double getTotalSpent() { return totalSpent; }
    public void setTotalSpent(double totalSpent) { this.totalSpent = totalSpent; }

    @Override
    public String toString() {
        return "week2.Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", preferredSize='" + preferredSize + '\'' +
                ", points=" + points +
                ", totalSpent=" + totalSpent +
                ", VIP=" + isVIP() +
                '}';
    }
}
