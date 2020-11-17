package menu;

import javax.swing.JOptionPane;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Game;
import logic.Game.STATE;
import utils.LoadGame;
import view.GraphicsUtil;

public class Menu {
    private int options = 0;//mi serve come selettore nel menu    
    
    @FXML
    private ImageView imgStart;
    @FXML
    private ImageView imgCredits;
    @FXML
    private ImageView imgSelectIcon;
    @FXML
    private ImageView imgSelectIcon1;
    @FXML
    private ImageView imgSelectIcon2;
    @FXML
    private ImageView imgSelectIcon3;
    @FXML
    private ImageView imgHelp;
    @FXML
    private ImageView imgLoadGame;
  
    private static Menu menu=null;
    
    public static Menu getInstance()
    {
    	if (menu==null)
    		menu=new Menu();
    	return menu;
    }
    
    private void decorePrimaryStage(FXMLLoader loader)
    {
    	GraphicsUtil.createScreen(loader,800,600);
		@SuppressWarnings("unused")
		Menu ml=(Menu) loader.getController();
    }
    
	public void menuScene()
	{	
		Game.State=STATE.MENU;
		decorePrimaryStage(new FXMLLoader(getClass().getResource("Menu.fxml")));
	}	

    void loadCredits()
    {
    	Game.State=STATE.CREDITS;
    	decorePrimaryStage(new FXMLLoader(getClass().getResource("CreditScreen.fxml")));
    }
    
    void loadHelp()
    {
    	Game.State=STATE.HELP;
    	decorePrimaryStage(new FXMLLoader(getClass().getResource("HelpScreen.fxml")));
    }
	    
    public void closeCurrentWindow(Event event)
    {
    	Node source=(Node) event.getSource();//node e' una superclasse che gestisce gli elementi grafici dello stage
		Stage stage1=(Stage) source.getScene().getWindow();
		stage1.close();
    }
    
    @FXML
    void KListener(KeyEvent event){
    	if(event.getCode()==KeyCode.ESCAPE && Game.State!=Game.STATE.MENU) {//se sono in credits,help...
			try {
				closeCurrentWindow(event);
				options=0;
				menuScene();
			} catch (Exception e) {	JOptionPane.showMessageDialog(null,"error KListener Menu"); }
    	}
    	else if(event.getCode()==KeyCode.ESCAPE)	
    		closeCurrentWindow(event);
    	
    	else if(event.getCode()==KeyCode.UP) 
    	{
    		if(options>1)
    			options--;
    	}
    	
    	else if(event.getCode()==KeyCode.DOWN)
    	{
    		if(options<4)
    			options++;    		
    	}
    			
    	if(event.getCode()==KeyCode.ENTER)
    	{
    		if(options==1)
    		{
    			closeCurrentWindow(event);
    			loadLevelMenu();
    		}
    		else if(options==2)
    		{
    			closeCurrentWindow(event);
    			loadGame();    		
    		}
    		else if(options==3)
    		{				
    			closeCurrentWindow(event);
    			loadHelp();
    		}    		
    		else if(options==4)
    		{				
    			closeCurrentWindow(event);
    			loadCredits();
    		}
    	}
    	
    	if(options == 1)
    		chooseStart();
    	if(options==2)
			chooseLoadGame();
		if(options==3)
			chooseHelp();
		if(options==4)
			chooseCredits();
    }
    
    private void loadGame()
    {
    	new LoadGame().drawLoadGame();
    }
    
    @FXML
    private void CreditsScreen(Event event) {
		closeCurrentWindow(event);
    	loadCredits();
    }
    
    @FXML
    private void GameStart(Event event) {
		closeCurrentWindow(event);
    	loadLevelMenu();
	}
    @FXML
    private void HelpScreen(Event event) 
    {
		closeCurrentWindow(event);
    	loadHelp();
    }
    
