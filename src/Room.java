import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private ArrayList<Pot> pots;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.pots = new ArrayList<>();
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

    public void addPot(Pot pot) {
        pots.add(pot);
    }

    public void destroyPot(Hero hero) {
        if (!pots.isEmpty()) {
            Pot pot = pots.remove(0);  // Détruire le pot
            hero.addGold(pot.getGoldAmount());  // Ajouter de l'or au héros
        }
    }
}
