package view;

import character.Enemy.MushRoom;
import javafx.scene.image.Image;

public class MushRoomView {
	
	private static MushRoomView instance=null;
	
	public static MushRoomView getInstance()
	{
		if(instance==null)
			instance=new MushRoomView();
		return instance;
	}
	
	Image currentImage;
	Image imgLeft;
	Image imgRight;
	
	private MushRoomView()
	{
		imgLeft =new Image(GraphicsUtil.class.getResourceAsStream("/view/images/Enemy/Mushrooms/MushLeft.png"));
		imgRight=new Image(GraphicsUtil.class.getResourceAsStream("/view/images/Enemy/Mushrooms/MushRight.png"));
	}

	public void update(MushRoom tmp)
	{
		if(tmp.getDirection()==MushRoom.MOVE_LEFT)
			currentImage=imgLeft;
		else
			currentImage=imgRight;
	}
}