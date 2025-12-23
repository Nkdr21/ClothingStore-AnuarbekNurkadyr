public class Main {
    public static void main(String[] args) {
        System.out.println("Week 2 - Clothing Store");

        // 1) objects
        ClothingItem hoodie = new ClothingItem(101, "Hoodie", "M", "Nike", 65000, 10);
        ClothingItem jeans  = new ClothingItem(102, "Jeans", "L", "Levis", 45000, 5);

        Customer c1 = new Customer(1, "Aruzhan", "M");

        // 2) print
        System.out.println(hoodie);
        System.out.println(jeans);
        System.out.println(c1);

        // 3) logic methods: discount + premium check
        hoodie.applyDiscount(10);
        System.out.println("After discount hoodie price: " + hoodie.getPrice());
        System.out.println("Is hoodie premium? " + hoodie.isPremium());

        // 4) Order demo
        Order order1 = new Order(5001, c1);
        order1.addItem(hoodie, 2);
        order1.addItem(jeans, 1);

        System.out.println(order1);

        // 5) complete order
        order1.complete();
        System.out.println("After complete:");
        System.out.println(order1);
        System.out.println(c1); // points, VIP changed maybe

        System.out.println("Program complete.");
    }
}
