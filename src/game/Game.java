package game;

import java.util.Scanner;
import java.util.Iterator;


public class Game {

    private Room currentRoom;
    private final Hero hero;
    private boolean hasVisited2 = false;
    private int count = -1;

    // Otherwise, an error in go() ---- Should we declare all rooms here?
    private final Room hall1 = new Room("Hall1", "A completely ordinary hallway. It leads to rooms '101' to '106', as well as to the hallway on the second floor 'hall2' and the exit 'exit'.");
    private final Room hall2 = new Room("Second Floor Hallway", "A completely ordinary hallway. It leads to rooms '201' to '206', as well as to the hallways on the first 'hall1' and third 'hall3' floors.");
    private final Room exit = new Room("Exit", "The exit of the hospital.");

    public Game() {

        // Room initialization

        Room room101 = new Room("Room 101", "A completely ordinary room.");
        Room room102 = new Room("Room 102", "An empty room.");
        Room room103 = new Room("Room 103", "A room full of debris.");
        Room room104 = new Room("Room 104", "A completely ordinary room.");
        Room room105 = new Room("Room 105", "An empty room with blood on the floor.");
        Room room106 = new Room("Room 106", "A room with a TV.");

        Room room201 = new Room("Room 201", "A room that seems empty at first glance.");
        Room room202 = new Room("Room 202", "An empty room.");
        Room room203 = new Room("Room 203", "A room with a TV.");
        Room room204 = new Room("Room 204", "A room with a stretcher blocking the door. Inside, you find the surgeon... Talk to him !");
        Room room205 = new Room("Room 205", "A lifeless room with a strong stench of decay. There's a hole in one of the walls.");
        Room room206 = new Room("Room 206", "A room with a TV.");

        Room room301 = new Room("Room 301", "This is your room. There’s only one door, which leads to the third-floor hallway 'Hall3'.");
        Room room302 = new Room("Room 302", "A room where a shootout occurred.");
        Room room303 = new Room("Room 303", "Another room with a hole.");
        Room room304 = new Room("Room 304", "A room where a shootout occurred.");
        Room room305 = new Room("Room 305 - Surgeon's Office", "Another empty room. A poorly written inscription is on the board: 'HELP 205->204'.");
        Room room306 = new Room("Room 306", "A room with a dying sergeant. 'Please, tell me my company commander is okay. He went to get vaccinated in room 105.'");

        Room hall3 = new Room("Third Floor Hallway", "A completely ordinary hallway. It leads to rooms '301' to '306', as well as to the hallway on the second floor 'hall2'.");

        // Link the rooms together
        room101.setExit("hall1", hall1);
        room102.setExit("hall1", hall1);
        room103.setExit("hall1", hall1);
        room104.setExit("hall1", hall1);
        room105.setExit("hall1", hall1);
        room106.setExit("hall1", hall1);

        room201.setExit("hall2", this.hall2);
        room202.setExit("hall2", this.hall2);
        room203.setExit("hall2", this.hall2);
        room204.setExit("205", room205);
        room205.setExit("hall2", this.hall2);
        room205.setExit("204", room204);
        room206.setExit("hall2", this.hall2);

        room301.setExit("hall3", hall3);
        room302.setExit("hall3", hall3);
        room303.setExit("hall3", hall3);
        room304.setExit("hall3", hall3);
        room305.setExit("hall3", hall3);
        room306.setExit("hall3", hall3);

        hall1.setExit("101", room101);
        hall1.setExit("102", room102);
        hall1.setExit("103", room103);
        hall1.setExit("104", room104);
        hall1.setExit("105", room105);
        hall1.setExit("106", room106);
        hall1.setExit("hall2", this.hall2);
        hall1.setExit("exit", exit);

        this.hall2.setExit("hall1", hall1);
        this.hall2.setExit("hall3", hall3);
        this.hall2.setExit("201", room201);
        this.hall2.setExit("202", room202);
        this.hall2.setExit("203", room203);
        this.hall2.setExit("205", room205);
        this.hall2.setExit("206", room206);

        hall3.setExit("301", room301);
        hall3.setExit("302", room302);
        hall3.setExit("303", room303);
        hall3.setExit("304", room304);
        hall3.setExit("305", room305);
        hall3.setExit("306", room306);
        hall3.setExit("hall2", this.hall2);

        // Add ammunition
        Surgeon surgeon = new Surgeon("Doc");
        Ammo ammo = new Ammo("Ammo");
        Gun gun = new Gun("Gun");
        room301.addItem(gun);
        room302.addItem(ammo);
        room304.addItem(ammo);
        room103.addItem(ammo);
        room204.addNPC(surgeon);

        // Initialize the hero
        hero = new Hero();
        hero.setName("Player");

        // Start the game in the initial room
        currentRoom = room301;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean gameRunning = true;

        System.out.println("""
                ______________________________________________________________________________________________________________
                You wake up in a military hospital. What should have been a routine vaccination turns into a nightmare...
                The vaccine was replaced with a poison that turns patients into zombies. Fortunately, your body didn’t react
                as expected... You need to find a way out, but beware, some are already in the process of transforming!
                ______________________________________________________________________________________________________________""");

        while (gameRunning) {
            count = count + 1;
            System.out.println("\n\n[ " + currentRoom.getName() + " ]");
            System.out.println(currentRoom.getDescription());
            currentRoom.printExits();
            if ((currentRoom == hall2 && !hasVisited2) || (currentRoom == exit)) {
                fightZombie();
                hasVisited2 = true;
            }
            System.out.println("What would you like to do? (Type HELP for a list of commands.)");
            input = scanner.nextLine();

            if (input.startsWith("GO ")) {
                String direction = input.substring(3);
                go(direction);
            } else if (input.equals("HELP")) {
                help();  // Show the list of commands
            } else if (input.equals("LOOK")) {
                look();  // Show the room's description and items
            } else if (input.startsWith("TAKE ")) {
                String itemName = input.substring(5);  // Extract the item name
                take(itemName);  // Take the item
            } else if (input.equals("INVENTORY")) {
                inventory();  // Show the hero's inventory
            } else if (input.equals("TALK")) {
                talk();  // Talk to the Surgeon to get a key
            } else if (input.equals("QUIT")) {
                gameRunning = false;
            } else {
                System.out.println("Unknown command.");
            }
        }

        System.out.println("Game over.");
        scanner.close();
    }

