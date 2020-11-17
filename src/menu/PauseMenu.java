package menu;

import application.GameLoop;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Game;
import utils.SaveGame;
import utils.Settings;
import view.GraphicPanel;

public class PauseMenu {

	private int options = 1;

	@FXML
	private ImageView lblImgExitGame;

	@FXML
	private ImageView imgSelectIcon2;

	@FXML
	private ImageView lblSaveGame;

	@FXML
	private ImageView lblImgResume;

	@FXML
	private ImageView imgSelectIcon1;

	@FXML
	private ImageView imgSelectIcon;

	@FXML
	public void KPauseMenu(KeyEvent event) {
		if (options > 1 && event.getCode() == KeyCode.UP) {
			options--;
			if (options == 2)
				chooseExit();
			if (options == 3)
				chooseSave();
		}

		if (options < 3 && event.getCode() == KeyCode.DOWN) {
			options++;
			if (options == 1)
				chooseResume();
			if (options == 2)
				chooseExit();
		}

		if (options == 1)
			chooseResume();
		if (options == 3)
			chooseSave();

		if (event.getCode() == KeyCode.ENTER) {
			if (options == 1)
				drawResumeGame(event);
			if (options == 2)
				System.exit(0);
			if (options == 3)
				new SaveGame().saveScreen();
		}

		if (event.getCode() == KeyCode.ESCAPE) {
			Menu.getInstance().closeCurrentWindow(event);
			drawResumeGame(event);
		}
	}

	private void drawResumeGame(Event event) {
		Menu.getInstance().closeCurrentWindow(event);
		Game.State = Game.STATE.GAME;
		GraphicPanel gp = new GraphicPanel(Game.getInstance());
		Stage primaryStage = new Stage();
		Scene scene = new Scene(gp);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setWidth(Settings.windowsSize);
		primaryStage.setHeight(Settings.windowsSize);
		primaryStage.setResizable(false);
		primaryStage.show();

		GameLoop gameLoop = new GameLoop(gp);
		gameLoop.start();
	}

	private void chooseResume() {
		options = 1;
		imgSelectIcon.setVisible(true);
		imgSelectIcon1.setVisible(false);
		imgSelectIcon2.setVisible(false);

		imgSelectIcon.resize(58, 58);

		lblImgResume.setFitWidth(170);
		lblImgExitGame.setFitWidth(150);
		lblSaveGame.setFitWidth(150);

		lblImgResume.setFitHeight(68);
		lblImgExitGame.setFitHeight(48);
		lblSaveGame.setFitHeight(48);
	}

	private void chooseExit() {
		options = 2;

		imgSelectIcon.setVisible(false);
		imgSelectIcon1.setVisible(true);
		imgSelectIcon2.setVisible(false);

		imgSelectIcon1.resize(58, 58);

		lblImgResume.setFitWidth(150);
		lblImgExitGame.setFitWidth(170);
		lblSaveGame.setFitWidth(150);

		lblImgResume.setFitHeight(48);
		lblImgExitGame.setFitHeight(68);
		lblSaveGame.setFitHeight(48);
	}

	private void chooseSave() {
		options = 3;

		imgSelectIcon.setVisible(false);
		imgSelectIcon1.setVisible(false);
		imgSelectIcon2.setVisible(true);

		imgSelectIcon2.resize(58, 58);

		lblImgResume.setFitWidth(150);
		lblImgExitGame.setFitWidth(150);
		lblSaveGame.setFitWidth(170);

		lblImgResume.setFitHeight(48);
		lblImgExitGame.setFitHeight(48);
		lblSaveGame.setFitHeight(68);
	}

	@FXML
	void resumeGame(MouseEvent event) {
		if (options == 1)
			drawResumeGame(event);
	}

	@FXML
	void exitGame(MouseEvent event) {
		if (options == 2)
			System.exit(0);
	}

	@FXML
	void saveGame(MouseEvent event) {
		if (options == 3)
			new SaveGame().saveScreen();
	}

	@FXML
	void zoomResumeIn(MouseEvent event) {
		chooseResume();
	}

	@FXML
	void zoomExitIn(MouseEvent event) {
		chooseExit();
	}

	@FXML
	void zoomSaveIn(MouseEvent event) {
		chooseSave();
	}
}