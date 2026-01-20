package assignment2;

import menu.Menu;
import menu.StoreMenu;

public class Main {

    public static void main(String[] args) {
        // 1. Polymorphism: Using Interface (Menu) to refer to the Object (StoreMenu)
        Menu app = new StoreMenu();

        // 2. Encapsulation: Starting the app. All logic is hidden inside StoreMenu.
        app.run();
    }
}