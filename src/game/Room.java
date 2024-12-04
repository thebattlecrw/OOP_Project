package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private final String name;
    private final String description;
    private final List<NPC> npcs;
    private final Map<String, Room> exits;
    private final Map<String, Item> items;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.npcs = new ArrayList<>();
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    // --- Methods to manage items in the room ---

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public List<NPC> getNPCs() {
        return npcs;
    }

    // Add an item to the room
    public void addItem(Item item) {
        items.put(item.NAME, item);  // Add the item using its name as the key
    }

    // Retrieve an item by its name
    public Item getItem(String itemName) {
        return items.get(itemName);  // Return the item matching the name or null if not found
    }

    // Remove an item from the room by its name
    public void removeItem(String itemName) {
        if (items.containsKey(itemName)) {
            items.remove(itemName);  // Remove the item from the room
        } else {
            System.out.println("This item does not exist in this room.");
        }
    }

    // Show the items present in the room
    public void showItems() {
        if (items.isEmpty()) {
            System.out.println("There are no items in this room.");
        } else {
            System.out.println("Items present in the room:");
            for (Item item : items.values()) {
                System.out.println("- " + item.NAME);
            }
        }
    }
}
