package character.Mario;

import java.util.Timer;
import java.util.TimerTask;
import character.Enemy.MushRoom;
import logic.Game;
import model.Block;
import utils.Settings;
import view.GraphicPanel;
import view.MarioView;

public class MarioMovement {
	private Mario mario;
	private Game game = Game.getInstance();
	private GraphicPanel gp = new GraphicPanel(game);
	private Block[][] blocks = Game.getBlocks();

	private static MarioMovement mm = null;

	public static MarioMovement getInstance() {
		if (mm == null)
			mm = new MarioMovement();

		return mm;
	}

	public MarioMovement() {
		this.mario = Mario.getInstance();
		this.game = Game.getInstance();
	}

	public void move(int direction) {
		if (!game.collisionWall(newX(direction), mario.getY())) {
			blocks[mario.getX()][mario.getY()].setType(Block.EMPTY);

			mario.setX(newX(direction));

			if (game.collisionCoin(mario.getX(), mario.getY()))
				mario.addCoins();

			if (collisionEnemy(mario.getX(), mario.getY()))
				mario.minusLife();

			blocks[mario.getX()][mario.getY()].setType(Block.MARIO);
		}

		if (!game.collisionWall(mario.getX(), mario.getY() + mario.getSpeed()))// se premo freccia a destra e sotto i
																				// piedi di mario non ci sono blocchi...

		{
			falling = true;
			new Timer().schedule(

					new TimerTask() {
						@Override
						public void run() {
							fall();
						}
					}, 300);
		}
		falling = false;
	}

	private boolean collisionEnemy(int col, int row) {
		if (!falling) // se non sto cadendo, ritorno il risultato dello scontro "frontale"
			return blocks[col][row].getType() == Block.ENEMY;

		// se sto cadendo e sotto i piedi ho un nemico lo elimino dall'arraylist
		if (blocks[col][row + Mario.getInstance().getSpeed()].getType() == Block.ENEMY) {
			MushRoom.getInstance().remove(col, row + Mario.getInstance().getSpeed());
			return true;
		}

		return false;
	}

	public void jump() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				salita();
				fall();
			}
		}, 300);
	}

	private void salita() {
		int i = 0; // contatore del salto

		// salto destra
		if (MarioView.JumpUpRight) {
			// se non ho collisioni di muri sulla testa e nell'atteraggio non supero le
			// colonne della matrice
			while (((mario.getX() + mario.getSpeed() * 2) < Settings.nCells)
					&& !game.collisionWall(mario.getX() + mario.getSpeed(), mario.getY() - mario.getSpeed()) && i < 2) {
				blocks[mario.getX()][mario.getY()].setType(Block.EMPTY);

				mario.setY(mario.getY() - mario.getSpeed());
				mario.setX(mario.getX() + mario.getSpeed());// mi sposto in diagonale
				
				if (game.collisionCoin(mario.getX(), mario.getY()))
					mario.addCoins();

				blocks[mario.getX()][mario.getY()].setType(Block.MARIO);

				i++;
				gp.update();
			}
			if ((mario.getX() + mario.getSpeed() * 2) >= Settings.nCells)// se andro' a superare le col. della mat mi posiziono 
																			//sull'ultima colonna disponibile
			{
				blocks[mario.getX()][mario.getY()].setType(Block.EMPTY);
				mario.setX(Settings.nCells - 1);
				blocks[mario.getX()][mario.getY()].setType(Block.MARIO);
				MarioView.getInstance().stop();
			}
		}
		// salto a sx
		else if (MarioView.JumpUpLeft) {
			// se non ho collisioni di muri sulla testa e nell'atteraggio non supero le
			// colonne della matrice
			while (((mario.getX() - mario.getSpeed() * 2) >= 0)
					&& !game.collisionWall(mario.getX() - mario.getSpeed(), mario.getY() - mario.getSpeed()) && i < 2) {
				blocks[mario.getX()][mario.getY()].setType(Block.EMPTY);

				mario.setY(mario.getY() - mario.getSpeed());// mi sposto di 2 celle
				mario.setX(mario.getX() - mario.getSpeed());

				if (game.collisionCoin(mario.getX(), mario.getY()))
					mario.addCoins();
				blocks[mario.getX()][mario.getY()].setType(Block.MARIO);

				i++;

				gp.update();
			}

			if ((mario.getX() - mario.getSpeed() * 2) < 0)// se andro' alla colonna -1 della mat
			{
				blocks[mario.getX()][mario.getY()].setType(Block.EMPTY);
				mario.setX(0);
				blocks[mario.getX()][mario.getY()].setType(Block.MARIO);
				MarioView.getInstance().stop();
			}
		}
	}

	// mario non deve volare!
	private boolean falling = false;

	private void fall() {
		while (!game.collisionWall(mario.getX(), mario.getY() + mario.getSpeed())) {
			falling = true;
			if (game.collisionCoin(mario.getX(), (mario.getY() + mario.getSpeed())))
				mario.addCoins();

			collisionEnemy(mario.getX(), mario.getY());
			if (mario.getY() + mario.getSpeed() < Settings.nCells) {
				blocks[mario.getX()][mario.getY()].setType(Block.EMPTY);
				mario.setY(mario.getY() + mario.getSpeed());

				blocks[mario.getX()][mario.getY()].setType(Block.MARIO);
				gp.update();
			}
		}
		MarioView.d = Mario.MOVE_RIGHT;
		MarioView.getInstance().stop();
	}

	private int newX(int direction) {
		if ((direction == Mario.MOVE_LEFT) && mario.getX() > 0)
			return mario.getX() - mario.getSpeed();
		else if ((direction == Mario.MOVE_RIGHT) && mario.getX() < Settings.nCells - 1)
			return mario.getX() + mario.getSpeed();

		return mario.getX();
	}

	public boolean winner() {
		return mario.getX() == Game.xStar && mario.getY() == Game.yStar;
	}
}
