package game;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    // --- Attributes. ---
    private String name;
    private final List<Key> keys;
    public Backpack backpack;  // Replace the list of items with a Backpack

    // --- Getters. ---
    public String getName() {
        return name;
    }
    public List<Key> getKeys() {
        return keys;
    }
    public Backpack getBackpack() {
        return backpack;  // Return the Backpack object
    }

    // --- Setters. ---
    public void setName(String name) {
        this.name = name;
    }

    // --- Constructor. ---
    public Hero() {
        this.name = "Generic";
        this.keys = new ArrayList<>();
        this.backpack = new Backpack();  // Initialize the backpack
    }

    // --- Methods. ---

    // Add an item to the backpack
    public void addItem(Item item) {
        backpack.addItem(item);
    }

    // Remove an item from the backpack
    public void removeItem(Item item) {
        backpack.removeItem(item);
    }

    // Show the inventory (the items in the backpack)
    public void showInventory() {
        backpack.showItems();
    }
}
