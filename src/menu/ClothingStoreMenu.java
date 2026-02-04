package menu;

import database.ClothingDAO;
import model.*;
import java.util.List;
import java.util.Scanner;

public class ClothingStoreMenu {

    private ClothingDAO dao;
    private Scanner scanner;

    // Constructor
    public ClothingStoreMenu() {
        this.dao = new ClothingDAO();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addShirt();
                    break;
                case "2":
                    addJacket();
                    break;
                case "3":
                    viewAllItems();
                    break;
                case "4":
                    viewShirtsOnly();
                    break;
                case "5":
                    viewJacketsOnly();
                    break;
                case "6":
                    updateItem();
                    break;
                case "7":
                    deleteItem();
                    break;
                case "8":
                    searchByName();
                    break;
                case "9":
                    searchByPriceRange();
                    break;
                case "10":
                    searchByMinPrice();
                    break;
                case "11":
                    polymorphismDemo();
                    break;
                case "0":
                    System.out.println("\nThank you for using Clothing Store System!");
                    System.out.println("Goodbye! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select 0-11.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("     CLOTHING STORE MANAGEMENT SYSTEM   ");
        System.out.println("==== ITEM MANAGEMENT ====");
        System.out.println("1. Add Shirt");
        System.out.println("2. Add Jacket");
        System.out.println("3. View All Items");
        System.out.println("4. View Shirts Only");
        System.out.println("5. View Jackets Only");
        System.out.println("6. Update Item");
        System.out.println("7. Delete Item");
        System.out.println("==== SEARCH & FILTER ====");
        System.out.println("8. Search by Name");
        System.out.println("9. Search by Price Range");
        System.out.println("10. High-Priced Items (Price >= X)");
        System.out.println("==== DEMO ====");
        System.out.println("11. Polymorphism Demo");
        System.out.println("0. Exit");

        System.out.print("Select an option: ");
    }

    private void addShirt() {
        System.out.println("\n=== ADD NEW SHIRT ===");

        try {
            System.out.print("Enter shirt name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price (KZT): ");
            double price = Double.parseDouble(scanner.nextLine());

            if (price < 0) {
                System.out.println("Price cannot be negative!");
                return;
            }

            System.out.print("Enter size (S/M/L/XL/XXL): ");
            String size = scanner.nextLine().toUpperCase();

            // Create new Shirt object (ID is 0 - database will generate it)
            Shirt newShirt = new Shirt(0, name, price, size);

            // Insert into database
            if (dao.insertShirt(newShirt)) {
                System.out.println("Shirt added successfully!");
            } else {
                System.out.println("Failed to add shirt.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Please enter a valid number.");
        }
    }

    private void addJacket() {
        System.out.println("\n=== ADD NEW JACKET ===");

        try {
            System.out.print("Enter jacket name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price (KZT): ");
            double price = Double.parseDouble(scanner.nextLine());

            if (price < 0) {
                System.out.println("Price cannot be negative!");
                return;
            }

            System.out.print("Enter fabric (Leather/Denim/Cotton/etc): ");
            String fabric = scanner.nextLine();

            // Create new Jacket object (ID is 0 - database will generate it)
            Jacket newJacket = new Jacket(0, name, price, fabric);

            // Insert into database
            if (dao.insertJacket(newJacket)) {
                System.out.println("Jacket added successfully!");
            } else {
                System.out.println("Failed to add jacket.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Please enter a valid number.");
        }
    }

    private void viewAllItems() {
        System.out.println("\n=== ALL CLOTHING ITEMS ===");

        List<ClothingItem> items = dao.getAllItems();

        if (items.isEmpty()) {
            System.out.println("No items in database!");
        } else {
            System.out.println("----------------------------------------");
            for (ClothingItem item : items) {
                System.out.print("ID: " + item.getId() + " | ");
                item.displayInfo();
            }
            System.out.println("----------------------------------------");
            System.out.println("Total items: " + items.size());
        }
    }

    private void viewShirtsOnly() {
        System.out.println("\n=== SHIRTS ONLY ===");

        List<Shirt> shirts = dao.getAllShirts();

        if (shirts.isEmpty()) {
            System.out.println("No shirts in database!");
        } else {
            System.out.println("----------------------------------------");
            for (Shirt shirt : shirts) {
                System.out.print("ID: " + shirt.getId() + " | ");
                shirt.displayInfo();
            }
            System.out.println("----------------------------------------");
            System.out.println("Total shirts: " + shirts.size());
        }
    }

    private void viewJacketsOnly() {
        System.out.println("\n=== JACKETS ONLY ===");

        List<Jacket> jackets = dao.getAllJackets();

        if (jackets.isEmpty()) {
            System.out.println("No jackets in database!");
        } else {
            System.out.println("----------------------------------------");
            for (Jacket jacket : jackets) {
                System.out.print("ID: " + jacket.getId() + " | ");
                jacket.displayInfo();
            }
            System.out.println("----------------------------------------");
            System.out.println("Total jackets: " + jackets.size());
        }
    }

    private void updateItem() {
        System.out.println("\n=== UPDATE ITEM ===");

        try {
            System.out.print("Enter Item ID to update: ");
            int itemId = Integer.parseInt(scanner.nextLine());

            // 1. Load current item from database
            ClothingItem existingItem = dao.getItemById(itemId);

            if (existingItem == null) {
                System.out.println("No item found with ID: " + itemId);
                return;
            }

            // 2. Display current information
            System.out.println("\n--- Current Information ---");
            existingItem.displayInfo();

            // 3. Get new values from user
            System.out.println("\n--- Enter New Values (press Enter to keep current) ---");

            System.out.print("New name [" + existingItem.getName() + "]: ");
            String newName = scanner.nextLine().trim();
            if (newName.isEmpty()) {
                newName = existingItem.getName(); // Keep current
            }

            System.out.print("New price [" + existingItem.getPrice() + "]: ");
            String priceInput = scanner.nextLine().trim();
            double newPrice;
            if (priceInput.isEmpty()) {
                newPrice = existingItem.getPrice(); // Keep current
            } else {
                newPrice = Double.parseDouble(priceInput);
                if (newPrice < 0) {
                    System.out.println("Price cannot be negative!");
                    return;
                }
            }

            // 4. Update based on item type
            if (existingItem instanceof Shirt) {
                Shirt shirt = (Shirt) existingItem;

                System.out.print("New size [" + shirt.getSize() + "]: ");
                String newSize = scanner.nextLine().trim();
                if (newSize.isEmpty()) {
                    newSize = shirt.getSize(); // Keep current
                }

                // Create updated Shirt object
                Shirt updatedShirt = new Shirt(itemId, newName, newPrice, newSize);

                if (dao.updateShirt(updatedShirt)) {
                    System.out.println("Shirt updated successfully!");
                } else {
                    System.out.println("Failed to update shirt.");
                }

            } else if (existingItem instanceof Jacket) {
                Jacket jacket = (Jacket) existingItem;

                System.out.print("New fabric [" + jacket.getFabric() + "]: ");
                String newFabric = scanner.nextLine().trim();
                if (newFabric.isEmpty()) {
                    newFabric = jacket.getFabric(); // Keep current
                }

                // Create updated Jacket object
                Jacket updatedJacket = new Jacket(itemId, newName, newPrice, newFabric);

                if (dao.updateJacket(updatedJacket)) {
                    System.out.println("Jacket updated successfully!");
                } else {
                    System.out.println("Failed to update jacket.");
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter valid numbers.");
        }
    }

    private void deleteItem() {
        System.out.println("\n=== DELETE ITEM ===");

        try {
            System.out.print("Enter Item ID to delete: ");
            int itemId = Integer.parseInt(scanner.nextLine());

            // 1. Load item from database to show what will be deleted
            ClothingItem item = dao.getItemById(itemId);

            if (item == null) {
                System.out.println("No item found with ID: " + itemId);
                return;
            }

            // 2. Display item details
            System.out.println("\n Item to be deleted:");
            item.displayInfo();

            // 3. Ask for confirmation
            System.out.print("\n Are you sure you want to delete this item? (yes/no): ");
            String confirmation = scanner.nextLine().trim();

            // 4. Delete only if user confirms
            if (confirmation.equalsIgnoreCase("yes")) {
                if (dao.deleteItem(itemId)) {
                    System.out.println("Item deleted successfully!");
                } else {
                    System.out.println("Failed to delete item.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID! Please enter a valid number.");
        }
    }

    private void searchByName() {
        System.out.println("\n=== SEARCH BY NAME ===");

        System.out.print("Enter name to search (partial match): ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Search keyword cannot be empty!");
            return;
        }

        List<ClothingItem> results = dao.searchByName(keyword);

        if (results.isEmpty()) {
            System.out.println("No items found matching '" + keyword + "'");
        } else {
            System.out.println("\n--- SEARCH RESULTS ---");
            for (ClothingItem item : results) {
                System.out.print("ID: " + item.getId() + " | ");
                item.displayInfo();
            }
            System.out.println("----------------------");
            System.out.println("Found: " + results.size() + " item(s)");
        }
    }

    private void searchByPriceRange() {
        System.out.println("\n=== SEARCH BY PRICE RANGE ===");

        try {
            System.out.print("Enter minimum price (KZT): ");
            double minPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter maximum price (KZT): ");
            double maxPrice = Double.parseDouble(scanner.nextLine());

            if (minPrice < 0 || maxPrice < 0) {
                System.out.println("Prices cannot be negative!");
                return;
            }

            if (minPrice > maxPrice) {
                System.out.println("Minimum price cannot be greater than maximum price!");
                return;
            }

            List<ClothingItem> results = dao.searchByPriceRange(minPrice, maxPrice);

            if (results.isEmpty()) {
                System.out.println("No items found in price range " + minPrice + " - " + maxPrice + " KZT");
            } else {
                System.out.println("\n--- ITEMS IN PRICE RANGE ---");
                for (ClothingItem item : results) {
                    System.out.print("ID: " + item.getId() + " | ");
                    item.displayInfo();
                }
                System.out.println("----------------------------");
                System.out.println("Found: " + results.size() + " item(s)");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Please enter valid numbers.");
        }
    }

    private void searchByMinPrice() {
        System.out.println("\n=== HIGH-PRICED ITEMS ===");

        try {
            System.out.print("Enter minimum price (KZT): ");
            double minPrice = Double.parseDouble(scanner.nextLine());

            if (minPrice < 0) {
                System.out.println("Price cannot be negative!");
                return;
            }

            List<ClothingItem> results = dao.searchByMinPrice(minPrice);

            if (results.isEmpty()) {
                System.out.println("No items found with price >= " + minPrice + " KZT");
            } else {
                System.out.println("\n--- ITEMS WITH PRICE >= " + minPrice + " KZT ---");
                for (ClothingItem item : results) {
                    System.out.print("ID: " + item.getId() + " | ");
                    item.displayInfo();
                }
                System.out.println("----------------------------");
                System.out.println("Found: " + results.size() + " item(s)");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Please enter a valid number.");
        }
    }

    private void polymorphismDemo() {
        System.out.println("\n=== POLYMORPHISM DEMO ===");

        List<ClothingItem> items = dao.getAllItems();

        if (items.isEmpty()) {
            System.out.println("No items in database for demo!");
            return;
        }

        System.out.println("\nApplying 20% discount to all items...\n");

        for (ClothingItem item : items) {
            System.out.println("Before: ");
            item.displayInfo();

            // Polymorphism: calling method through parent class reference
            item.applyDiscount(20);

            System.out.println("After discount: ");
            item.displayInfo();
            System.out.println("Category: " + item.getCategory());
            System.out.println("---");
        }

        System.out.println("Demo completed! (Note: Prices in database are NOT changed)");
    }
}