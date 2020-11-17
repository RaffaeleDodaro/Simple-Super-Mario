package logic;

import java.util.Vector;
import character.Enemy.MushRoom;
import character.Mario.Mario;
import model.Block;
import utils.Level;
import utils.Settings;

public class Game {

	private static Block[][] blocks = new Block[Settings.nCells][Settings.nCells];

	public static int yStar;
	public static int xStar;
	private Mario mario;
	
	private static Game game = null;

	public static Game getInstance() {
		if (game == null)
			game = new Game(Level.getInstance());

		return game;
	}

	public static enum STATE// mi serve per gestire in che stato mi trovo
	{
		MENU, GAME, CREDITS, HELP, PAUSE
	};

	public static STATE State = STATE.MENU;

	public Game(Level r) {
		if (State == STATE.GAME) {
			mario = Mario.getInstance();
			mario.setSpeed(1);

			for (int i = 0; i < blocks.length; i++)
				for (int j = 0; j < blocks[i].length; j++)
					blocks[i][j] = new Block(Block.EMPTY);
			int k = 0;
			Vector<String> l = r.RLevel();

			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[i].length; j++) {
					if (k < l.size()) {
						if (l.elementAt(k).equals("c"))
							blocks[j][i].setType(Block.COIN);

						else if (l.elementAt(k).equals("w"))
							blocks[j][i].setType(Block.LANDBLOCK);

						else if (l.elementAt(k).equals("b"))
							blocks[j][i].setType(Block.STOPBLOCK);

						else if (l.elementAt(k).equals("m")) {
							blocks[j][i].setType(Block.MARIO);

							mario.setX(j);
							mario.setY(i);
						}

						else if (l.elementAt(k).equals("s")) {
							blocks[j][i].setType(Block.STAR);
							yStar = i;
							xStar = j;
						}

						else if (l.elementAt(k).equals("f")) {
							blocks[j][i].setType(Block.ENEMY);

							MushRoom a = new MushRoom(0,0,1);
							a.setX(j);
							a.setY(i);
							MushRoom.getInstance().addMushroom(a);
						}

						else if (l.elementAt(k).equals("e"))
							blocks[j][i].setType(Block.EMPTY);

						k++;
					}
				}
			}
		}
	}

	public static Block[][] getBlocks() {
		return blocks;
	}	

	public boolean collisionCoin(int col, int row) {
		return blocks[col][row].getType() == Block.COIN;
	}

	public boolean collisionWall(int col, int row) {
		return blocks[col][row].getType() == Block.STOPBLOCK || blocks[col][row].getType() == Block.LANDBLOCK;
	}
}