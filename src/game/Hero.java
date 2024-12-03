package game;

public class Hero {

    // --- Attributs. ---
    private String name;
    public Backpack backpack;  // Remplacer la liste d'items par un Backpack

    // --- Getters. ---
    public String getName() {
        return name;
    }

    // --- Setters. ---
    public void setName(String name) {
        this.name = name;
    }

    // --- Constructeur. ---
    public Hero() {
        this.name = "Generic";
        this.backpack = new Backpack();  // Initialisation du sac à dos
    }

    // --- Méthodes. ---

    // Ajouter un item au backpack
    public void addItem(Item item) {
        backpack.addItem(item);
    }

    // Afficher l'inventaire (les items dans le backpack)
    public void showInventory() {
        backpack.showItems();
    }
}
