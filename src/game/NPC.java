package game;

public abstract class NPC {
	public final int HP;
	public final int ATT;
	public final int DEF;
	protected String description;
	protected Key key;
	protected int gold;
	
	public static final int DEFAULT_HP = 100;
	public static final int DEFAULT_ATT = 10;
	public static final int DEFAULT_DEF = 5;
	public static final int DEFAULT_GOLD = 3;
	
	public NPC(String desc) {
		this.HP = DEFAULT_HP;
		this.ATT = DEFAULT_ATT;
		this.DEF = DEFAULT_DEF;
		this.description = desc;
		
	}
	
	public abstract void attack();
	
	public abstract void interact();
	
	public abstract void print_description();
	
}
