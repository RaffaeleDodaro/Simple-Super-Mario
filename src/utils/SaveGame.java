package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import character.Mario.Mario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import logic.Game;
import menu.Menu;
import model.Block;
import view.GraphicsUtil;

public class SaveGame {
	@FXML
	private TextField txtNameSaveGame;

	@FXML
	private Button btnSave;

	@FXML
	private Label lblSaveStatus;

	private boolean createSave() {
		BufferedWriter bw;
		try {
			ArrayList<String> arraySave = LoadGame.SaveAndLoad();

			boolean exists = false;

			for (int i = 0; i < arraySave.size() && !exists; i++)
				if (arraySave.get(i).equalsIgnoreCase(txtNameSaveGame.getText() + ".txt")
						&& !txtNameSaveGame.getText().equals("")) 
				{
					lblSaveStatus.setVisible(true);
					lblSaveStatus.setText("FILE EXISTS. CHANGE NAME!");
					exists = true;
				}

			if (!exists && !txtNameSaveGame.getText().equals("")) {
				bw = new BufferedWriter(new FileWriter("src/SaveGames/" + txtNameSaveGame.getText() + ".txt"));

				bw.write(String.valueOf(Level.getLevel()));// il valueof converte int to string e leggo il livello attuale
				bw.newLine();

				bw.write(String.valueOf(Mario.getInstance().getCoins()));// il valueof converte int to string e leggo le monete che ha mario
				bw.newLine();

				bw.write(String.valueOf(Mario.getInstance().getLifePoints()));//punti vita che ha mario
				bw.newLine();

				for (int i = 0; i < Game.getBlocks().length; i++) {
					for (int j = 0; j < Game.getBlocks()[i].length; j++) {
						if (Game.getBlocks()[j][i].getType() == Block.COIN)
							bw.write('c' + " ");

						if (Game.getBlocks()[j][i].getType() == Block.STOPBLOCK)
							bw.write('b' + " ");

						if (Game.getBlocks()[j][i].getType() == Block.LANDBLOCK)
							bw.write('w' + " ");

						if (Game.getBlocks()[j][i].getType() == Block.MARIO)
							bw.write('m' + " ");

						if (Game.getBlocks()[j][i].getType() == Block.STAR)
							bw.write('s' + " ");

						if (Game.getBlocks()[j][i].getType() == Block.ENEMY)
							bw.write('f' + " ");

						if (Game.getBlocks()[j][i].getType() == Block.EMPTY)
							bw.write('e' + " ");
					}
					bw.newLine();
				}
				bw.close();
				return true;
			}
		} catch (Exception e) {JOptionPane.showMessageDialog(null, "ERROR WHILE SAVING");}

		if (txtNameSaveGame.getText().equals("")) {
			lblSaveStatus.setVisible(true);
			lblSaveStatus.setText("NAME NOT VALID. CHANGE NAME!");
		}
		return false;
	}

	@FXML
	private void saveClick(MouseEvent event) {
		if (createSave()) {
			JOptionPane.showMessageDialog(null, "SAVED CORRECTLY");
			Menu.getInstance().closeCurrentWindow(event);
		}
	}

	@FXML
	void KSave(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER && !txtNameSaveGame.getText().equals(""))
			createSave();

		else if (event.getCode() == KeyCode.ENTER && txtNameSaveGame.getText().equals("")) {
			lblSaveStatus.setVisible(true);
			lblSaveStatus.setText("NAME NOT VALID. CHANGE NAME!");
		} else if (event.getCode() == KeyCode.ESCAPE)
			Menu.getInstance().closeCurrentWindow(event);
	}

	public void saveScreen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/utils/SaveGame.fxml"));
		GraphicsUtil.createScreen(loader, 600, 400);
	}
}
