package menu;

import interfaces.Menu;
import models.*;
import exceptions.MyException;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreMenu implements Menu {
    private ArrayList<ClothingItem> items = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n===== CLOTHING STORE =====");
        System.out.println("1. Add Shirt");
        System.out.println("2. Add Jacket");
        System.out.println("3. View All Items");
        System.out.println("0. Exit");
        System.out.print("Select: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: addShirt(); break;
                    case 2: addJacket(); break;
                    case 3: viewAll(); break;
                    case 0:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default: System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please enter a number!");
                scanner.nextLine();
            }
        }
    }

    private void addShirt() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Size (S/M/L): ");
            String size = scanner.nextLine();

            Shirt s = new Shirt(id, name, price, size);
            items.add(s);
            System.out.println("Success! Discount Price (10%): " + s.calculateDiscount(10));

        } catch (MyException e) {
            System.out.println("VALIDATION ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("INPUT ERROR: Invalid format!");
            scanner.nextLine();
        }
    }

    private void addJacket() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Enter

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = scanner.nextDouble();

            System.out.print("Is Waterproof? (true/false): ");
            boolean wp = scanner.nextBoolean();

            items.add(new Jacket(id, name, price, wp));
            System.out.println("Jacket added!");

        } catch (MyException e) {
            System.out.println("VALIDATION ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("INPUT ERROR: Invalid format!");
            scanner.nextLine();
        }
    }

    private void viewAll() {
        if (items.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            System.out.println("\n--- ALL ITEMS ---");
            for (ClothingItem item : items) {
                System.out.println(item);
            }
        }
    }
}