package game;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    // List to store items in the inventory
    public List<Item> items;

    // Maximum capacity of the inventory (e.g., 10 items)
    private final int MAX_CAPACITY = 10;

    // Constructor for the class
    public Backpack() {
        this.items = new ArrayList<>();
    }

    // Add an item to the backpack
    public void addItem(Item item) {
        items.add(item);
    }

    // Remove an item from the backpack
    public void removeItem(Item item) {
        items.remove(item);
    }

    // Show the items in the backpack
    public void showItems() {
        int volume = 0;
        for (Item item : items) {
            volume = volume + item.VOLUME;
        }
        System.out.println("Volume: " + volume + " / " + MAX_CAPACITY);
        if (items.isEmpty()) {
            System.out.println("Your backpack is empty.");
        } else {
            System.out.println("Item(s) in your backpack:");
            for (Item item : items) {
                System.out.println("- " + item.NAME);
            }
        }
    }
}
