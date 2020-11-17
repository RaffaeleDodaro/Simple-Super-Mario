package character;

public class Character {
	private int x = 0;
	private int y = 0;
	private int speed;
	
	public Character(int x, int y, int speed) {
		super();
		this.setX(x);
		this.setY(y);
		this.setSpeed(speed);
	}

	public int getY() {
		return x;
	}

	public void setY(int y) {
		this.x = y;
	}

	public int getX() {
		return y;
	}

	public void setX(int x) {
		this.y = x;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}	
}
