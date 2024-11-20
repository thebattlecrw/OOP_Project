package game;

public abstract class Item {
	public final String NAME;
	public final int VOLUME;
	
	public Item(String name, int vol){
		this.NAME = name;
		this.VOLUME = vol;
	}
	
	// public abstract void use();
}
