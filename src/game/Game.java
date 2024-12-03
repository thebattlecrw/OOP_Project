package game;

import java.util.Scanner;

public class Game {

    private Room currentRoom;
    private final Hero hero;

    //Sinon erreur à go() ---- On met toutes les rooms ici ?
    private final Room couloir2 = new Room("Couloir du 2ème étage", "Un couloir tout à fait banal. Il mène aux pièces «201» à «206», ainsi qu'aux couloirs du 1er «couloir1» et 3ème «couloir3» étage.");

    public Game() {

        // Initialisation des salles
        Room couloir3 = new Room("Couloir du 3ème étage", "Un couloir tout à fait banal. Il mène aux pièces «301» à «306», ainsi qu'au couloir du 2ème étage «couloir2».");

        Room room301 = new Room("Salle 301", "C'est votre chambre. Il n'y a qu'une porte, qui mène au couloir du 3ème étage «couloir3».");
        Room room302 = new Room("Salle 302", "Une salle où un échange de tirs a eu lieu.");
        Room room303 = new Room("Salle 303", "Une autre salle avec un trou.");
        Room room304 = new Room("Salle 304", "Une salle où un échange de tirs a eu lieu.");
        Room room305 = new Room("Salle 305 - Cabinet du Dr. Hou", "Une autre salle vide. Une inscription à moitié effacée, ou simplement mal écrite, est sur le tableau : «H LP 101 3__4");
        Room room306 = new Room("Salle 306", "Une pièce avec un sergent agonisant. «S'il te plaît, dis moi que mon chef de compagnie va bien. Il allait se faire vacciner en salle 204.»");

        Room room201 = new Room("Salle 201", "Une chambre qui semble vide au premier regard.");
        Room room202 = new Room("Salle 202", "Une salle vide.");
        Room room203 = new Room("Salle 203", "Une salle avec une télé.");
        Room room204 = new Room("Salle 204", "Une salle avec un brancard qui bloque la porte. Vous y trouvez le corps de l'adjudant TIFRICE, à moitié zombifié...");
        Room room205 = new Room("Salle 205", "Une salle sans vie, avec une forte odeur de putréfaction. Il y a un trou dans un mur.");
        Room room206 = new Room("Salle 206", "Une salle avec une télé.");

        // Relier les salles entre elles
        couloir3.setExit("301", room301);
        couloir3.setExit("302", room302);
        couloir3.setExit("303", room303);
        couloir3.setExit("304", room304);
        couloir3.setExit("305", room305);
        couloir3.setExit("306", room306);
        couloir3.setExit("couloir2", couloir2);

        room301.setExit("couloir3", couloir3);
        room302.setExit("couloir3", couloir3);
        room303.setExit("couloir3", couloir3);
        room304.setExit("couloir3", couloir3);
        room305.setExit("couloir3", couloir3);
        room306.setExit("couloir3", couloir3);

        couloir2.setExit("couloir3", couloir3);
        couloir2.setExit("201", room201);
        couloir2.setExit("202", room202);
        couloir2.setExit("203", room203);
        couloir2.setExit("205", room205);
        couloir2.setExit("206", room206);

        room201.setExit("couloir2", couloir2);
        room202.setExit("couloir2", couloir2);
        room203.setExit("couloir2", couloir2);
        room204.setExit("205", room205);
        room205.setExit("couloir2", couloir2);
        room205.setExit("204", room204);
        room206.setExit("couloir2", couloir2);

        // Ajouter les munitions
        Ammo ammo = new Ammo("Ammo");
        room302.addItem(ammo);
        room304.addItem(ammo);

        // Initialiser le héros
        hero = new Hero();

        // Commencer le jeu dans la salle de départ
        currentRoom = room301;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean gameRunning = true;

        System.out.println(""" 
                _____________________________________________________________________________________________________________________________________________________________
                Vous vous réveillez dans un hôpital militaire. Alors que vous ne deviez venir que pour un banal rappel de vaccin, le scénario tourne au cauchemar...
                Le dit vaccin a été remplacé par un poison transformant les patients en zombis. Heureusement, votre corps n'a pas réagi comme attendu, mais ce n'est pas le
                cas pour le reste du régiment... À vous de trouver une solution pour sortir d'ici, mais attention, certains ont déjà entammé le processus de transformation !
                _____________________________________________________________________________________________________________________________________________________________""");

        while (gameRunning) {
            System.out.println("\n\n[ " + currentRoom.getName() + " ]");
            System.out.println(currentRoom.getDescription());
            System.out.println("Que voulez-vous faire ? (HELP pour la liste des commandes.)");
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

        // Vérifier si le joueur est dans le couloir2 et tente d'aller dans la salle 204
        if (currentRoom == couloir2 && direction.equals("204")) {
            System.out.println("La porte semble bloquée de l'intérieur.");
        } else {
            if (nextRoom != null) {
                currentRoom = nextRoom;  // Déplacer le joueur dans la salle suivante
                System.out.println("Vous vous déplacez vers : " + nextRoom.getName());
            } else {
                System.out.println("Vous faites face à un mur.");
            }
        }
    }

    public void help() {
        System.out.println("Commandes disponibles :");
        System.out.println("GO [direction] - Se déplacer dans la direction spécifiée (ex: GO east).");
        System.out.println("HELP - Afficher cette liste de commandes.");
        System.out.println("LOOK - Regarder autour de vous (affiche la description de la salle et les objets présents).");
        System.out.println("TAKE [item] - Prendre un item de la salle (ex: TAKE Key).");
        System.out.println("INVENTORY - Afficher les objets dans votre inventaire.");
        System.out.println("QUIT - Quitter le jeu.");
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
