package view;

import javax.swing.JOptionPane;
import character.Enemy.MushRoom;
import character.Enemy.MushRoomMovement;
import character.Mario.Mario;
import character.Mario.MarioMovement;
import controller.MovementController;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Game;
import logic.Game.STATE;
import utils.Level;
import utils.Settings;

public class GraphicPanel extends StackPane {

	private Canvas canvas;
	public Font font = Font.loadFont(getClass().getResourceAsStream("/view/Font/SuperMario.ttf"), 50);
	private Level rl = Level.getInstance();

	private static GraphicPanel gp = null;

	public static GraphicPanel getInstance(Game game) {
		if (gp == null)
			gp = new GraphicPanel(game);
		return gp;
	}

	public GraphicPanel(Game game) {
		canvas = new Canvas();
		canvas.setFocusTraversable(true);

		canvas.setOnKeyPressed(new MovementController());
		canvas.setOnKeyReleased(new MovementController());

		getChildren().add(canvas);
		this.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		canvas.widthProperty().bind(this.widthProperty());
		canvas.heightProperty().bind(this.heightProperty());
	}

	public void draw() {
		if (Game.State == STATE.GAME) {
			canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			for (int i = 0; i < Game.getBlocks().length; i++) {
				int x = i * Settings.blockSize;
				for (int j = 0; j < Game.getBlocks()[i].length; j++) {
					int y = j * Settings.blockSize;
					switch (Game.getBlocks()[i][j].getType()) {
					case model.Block.MARIO:
						MarioView mv = MarioView.getInstance();
						
						if (MarioView.d == Mario.MOVE_RIGHT)
							canvas.getGraphicsContext2D().drawImage(mv.currentImageRight, x, y, Settings.blockSize,
									Settings.blockSize);
						
						if (MarioView.d == Mario.MOVE_LEFT)
							canvas.getGraphicsContext2D().drawImage(mv.currentImageLeft, x, y, Settings.blockSize,
									Settings.blockSize);
						
						if (MarioView.d == Mario.JUMP_RIGHT)
							canvas.getGraphicsContext2D().drawImage(mv.currentImageJumpRight, x, y, Settings.blockSize,
									Settings.blockSize);
						
						if (MarioView.d == Mario.JUMP_LEFT)
							canvas.getGraphicsContext2D().drawImage(mv.currentImageJumpLeft, x, y, Settings.blockSize,
									Settings.blockSize);
						break;

					case model.Block.STOPBLOCK:
						canvas.getGraphicsContext2D().drawImage(GraphicsUtil.getImgStopBlock(), x, y,
								Settings.blockSize, Settings.blockSize);
						break;

					case model.Block.LANDBLOCK:
						canvas.getGraphicsContext2D().drawImage(GraphicsUtil.getImgLandBlock(), x, y,
								Settings.blockSize, Settings.blockSize);
						break;

					case model.Block.STAR:
						canvas.getGraphicsContext2D().drawImage(GraphicsUtil.getStar(), x, y, Settings.blockSize,
								Settings.blockSize);
						break;

					case model.Block.ENEMY:
						MushRoomView emv = MushRoomView.getInstance();
						canvas.getGraphicsContext2D().drawImage(emv.currentImage, x, y, Settings.blockSize,
								Settings.blockSize);
						break;

					case model.Block.COIN:
						canvas.getGraphicsContext2D().drawImage(GraphicsUtil.getCoin(), x, y, Settings.blockSize,
								Settings.blockSize);
						break;

					default:
						break;
					}
					drawCoins(canvas.getGraphicsContext2D());
					remainingLife(canvas.getGraphicsContext2D());
				}
			}

			checkDead(canvas.getGraphicsContext2D());

			if (MarioMovement.getInstance().winner())
				changeLevel();
		}
	}

	private boolean up = false;

	public void update() {
		MarioView.getInstance().update();

		if (up) {
			up = false;

			for (int i = 0; i < MushRoom.getInstance().listMushRoom.size(); i++) {
				MushRoom tmp = MushRoom.getInstance().listMushRoom.get(i);
				MushRoomMovement tmpMov = new MushRoomMovement(tmp);
				tmpMov.movementMushRooms();
				MushRoomView.getInstance().update(tmp);
			}
		} else
			up = true;

		draw();
	}

	public void pause() {
		if (Game.State == STATE.PAUSE)
			drawPauseMenu(canvas.getGraphicsContext2D());
	}

	public void changeLevel() {
		if (Level.getLevel() + 1 < 3)// 2 livelli ho creato e devo fare attenzione a non sforare i livelli
		{
			JOptionPane.showMessageDialog(null, "YOU PASSED LEVEL! \n PRESS OK TO GO NEXT");
			rl.upLevel();
			new Game(rl);
			canvas.setOnKeyPressed(new MovementController());
		} else {
			JOptionPane.showMessageDialog(null, "YOU ARE WINNER! \n PRESS OK TO CLOSE GAME");
			System.exit(0);
		}
	}

	private void drawCoins(GraphicsContext g) {
		g.setFill(Color.RED);
		g.setFont(font);
		g.drawImage(GraphicsUtil.getCoin(), Settings.windowsSize - 200, Settings.windowsSize - 780);
		g.fillText("" + Mario.getInstance().getCoins(), Settings.windowsSize - 170, Settings.windowsSize - 750);
	}

	private void remainingLife(GraphicsContext g) {
		g.setFill(Color.RED);
		g.setFont(font);
		g.drawImage(GraphicsUtil.getLifePoints(), 10, 10);
		g.fillText("" + Mario.getInstance().getLifePoints(), 70, 52);
	}

	private void checkDead(GraphicsContext g) {
		if (Mario.getInstance().getLifePoints() == 0) {
			JOptionPane.showMessageDialog(null, "OH NO! YOU ARE DEAD! \n PRESS OK TO CLOSE GAME");
			System.exit(0);
		}
	}

	private void drawPauseMenu(GraphicsContext g) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/PauseMenu.fxml/"));
		GraphicsUtil.createScreen(loader, 600, 300);
	}
}
