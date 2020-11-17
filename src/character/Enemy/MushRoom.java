package character.Enemy;

import java.util.concurrent.CopyOnWriteArrayList;
import character.Character;

public class MushRoom extends Character {

	public MushRoom(int x, int y, int speed) {
		super(x, y, speed);
	}

	private int direction;

	// In pratica la classe si trasforma in thread-safe al momento dell’accesso in
	// scrittura (implementazione di un ArrayList sincronizzato);
	public CopyOnWriteArrayList<MushRoom> listMushRoom = new CopyOnWriteArrayList<MushRoom>();

	private static MushRoom m = null;

	public static MushRoom getInstance() {
		if (m == null)
			m = new MushRoom(0, 0, 0);
		return m;
	}

	public final static int MOVE_LEFT = 0;
	public final static int MOVE_RIGHT = 1;

	public void addMushroom(MushRoom e) {
		listMushRoom.add(e);
	}

	public void remove(int x, int y) {
		for (MushRoom i : listMushRoom)
			if (i.getX() == x && i.getY() == y)
				listMushRoom.remove(i);
		
	}

	public void setDirection(int d) {
		direction = d;
	}

	public int getDirection() {
		return direction;
	}
}