package view;

import java.util.ArrayList;

import character.Mario.Mario;
import javafx.scene.image.Image;
import utils.Settings;

public class MarioView {

	private ArrayList<Image> imagesLeft;
	private ArrayList<Image> imagesRight;

	public static int d = Mario.MOVE_RIGHT;

	private int indexLeft;
	private int indexRight;

	Image currentImageRight;
	Image currentImageLeft;

	Image currentImageJumpRight;
	Image currentImageJumpLeft;

	Image imgJumpRight;
	Image imgJumpLeft;

	int dim;

	private boolean move;
	public static boolean JumpUpRight = false;
	public static boolean JumpUpLeft = false;

	private static MarioView instance = null;

	public static MarioView getInstance() {
		if (instance == null)
			instance = new MarioView();
		return instance;
	}

	private MarioView() {

		imagesLeft = new ArrayList<Image>();
		imagesRight = new ArrayList<Image>();

		dim = Settings.blockSize;
		move = false;
		indexLeft = 0;
		indexRight = 0;

		for (int i = 0; i < 5; i++) {
			Image imgL = new Image(getClass().getResourceAsStream("/view/images/Mario/Left/" + i + "L.png"));
			imagesLeft.add(imgL);

			Image imgR = new Image(getClass().getResourceAsStream("/view/images/Mario/Right/" + i + "R.png"));
			imagesRight.add(imgR);
		}

		imgJumpRight = new Image(getClass().getResourceAsStream("/view/images/Mario/Jump/4R.png"));
		imgJumpLeft = new Image(getClass().getResourceAsStream("/view/images/Mario/Jump/4L.png"));

		currentImageRight = imagesRight.get(0);
		currentImageLeft = imagesLeft.get(0);
	}

	public void move() {
		move = true;
	}

	public void stop() {
		move = false;
	}

	public void update() {
		if (move) {
			if (d == Mario.MOVE_RIGHT) {
				indexRight++;
				if (indexRight >= imagesRight.size())
					indexRight = 0;
			} else if (d == Mario.MOVE_LEFT) {
				indexLeft++;
				if (indexLeft >= imagesLeft.size())
					indexLeft = 0;
			}
		} else {
			if (d == Mario.MOVE_RIGHT)
				indexRight = 0;
			if (d == Mario.MOVE_LEFT)
				indexLeft = 0;
		}
		if (d == Mario.MOVE_RIGHT)
			currentImageRight = imagesRight.get(indexRight);
		if (d == Mario.MOVE_LEFT)
			currentImageLeft = imagesLeft.get(indexLeft);

		if (d == Mario.JUMP_RIGHT)
			currentImageJumpRight = imgJumpRight;
		if (d == Mario.JUMP_LEFT)
			currentImageJumpLeft = imgJumpLeft;
	}
}
