package menu;

import application.GameLoop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Game;
import logic.Game.STATE;
import utils.Level;
import utils.Settings;
import view.GraphicPanel;
import view.GraphicsUtil;

public class ChooseLevelMenu {
	
    private Scene scene;
    private Stage primaryStage;
    private Menu m=Menu.getInstance();
    private FXMLLoader loader;
    private Level r = Level.getInstance();
    private int options = 0;//mi serve come selettore nel menu
    
    public ChooseLevelMenu() {primaryStage=new Stage();}
    
	public void loadLevelMenu()
	{
		Game.State=STATE.MENU;
    	loader= new FXMLLoader(getClass().getResource("ChooseLevel.fxml"));
    	@SuppressWarnings("unused")
		ChooseLevelMenu ml=(ChooseLevelMenu) loader.getController();
    	GraphicsUtil.createScreen(loader,800,600);
	}
    
    public void createLevel(Level r)
    {
    	Game.State=STATE.GAME;
    	Game game=Game.getInstance();
    	GraphicPanel gp=new GraphicPanel(game);
		scene=new Scene(gp);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setWidth(Settings.windowsSize);
		primaryStage.setHeight(Settings.windowsSize);
		primaryStage.setResizable(false);
		primaryStage.show();
		GameLoop gameLoop=new GameLoop(gp);
		gameLoop.start();
    }	    

    @FXML
    private void loadLevel1(MouseEvent event) {
		m.closeCurrentWindow(event);
    	r.setLevel(1);
    	createLevel(r);
    }
    
    @FXML
    private void loadLevel2(MouseEvent event) {
		m.closeCurrentWindow(event);
    	r.setLevel(2);
    	createLevel(r);
    }
    
    public void KListener(KeyEvent event)
    {
    	if(event.getCode()==KeyCode.ESCAPE)
    	{   
    		Menu m=new Menu(); 		
    		m.closeCurrentWindow(event);
    		m.menuScene();
    	}
    	
    	else if(event.getCode()==KeyCode.ENTER)
    	{
    		if(options==1)
    		{
    			r.setLevel(1);
    			m.closeCurrentWindow(event);
    			createLevel(r);
    		}
    		else if(options==2)
    		{
    			r.setLevel(2);
    			m.closeCurrentWindow(event);
    			createLevel(r);
    		}
    	}
    	
    	if(options>1 && event.getCode()==KeyCode.UP)
    		options--;
    	
    	if(options<2 && event.getCode()==KeyCode.DOWN)
        	options++;
    	
    	if(options == 1)
    		chooseLevel1();
    	if(options==2)
    		chooseLevel2();
    }
    
    private void chooseLevel1()
    {
    	options=1;
    	
    	imgLevel1.setFitHeight(68);
    	imgLevel1.setFitWidth(220);
    	
    	imgLevel2.setFitHeight(48);
    	imgLevel2.setFitWidth(200);
    	
    	imgSelectIcon1.setVisible(true);
    	imgSelectIcon2.setVisible(false);
    	
    	//ridimensiono immagine fungo
    	imgSelectIcon1.setFitHeight(58);
    	imgSelectIcon1.setFitWidth(58);
    }
    
    private void chooseLevel2()
    {
    	options=2;
    	
    	imgLevel1.setFitHeight(48);
    	imgLevel1.setFitWidth(200);
    	
    	imgLevel2.setFitHeight(68);
    	imgLevel2.setFitWidth(220);
    	
    	imgSelectIcon1.setVisible(false);
    	imgSelectIcon2.setVisible(true);

    	//ridimensiono immagine fungo
    	imgSelectIcon2.setFitHeight(58);
    	imgSelectIcon2.setFitWidth(58);
    }
    
    @FXML
    private void zoomLevel1(MouseEvent event){chooseLevel1();}
    
    @FXML
    private void zoomLevel2(MouseEvent event) {chooseLevel2();}

    @FXML
    private ImageView imgSelectIcon2;
    @FXML
    private ImageView imgLevel1;
    @FXML
    private ImageView imgSelectIcon1;
    @FXML
    private ImageView imgLevel2;
}