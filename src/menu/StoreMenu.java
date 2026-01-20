package menu;

import assignment2.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreMenu implements Menu {

    // Tools we need: Scanner for input, ArrayList to save items
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<ClothingItem> items = new ArrayList<>();

    public StoreMenu() {
        // Adding some starting items so the list isn't empty
        items.add(new Shirt(1, "Nike Polo", 15000, "Short"));
        items.add(new Jacket(2, "Winter Coat", 50000, true));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== CLOTHING STORE MENU ===");
        System.out.println("1. Add Shirt");
        System.out.println("2. Add Jacket");
        System.out.println("3. View All Items");
        System.out.println("0. Exit");
    }

    @Override
    public void run() {
        boolean running = true;

        // Loop: Keep showing the menu until user wants to exit
        while (running) {
            displayMenu();
            System.out.print("Enter choice: ");

            // TRY-CATCH: Prevents crash if user types letters (ABC) instead of numbers
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // IMPORTANT: Consumes the "Enter" key press

                switch (choice) {
                    case 1:
                        addShirt();
                        break;
                    case 2:
                        addJacket();
                        break;
                    case 3:
                        viewAll();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                // If user enters bad input, we catch the error here
                System.out.println("Error: Please enter a valid number!");
                scanner.nextLine(); // Clear the bad input from scanner memory
            }
        }
    }

    private void addShirt() {
        try {
            System.out.println("\n--- Add Shirt ---");

            // Taking inputs from user
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Fix scanner bug

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Sleeve: ");
            String sleeve = scanner.nextLine();

            // Creating the Shirt object and saving it to the list
            items.add(new Shirt(id, name, price, sleeve));
            System.out.println("Shirt added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void addJacket() {
        try {
            System.out.println("\n--- Add Jacket ---");

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = scanner.nextDouble();

            System.out.print("Waterproof (true/false): ");
            boolean waterproof = scanner.nextBoolean();

            // Creating the Jacket object
            items.add(new Jacket(id, name, price, waterproof));
            System.out.println("Jacket added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void viewAll() {
        System.out.println("\n--- All Items ---");
        // Loop through the list and print each item
        for (ClothingItem item : items) {
            System.out.println(item); // Uses the toString() method of the item
        }
    }
}