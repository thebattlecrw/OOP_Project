package game;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    // Liste pour stocker les items dans l'inventaire
    public List<Item> items;

    // Constructeur de la classe
    public Backpack() {
        this.items = new ArrayList<>();
    }

    // Ajouter un item au backpack
    public void addItem(Item item) {
        items.add(item);
    }

    // Afficher les items du backpack
    public void showItems() {
        int vol = 0;
        for (Item item : items) {
            vol = vol + item.VOLUME;
        }
        // Capacité maximale de l'inventaire (par exemple 10 objets, ne sera jamais dépassé)
        int MAX_CAPACITY = 10;
        System.out.println("Volume : " + vol + " / " + MAX_CAPACITY);
        if (items.isEmpty()) {
            System.out.println("Your backpack is empty.");
        } else {
            System.out.println("Item(s) in your backpack :");
            for (Item item : items) {
                System.out.println("- " + item.NAME);
            }
        }
    }
}