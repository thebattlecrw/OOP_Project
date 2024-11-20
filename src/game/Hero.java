public class Hero {
    private int gold;

    public Hero() {
        this.gold = 0;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }
}
