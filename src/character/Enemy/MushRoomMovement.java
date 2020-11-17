package character.Enemy;

import character.Mario.Mario;
import logic.Game;
import model.Block;
import utils.Settings;

public class MushRoomMovement {

	private MushRoom tmp;

	public MushRoomMovement(MushRoom tmp) {
		this.tmp = tmp;
	}

	private int newX() {
		if (tmp.getX() > 0 && tmp.getDirection() == MushRoom.MOVE_LEFT)
			return tmp.getX() - 1;

		else if (tmp.getX() < Settings.nCells - 1 && tmp.getDirection() == MushRoom.MOVE_RIGHT)
			return tmp.getX() + 1;

		else if (tmp.getX() == 0)
			tmp.setDirection(MushRoom.MOVE_RIGHT);

		else if (tmp.getX() == Settings.nCells - 1)
			tmp.setDirection(MushRoom.MOVE_LEFT);

		return tmp.getX();
	}

	private Block[][] blocks = Game.getBlocks();

	public void movementMushRooms() {
		if (!Game.getInstance().collisionWall(newX(), tmp.getY())) {
			if (tmp.getDirection() == MushRoom.MOVE_LEFT
					// la seconda condizione(riga 39) mi serve per capire se nella colonna
					// precedente, riga in basso ho il vuoto o un blocco
					&& blocks[tmp.getX() - tmp.getSpeed()][tmp.getY() + tmp.getSpeed()].getType() != Block.EMPTY) {
				// se ho il blocco sotto i piedi posso muovermi
				if (blocks[tmp.getX()][tmp.getY()].getType() != Block.MARIO
						&& !Game.getInstance().collisionCoin(tmp.getX() - tmp.getSpeed(), tmp.getY()))
					blocks[tmp.getX()][tmp.getY()].setType(Block.EMPTY);

				if (Game.getInstance().collisionCoin(tmp.getX() - tmp.getSpeed(), tmp.getY()))
					blocks[tmp.getX()][tmp.getY()].setType(Block.COIN);

				tmp.setX(newX());
				blocks[tmp.getX()][tmp.getY()].setType(Block.ENEMY);
			}

			else // se non ho il blocco sotto i piedi
			{
				if ((tmp.getX() - tmp.getSpeed()) > 0
						&& (blocks[tmp.getX() - tmp.getSpeed()][tmp.getY() + tmp.getSpeed()].getType() == Block.EMPTY))
					// se nella colonna precedente, riga sotto c'e' vuoto allora mi giro
					tmp.setDirection(MushRoom.MOVE_RIGHT);
			}

			// colonna //riga
			if (tmp.getDirection() == MushRoom.MOVE_RIGHT
					&& blocks[tmp.getX() + tmp.getSpeed()][tmp.getY() + tmp.getSpeed()].getType() != Block.EMPTY) {
				if (blocks[tmp.getX()][tmp.getY()].getType() != Block.MARIO
						&& !Game.getInstance().collisionCoin(tmp.getX() + tmp.getSpeed(), tmp.getY()))
					blocks[tmp.getX()][tmp.getY()].setType(Block.EMPTY);

				if (Game.getInstance().collisionCoin(tmp.getX() + tmp.getSpeed(), tmp.getY()))
					blocks[tmp.getX()][tmp.getY()].setType(Block.COIN);

				tmp.setX(newX());
				blocks[tmp.getX()][tmp.getY()].setType(Block.ENEMY);
			}

			else {
				if (tmp.getX() + tmp.getSpeed() < Settings.nCells - 1
						&& blocks[tmp.getX() + tmp.getSpeed()][tmp.getY() + tmp.getSpeed()].getType() == Block.EMPTY)
					// se nella colonna successiva, riga sotto c'e' vuoto allora mi giro
					tmp.setDirection(MushRoom.MOVE_LEFT);
			}

			if (Mario.getInstance().getX() == tmp.getX() && Mario.getInstance().getY() == tmp.getY()) {
				// ho collisione con mario e gli tolgo una vita
				Mario.getInstance().minusLife();
				blocks[tmp.getX()][tmp.getY()].setType(Block.MARIO);
			}

		}

		else {
			if (tmp.getDirection() == MushRoom.MOVE_LEFT)
				// voltati a destra e continua a muoverti
				tmp.setDirection(MushRoom.MOVE_RIGHT);
			else
				// voltati a destra e continua a muoverti
				tmp.setDirection(MushRoom.MOVE_LEFT);
		}

	}
}
