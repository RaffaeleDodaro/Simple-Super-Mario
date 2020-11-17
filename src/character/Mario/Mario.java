package character.Mario;

import character.Character;

public class Mario extends Character {
	
	public Mario(int x, int y, int speed) {
		super(x, y,speed);
		lifePoints = 3;
		coins = 0;
	}

	private int lifePoints;
	private int coins;

	public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int JUMP_RIGHT = 2;
	public final static int JUMP_LEFT = 3;

	private static Mario mario = null;

	public static Mario getInstance() {
		if (mario == null)
			mario = new Mario(0,0,1);
		return mario;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public void minusLife() {
		lifePoints--;
	}

	public int getCoins() {
		return coins;
	}

	public void addCoins() {
		coins += 100;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
}
