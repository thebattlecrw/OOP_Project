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

        // Ajouter un NPC/Item dans certaines salles

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
            System.out.println("Vous êtes dans " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            System.out.println("Que voulez-vous faire? (GO [direction], QUIT)");
            input = scanner.nextLine();

            if (input.startsWith("GO ")) {
                String direction = input.substring(3);
                go(direction);
            } else if (input.equals("QUIT")) {
                gameRunning = false;
            } else {
                System.out.println("Commande inconnue.");
            }
        }

        System.out.println("Fin du jeu.");
        scanner.close();
    }

    private void go(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
        } else {
            System.out.println("Vous faites face à un mur.");
        }
    }
}
