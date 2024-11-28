package game;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    // Liste pour stocker les items dans l'inventaire
    private List<Item> items;

    // Capacité maximale de l'inventaire (par exemple 10 objets)
    private final int MAX_CAPACITY = 10;

    // Constructeur de la classe
    public Backpack() {
        this.items = new ArrayList<>();
    }
    // Ajouter un item au backpack
    public void addItem(Item item) {
        items.add(item);
    }

    // Retirer un item du backpack
    public void removeItem(Item item) {
        items.remove(item);
    }

    // Afficher les items du backpack
    public void showItems() {
        if (items.isEmpty()) {
            System.out.println("Votre sac à dos est vide.");
        } else {
            System.out.println("Objets dans votre sac à dos :");
            for (Item item : items) {
                System.out.println("- " + item.NAME);
            }
        }
    }

    // Retourner la liste des items
    public List<Item> getItems() {
        return items;
    }
}