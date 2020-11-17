package model;

public class Block {
	public final static int EMPTY = 0;
	public final static int MARIO = 1;
	public final static int STOPBLOCK = 2;
	public final static int STAR = 3;
	public final static int ENEMY = 4;
	public final static int COIN = 5;
	public final static int LANDBLOCK = 6;
	private int type;

	public Block(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
