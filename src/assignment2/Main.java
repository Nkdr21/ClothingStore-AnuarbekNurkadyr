package assignment2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ONE polymorphic list
    static ArrayList<ClothingItem> items = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU (Assignment 2) ---");
            System.out.println("1) Add parent item");
            System.out.println("2) Add shirt");
            System.out.println("3) Add jacket");
            System.out.println("4) View all");
            System.out.println("5) Polymorphism demo (wear)");
            System.out.println("6) View shirts only (instanceof)");
            System.out.println("7) View jackets only (instanceof)");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice = readInt(sc);

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    addParent(sc);
                    break;
                case 2:
                    addShirt(sc);
                    break;
                case 3:
                    addJacket(sc);
                    break;
                case 4:
                    viewAll();
                    break;
                case 5:
                    demoWear();
                    break;
                case 6:
                    viewShirtsOnly();
                    break;
                case 7:
                    viewJacketsOnly();
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }

        sc.close();
        System.out.println("Bye!");
    }

    // -------- menu actions --------
    static void addParent(Scanner sc) {
        System.out.println("\nAdd ClothingItem:");
        int id = askInt(sc, "id: ");
        String name = askLine(sc, "name: ");
        String brand = askLine(sc, "brand: ");
        double price = askDouble(sc, "price: ");
        int stock = askInt(sc, "stock: ");

        ClothingItem item = new ClothingItem(id, name, brand, price, stock);
        items.add(item);
        System.out.println("Added.");
    }

    static void addShirt(Scanner sc) {
        System.out.println("\nAdd Shirt:");
        int id = askInt(sc, "id: ");
        String name = askLine(sc, "name: ");
        String brand = askLine(sc, "brand: ");
        double price = askDouble(sc, "price: ");
        int stock = askInt(sc, "stock: ");
        String sleeve = askLine(sc, "sleeve (short/long): ");

        ClothingItem item = new Shirt(id, name, brand, price, stock, sleeve); // stored as parent
        items.add(item);
        System.out.println("Added.");
    }

    static void addJacket(Scanner sc) {
        System.out.println("\nAdd Jacket:");
        int id = askInt(sc, "id: ");
        String name = askLine(sc, "name: ");
        String brand = askLine(sc, "brand: ");
        double price = askDouble(sc, "price: ");
        int stock = askInt(sc, "stock: ");
        boolean waterproof = askYesNo(sc, "waterproof? (y/n): ");

        ClothingItem item = new Jacket(id, name, brand, price, stock, waterproof); // stored as parent
        items.add(item);
        System.out.println("Added.");
    }

    static void viewAll() {
        System.out.println("\n--- ALL ITEMS ---");
        if (items.isEmpty()) {
            System.out.println("Empty.");
            return;
        }
        for (ClothingItem it : items) {
            System.out.println(it); // polymorphic toString -> getType()
        }
    }

    static void demoWear() {
        System.out.println("\n--- wear() demo ---");
        for (ClothingItem it : items) {
            it.wear(); // polymorphism (override)
        }
    }

    static void viewShirtsOnly() {
        System.out.println("\n--- SHIRTS ONLY ---");
        for (ClothingItem it : items) {
            if (it instanceof Shirt) {
                Shirt s = (Shirt) it; // casting
                System.out.println(s);
                s.fold(); // unique method
            }
        }
    }

    static void viewJacketsOnly() {
        System.out.println("\n--- JACKETS ONLY ---");
        for (ClothingItem it : items) {
            if (it instanceof Jacket) {
                Jacket j = (Jacket) it; // casting
                System.out.println(j);
                j.zip(); // unique method
            }
        }
    }

    // -------- small input helpers --------
    static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Enter number: ");
        }
        int x = sc.nextInt();
        sc.nextLine();
        return x;
    }

    static int askInt(Scanner sc, String msg) {
        System.out.print(msg);
        return readInt(sc);
    }

    static double askDouble(Scanner sc, String msg) {
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
            sc.nextLine();
            System.out.print("Enter number: ");
        }
        double x = sc.nextDouble();
        sc.nextLine();
        return x;
    }

    static String askLine(Scanner sc, String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    static boolean askYesNo(Scanner sc, String msg) {
        System.out.print(msg);
        String s = sc.nextLine().trim().toLowerCase();
        return s.startsWith("y");
    }
}
