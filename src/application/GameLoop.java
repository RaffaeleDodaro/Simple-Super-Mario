package application;

import javafx.animation.AnimationTimer;
import logic.Game;
import logic.Game.STATE;
import view.GraphicPanel;

public class GameLoop extends AnimationTimer {
	private long previousTime;
	private GraphicPanel gp;
	private long frequency = 60 * 1000000;

	public GameLoop(GraphicPanel p) {
		gp = p;
		previousTime = 0;
	}

	public void handle(long currentNanoTime) {
		if (Game.State == STATE.GAME) {
			this.start();
			if (currentNanoTime - previousTime >= frequency) {
				gp.update();
				previousTime = currentNanoTime;
			}
		} else {
			// carica il menu di pausa
			this.stop();
			gp.pause();
		}

	}
}