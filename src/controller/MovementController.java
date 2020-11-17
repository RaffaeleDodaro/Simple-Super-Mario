package controller;

import character.Mario.Mario;
import character.Mario.MarioMovement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.Game;
import logic.Game.STATE;
import menu.Menu;
import view.MarioView;

public class MovementController implements EventHandler<KeyEvent>{
	
	private boolean up=false;
	private long t=0;
	
	@Override
	public void handle(KeyEvent e) 
	{
		if(Game.State==STATE.GAME)
		{	
			if(e.getEventType().equals(KeyEvent.KEY_PRESSED))
			{
				if(e.getCode()==KeyCode.UP) {
					up=true;
					t=System.currentTimeMillis();
				}
				
				if(e.getCode()==KeyCode.LEFT && !up)
				{
					MarioMovement.getInstance().move(Mario.MOVE_LEFT);
					MarioView.d=Mario.MOVE_LEFT;
					MarioView.getInstance().move();
				}
				
				if(e.getCode()==KeyCode.RIGHT && !up)
				{
					MarioMovement.getInstance().move(Mario.MOVE_RIGHT);
					MarioView.d=Mario.MOVE_RIGHT;
					MarioView.getInstance().move();
				}
				
				if(e.getCode()==KeyCode.LEFT && up && (System.currentTimeMillis()-t<=300))
				{
					up=false;
					MarioView.d=Mario.JUMP_LEFT;
					MarioView.JumpUpLeft=true;
					MarioView.JumpUpRight=false;
					MarioMovement.getInstance().jump();
					MarioView.getInstance().move();
					MarioView.getInstance().stop();
				}
				
				if(e.getCode()==KeyCode.RIGHT && up && (System.currentTimeMillis()-t<=300))
				{
					up=false;
					MarioView.d=Mario.JUMP_RIGHT;
					MarioView.JumpUpRight=true;
					MarioView.JumpUpLeft=false;
					MarioMovement.getInstance().jump();
					MarioView.getInstance().move();
					MarioView.getInstance().stop();
				}
				if(e.getCode()==KeyCode.ESCAPE)
				{
					Game.State=Game.STATE.PAUSE;
					Menu.getInstance().closeCurrentWindow(e);
				}
			}
			
			if(e.getEventType().equals(KeyEvent.KEY_RELEASED))
			{
				if(e.getCode()==KeyCode.RIGHT || e.getCode()==KeyCode.LEFT)
					MarioView.getInstance().stop();
			}
		}
	}
	
}
