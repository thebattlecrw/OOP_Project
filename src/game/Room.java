package game;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private Map<String, Item> items;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
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

    // --- Méthodes pour gérer les items dans la salle ---

    // Ajouter un item à la salle
    public void addItem(Item item) {
        items.put(item.NAME, item);  // On ajoute l'item en utilisant son nom comme clé
    }

    // Récupérer un item par son nom
    public Item getItem(String itemName) {
        return items.get(itemName);  // Retourne l'item correspondant au nom ou null si non trouvé
    }

    // Retirer un item de la salle par son nom
    public void removeItem(String itemName) {
        if (items.containsKey(itemName)) {
            items.remove(itemName);  // Retirer l'item de la salle
        } else {
            System.out.println("Cet item n'existe pas dans cette salle.");
        }
    }

    // Afficher les items présents dans la salle
    public void showItems() {
        if (items.isEmpty()) {
            System.out.println("Il n'y a pas d'objets dans cette salle.");
        } else {
            System.out.println("Objets présents dans la salle :");
            for (Item item : items.values()) {
                System.out.println("- " + item.NAME);
            }
        }
    }
}