    // In-game commands
    private void go(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        boolean hasKey = hero.backpack.items.stream().anyMatch(item -> item instanceof Key);

        if (currentRoom == hall2 && direction.equals("204")) {
            // Specific case: The door to room 204 is blocked
            System.out.println("The door seems blocked from the inside.");
        } else if (currentRoom == hall1 && direction.equals("exit")) {
            // Specific case: Attempting to exit via the exit door
            if (hasKey) {
                if (nextRoom != null) {
                    currentRoom = nextRoom;  // Move to the next room
                    System.out.println("You use the key and move to: " + nextRoom.getName());
                } else {
                    System.out.println("The exit doesn't seem accessible.");
                }
            } else {
                System.out.println("You need a key to open this door!");
            }
        } else if (nextRoom != null) {
            // General case: Valid movement
            currentRoom = nextRoom;
            System.out.println("You move to: " + nextRoom.getName());
        } else {
            // General case: Invalid movement
            System.out.println("You face a wall.");
        }
    }


    public void help() {
        System.out.println("Available commands:");
        System.out.println("GO [direction] - Move in the specified direction (e.g., GO east).");
        System.out.println("HELP - Display this list of commands.");
        System.out.println("LOOK - Look around (displays the room description and items present).");
        System.out.println("TAKE [item] - Pick up an item in the room (e.g., TAKE Key).");
        System.out.println("INVENTORY - Show items in your inventory.");
        System.out.println("TALK - Talk to an NPC.");
        System.out.println("QUIT - Quit the game.");
    }

    public void look() {
        currentRoom.showItems();  // Display the items in the room
    }

    public void take(String itemName) {
        Item item = currentRoom.getItem(itemName);  // Get the item by its name from the room
        if (item != null) {
            hero.addItem(item);  // Add the item to the hero’s inventory
            currentRoom.removeItem(itemName);  // Remove the item from the room
            System.out.println("You took the item: " + item.NAME);
        } else {
            System.out.println("This item is not present in the room.");
        }
    }

    public void inventory() {
        hero.showInventory();  // Display the hero's inventory
    }

    public void fightZombie() {
        int requiredAmmo = currentRoom == hall2 ? 1 : 2;
        Scanner scanner = new Scanner(System.in);

        System.out.println("A zombie attacks you! He needs " + requiredAmmo + " bullets to be killed. Would you attack it? [YES/NO]");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("YES")) {

            long ammoCount = hero.backpack.items.stream()
                    .filter(item -> item instanceof Ammo)
                    .count();

            boolean hasGun = hero.backpack.items.stream()
                    .anyMatch(item -> item instanceof Gun);

            if (ammoCount >= requiredAmmo && hasGun) {
                System.out.println("Congratulations, you just killed the zombie with " + requiredAmmo + " bullet(s).");
                System.out.println("See by yourself -> https://youtu.be/TBK7Tr-V3fg?si=ZWEUxcAj8ZJ6rCTe&t=19");

                if (currentRoom == exit) {
                    System.out.println("Congratulations, you finished the game within " + count + " moves !");
                    System.exit(0);
                }

                // Remove used muns from backpack
                int removedAmmo = 0;
                Iterator<Item> iterator = hero.backpack.items.iterator();
                while (iterator.hasNext() && removedAmmo < requiredAmmo) {
                    Item item = iterator.next();
                    if (item instanceof Ammo) {
                        iterator.remove();
                        removedAmmo++;
                    }
                }

                System.out.println("You now have " + (ammoCount - requiredAmmo) + " bullet(s) remaining.");
            } else if (ammoCount >= requiredAmmo && !hasGun) {
                System.out.println("You have enough bullet(s), but no weapon !");
                System.out.println("The zombie killed you. Game over.");
                System.exit(0);
            } else {
                System.out.println("Not enough ammo. You have " + ammoCount + " ammo and the zombie requires " + requiredAmmo + " bullet(s) to be killed.");
                System.out.println("The zombie killed you. Game over.");
                System.exit(0);
            }
        } else {
            System.out.println("You cannot flee from a zombie! He killed you. Game over.");
            System.exit(0);
        }
    }

    public void talk() {
        boolean hasKey = hero.backpack.items.stream().anyMatch(item -> item instanceof Key);
        if (!hasKey) {
            for (NPC npc : currentRoom.getNPCs()) {
                if (npc instanceof Surgeon) {
                    System.out.println("The surgeon hands you a key, whispering: \"Take this and leave quickly...\"");
                    Key key = new Key("Surgeon's Key");
                    hero.addItem(key);
                    break;
                }
            } System.out.println("There is no one to talk to here.");
        } else System.out.println("You already collected the surgeon's key.");
    }
}
