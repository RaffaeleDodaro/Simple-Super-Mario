package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Level {

	private static int Level;

	private static Level level = null;

	public static Level getInstance() {
		if (level == null)
			level = new Level();
		return level;
	}

	public static int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}

	public int upLevel() {
		return Level++;
	}

	public Vector<String> leggi(BufferedReader br) {
		String[] div = { "" };
		Vector<String> copia = new Vector<String>();

		int h = (-Settings.nCells);
		try {
			while (br.ready()) {
				div = br.readLine().split(" ");

				for (int i = (h + div.length); i < div.length; i++)
					copia.add(div[i]);
			}
			br.close();
		} catch (IOException e) {e.printStackTrace();}
		return copia;
	}	

	public Vector<String> RLevel() {
		Vector<String> copia = new Vector<String>();
		if (LoadGame.loadingGame)
		{
			copia = LoadGame.copia;
			LoadGame.loadingGame=false;
		}
		else {
			try {
				BufferedReader br = new BufferedReader(new FileReader("src/level/Level" + Level + ".txt"));
				copia = leggi(br);
			} catch (IOException e) {JOptionPane.showMessageDialog(null,"livello non trovato");}
		}
		return copia;
	}
}