    private void loadLevelMenu()
    {
    	new ChooseLevelMenu().loadLevelMenu(); 
    }

    private void chooseStart()
    {
    	options=1;
    	
    	imgStart.setFitHeight(80);
    	imgStart.setFitWidth(220);
    	
    	imgCredits.setFitHeight(60);
    	imgCredits.setFitWidth(200);
    	
    	imgHelp.setFitHeight(60);
    	imgHelp.setFitWidth(155);
    	
    	imgLoadGame.setFitHeight(60);
    	imgLoadGame.setFitWidth(200);
    	
    	imgSelectIcon.setVisible(true);
    	imgSelectIcon1.setVisible(false);
    	imgSelectIcon2.setVisible(false);
    	imgSelectIcon3.setVisible(false);
    	
    	//ridimensiono immagine fungo
    	imgSelectIcon.setFitHeight(58);
    	imgSelectIcon.setFitWidth(58);
    	
    }
    
    private void chooseLoadGame()
    {
    	options=2;
    	imgStart.setFitHeight(60);
    	imgStart.setFitWidth(200);
    	
    	imgCredits.setFitHeight(60);
    	imgCredits.setFitWidth(200);
    	
    	imgHelp.setFitHeight(60);
    	imgHelp.setFitWidth(155);
    	
    	imgLoadGame.setFitHeight(80);
    	imgLoadGame.setFitWidth(220);
    	
    	imgSelectIcon.setVisible(false);
    	imgSelectIcon1.setVisible(true);
    	imgSelectIcon2.setVisible(false);
    	imgSelectIcon3.setVisible(false);
    	
    	//ridimensiono immagine fungo
    	imgSelectIcon1.setFitHeight(58);
    	imgSelectIcon1.setFitWidth(58);
    }
    
    private void chooseHelp()
    {
    	options=3;
    	imgStart.setFitHeight(60);
    	imgStart.setFitWidth(200);
    	
    	imgCredits.setFitHeight(60);
    	imgCredits.setFitWidth(200);
    	
    	imgHelp.setFitHeight(80);
    	imgHelp.setFitWidth(220);
    	
    	imgLoadGame.setFitHeight(60);
    	imgLoadGame.setFitWidth(200);
    	
    	imgSelectIcon.setVisible(false);
    	imgSelectIcon1.setVisible(false);
    	imgSelectIcon2.setVisible(true);
    	imgSelectIcon3.setVisible(false);
    	
    	//ridimensiono immagine fungo
    	imgSelectIcon2.setFitHeight(58);
    	imgSelectIcon2.setFitWidth(58);    	
    }
    
    private void chooseCredits()
    {
    	options=4;
    	
    	imgStart.setFitHeight(60);
    	imgStart.setFitWidth(200);
    	
    	imgCredits.setFitHeight(80);
    	imgCredits.setFitWidth(220);
    	
    	imgHelp.setFitHeight(60);
    	imgHelp.setFitWidth(155);
    	
    	imgLoadGame.setFitHeight(60);
    	imgLoadGame.setFitWidth(200);
    	
    	imgSelectIcon.setVisible(false);
    	imgSelectIcon1.setVisible(false);
    	imgSelectIcon2.setVisible(false);
    	imgSelectIcon3.setVisible(true);
    	
    	//ridimensiono immagine fungo
    	imgSelectIcon3.setFitHeight(58);
    	imgSelectIcon3.setFitWidth(58);
    }
    
    @FXML
    private void loadGameScreen(MouseEvent event) {
    	closeCurrentWindow(event);
    	loadGame(); 
    }
    
    @FXML
    private void zoomStart(MouseEvent event) { chooseStart(); }
    
    @FXML
    private void zoomCredits(MouseEvent event) { chooseCredits(); }
    
    @FXML
    private void zoomHelp(MouseEvent event) { chooseHelp(); }
    
    @FXML
    private void zoomLoadGame(MouseEvent event) { chooseLoadGame(); }
}