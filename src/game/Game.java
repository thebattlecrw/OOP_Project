package game;

import java.util.Scanner;

public class Game {

    private Room currentRoom;
    private Hero hero;

    public Game() {
        // Initialisation des salles
        Room room1 = new Room("Salle de départ", "C'est la salle de départ.");
        Room room2 = new Room("Salle 2", "Une salle avec un pot.");
        Room room3 = new Room("Salle 3", "Une autre salle avec un pot.");
        Room room4 = new Room("Salle 4", "Une salle vide.");
        Room room5 = new Room("Salle 5", "Une autre salle vide.");

        // Relier les salles entre elles
        room1.setExit("east", room2);
        room2.setExit("west", room1);
        room2.setExit("east", room3);
        room3.setExit("west", room2);
        room3.setExit("east", room4);
        room4.setExit("west", room3);
        room4.setExit("east", room5);
        room5.setExit("west", room4);

        // Ajouter les munitions
        Ammo ammo = new Ammo("Ammo");
        room1.addItem(ammo);

        // Initialiser le héros
        hero = new Hero();

        // Commencer le jeu dans la salle de départ
        currentRoom = room1;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println("\nVous êtes dans " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            System.out.println("Que voulez-vous faire? (HELP pour la liste des commandes.)");
            input = scanner.nextLine();

            if (input.startsWith("GO ")) {
                String direction = input.substring(3);
                go(direction);
            } else if (input.equals("HELP")) {
                help();  // Afficher la liste des commandes
            } else if (input.equals("LOOK")) {
                look();  // Afficher la description de la salle et les items
            } else if (input.startsWith("TAKE ")) {
                String itemName = input.substring(5);  // Récupérer le nom de l'item
                take(itemName);  // Prendre l'item
            } else if (input.equals("INVENTORY")) {
                inventory();  // Afficher l'inventaire du héros
            } else if (input.equals("QUIT")) {
                gameRunning = false;
            } else {
                System.out.println("Commande inconnue.");
            }
        }

        System.out.println("Fin du jeu.");
        scanner.close();
    }


    //COMMANDES EN JEU
    private void go(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
        } else {
            System.out.println("Vous faites face à un mur.");
        }
    }

    public void help() {
        System.out.println("Commandes disponibles :");
        System.out.println("GO [direction] - Se déplacer dans la direction spécifiée (ex: GO east).");
        System.out.println("QUIT - Quitter le jeu.");
        System.out.println("HELP - Afficher cette liste de commandes.");
        System.out.println("LOOK - Regarder autour de vous (affiche la description de la salle et les objets présents).");
        System.out.println("TAKE [item] - Prendre un item de la salle (ex: TAKE Key).");
        System.out.println("INVENTORY - Afficher les objets dans votre inventaire.");
    }

    public void look() {
        currentRoom.showItems();  // Afficher les objets dans la salle
    }

    public void take(String itemName) {
        Item item = currentRoom.getItem(itemName);  // Récupérer l'item par son nom dans la salle
        if (item != null) {
            hero.addItem(item);  // Ajouter l'item à l'inventaire du héros
            currentRoom.removeItem(itemName);  // Retirer l'item de la salle
            System.out.println("Vous avez pris l'item : " + item.NAME);
        } else {
            System.out.println("Cet item n'est pas présent dans cette salle.");
        }
    }

    public void inventory() {
        hero.showInventory();  // Affiche l'inventaire du héros
    }
}
