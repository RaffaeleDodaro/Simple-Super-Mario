package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import character.Mario.Mario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import menu.ChooseLevelMenu;
import menu.Menu;
import view.GraphicsUtil;

public class LoadGame {

	@FXML
	private ListView<String> lstView;

	@FXML
	private Button btnLoad;

	@FXML
	private Label lblSelect;

	int options = 0;

	public void drawLoadGame() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoadGame.fxml"));
		GraphicsUtil.createScreen(loader, 800, 600);
	}

	public static ArrayList<String> SaveAndLoad() {
		ArrayList<String> arraySave = new ArrayList<String>();

		File folder = new File("src/SaveGames/");
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt");// leggo solo i file con estensione .txt
			}
		});

		for (int i = 0; i < listOfFiles.length; i++)
			if (listOfFiles[i].isFile())
				arraySave.add(listOfFiles[i].getName());
		return arraySave;
	}

	public void initialize() {
		ArrayList<String> arraySave = SaveAndLoad();

		for (int i = 0; i < arraySave.size(); i++)
			lstView.getItems().add(arraySave.get(i));
	}

	@FXML
	void chooseFile(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (lstView.getSelectionModel().getSelectedIndex() != -1) {
				Menu.getInstance().closeCurrentWindow(event);
				load();
			} else
				lblSelect.setVisible(true);
		}

		else if (event.getCode() == KeyCode.ESCAPE) {
			Menu ml = new Menu();
			ml.closeCurrentWindow(event);
			ml.menuScene();
		}
	}

	@FXML
	void loadLevel(MouseEvent event) {
		if (lstView.getSelectionModel().getSelectedIndex() != -1) {
			Menu.getInstance().closeCurrentWindow(event);
			load();
		} else
			lblSelect.setVisible(true);
	}

	public static boolean loadingGame = false;
	public static Vector<String> copia;

	public void load() {
		// quando premi invio viene caricato il livello e questo metodo lo uso per
		// leggere i dati
		loadingGame = true;

		int k = 0;
		BufferedReader br;

		Mario mario = Mario.getInstance();
		Level l = Level.getInstance();

		copia = new Vector<String>();

		String[] div = { "" };
		int h = (-Settings.nCells);

		try {
			br = new BufferedReader(new FileReader("src/SaveGames/" + lstView.getSelectionModel().getSelectedItem()));

			while (br.ready()) {
				if (k < 3) {
					div = br.readLine().split("\n");

					if (k == 0)// ho letto il livello
						l.setLevel(Integer.parseInt(div[0]));
					else if (k == 1)// ho letto coins
						mario.setCoins(Integer.parseInt(div[0]));
					else if (k == 2)// ho letto la vita
						mario.setLifePoints(Integer.parseInt(div[0]));
				} else {
					div = br.readLine().split(" ");

					for (int i = (h + div.length); i < div.length; i++)
						copia.add(div[i]);
				}
				k++;
			}
			br.close();
		} catch (Exception e) {JOptionPane.showMessageDialog(null, "error load level");	}

		new ChooseLevelMenu().createLevel(l);
	}
}