package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GraphicsUtil {

	private final static String PATH = "/view/images";

	public static void createScreen(FXMLLoader loader, int dimW, int dimH) {
		try {
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root, dimW, dimH);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR CREATE A SCREEN! RETRY");
		}
	}

	public static Image getImgStopBlock() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Block/StopBlock.png"));
	}

	public static Image getImgLandBlock() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Block/landBlock.png"));
	}

	public static Image getStar() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Various/Star.png"));
	}

	public static Image getWinner() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Various/Winner1.png"));
	}

	public static Image getBackgroundStartScreen() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Screen/StartScreen.jpg"));
	}

	public static Image getSelectIcon() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Various/select-icon.png"));
	}

	public static Image getDead() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Screen/Dead.jpg"));
	}

	public static Image getCredits() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Screen/Credits.jpg"));
	}

	public static Image getCoin() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Coin/Coin1.png"));
	}

	public static Image getLifePoints() {
		return new Image(GraphicsUtil.class.getResourceAsStream(PATH + "/Various/LifePoints.png"));
	}
}
