// --- Import. ---
import java.util.ArrayList;

// --- Principal class. ---
public class Hero {

    // --- Attributs. ---
    private String name;
    private int healthPoints;
    private int ammoCount;
    private List<Key> keys;

    // --- Getters. ---
    public String getName() {
        return name;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getAmmoCount() {
        return ammoCount;
    }
    public List<Key> getKeys() {
        return keys;
    }

    // --- Setters. --- 
    public void setName(String name) {
        this.name = name;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public void setAmmoCount(int ammoCount) {
        this.ammoCount = ammoCount;
    }

    // --- Constructors. ---
    public Hero(){
        this.name = "Generic";
        this.healthPoints = 100;
        this.ammoCount = 0;
        this.keys = new ArrayList<>();
    }
}