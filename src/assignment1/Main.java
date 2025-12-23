package assignment1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Week 2 - Clothing Store (Nomad Edition)");

        // Traditional / creative items
        ClothingItem shapan = new ClothingItem(201, "Shapan", "L", "Steppe Style", 85000, 4);
        ClothingItem taqiya = new ClothingItem(202, "Taqiya", "M", "Nomad Wear", 12000, 20);

        Customer c1 = new Customer(7, "Nurkadyr", "M");

        System.out.println("\n--- Initial data ---");
        System.out.println(shapan);
        System.out.println(taqiya);
        System.out.println(c1);

        // discount demo
        System.out.println("\n--- Discount demo ---");
        shapan.applyDiscount(15);
        System.out.println("After discount Shapan price: " + shapan.getPrice());
        System.out.println("Is Shapan premium? " + shapan.isPremium());

        // order demo
        System.out.println("\n--- week2.Order demo ---");
        Order order1 = new Order(9001, c1);
        order1.addItem(shapan, 1);
        order1.addItem(taqiya, 2);

        System.out.println("Before complete: " + order1);

        // complete demo (points update)
        order1.complete();
        System.out.println("After complete: " + order1);
        System.out.println("week2.Customer after purchase: " + c1);

        System.out.println("\nProgram complete.");
    }
}
