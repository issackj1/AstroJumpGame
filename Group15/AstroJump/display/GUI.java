package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import common.GameConfig;
import common.Types.STATE;
import gameObjects.Backdrop;
import gameObjects.Boost;
import gameObjects.Meteor;
import logic.Game;
import logic.KeyInput;


/**
 * Displays graphic user interface in the window.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class GUI extends JPanel implements ActionListener{

	private Game game;
	private Timer timer;

    public GUI() {
    	initGUI();
    }
    
    /**
     * Class constructor.
     * Initializes game GUI.
     */
    private void initGUI() {
        setFocusable(true);
        setDoubleBuffered(true);
        game = new Game();

        addKeyListener(new KeyInput(game));
        timer = new Timer(GameConfig.REFRESH_RATE, this);
        timer.start();
    }

    /**
 	 * Show movement in game.
 	 * 
 	 * @param timerEvent	Refreshes game.
 	 */
    public void actionPerformed(ActionEvent timerEvent) {
    	game.update();
        repaint();
    }

	/**
	 * Displays screens according to the state (Intro, Play, Paused, GameOver) of the game.
	 * 
	 * @param graphicEvent	Used to draw images in window.
	 */
    public void paintComponent(Graphics graphicEvent) {
    	super.paintComponent(graphicEvent);
    	drawBackdrop(graphicEvent);
    	
    	if (game.getGameState() == STATE.ScreenIntro)
       		drawScreenIntro(graphicEvent);  	
    	else if (game.getGameState() == STATE.Play || game.getGameState() == STATE.Intro || game.getGameState() == STATE.Paused) {
    		if (!game.getAvatar().avatarLaunched())
    			drawTutorial(graphicEvent);  
    		drawGameObjects(graphicEvent);
	        drawScores(graphicEvent);
	        if (game.getGameState() == STATE.Paused) 
	        	drawPaused(graphicEvent);
    	}
        else if (game.getGameState() == STATE.GameOver)
            drawGameOver(graphicEvent);
    }

	/**
	 * Displays game objects.
	 * 
	 * @param graphicEvent	Used to draw images in window.
	 */
    private void drawGameObjects(Graphics graphicEvent) {
        Graphics2D graphics2D = (Graphics2D) graphicEvent;
        graphics2D.drawImage(game.getAvatar().getImage(), game.getAvatar().getX(), game.getAvatar().getY(), this);
        drawBoost(graphicEvent);
        drawMeteor(graphicEvent);
    }
	
	/**
	 * Displays 'boosts'.
	 * 
	 * @param graphicEvent	Used to draw images in window.
	 */
	private void drawBoost(Graphics graphicEvent) {
		Graphics2D graphics2D = (Graphics2D) graphicEvent;
		ArrayList<Boost> boostList = game.getProjectileCollection().getBoostList();
		for (Object boostElement : boostList) {
			Boost aBoost = (Boost) boostElement;
			graphics2D.drawImage(aBoost.getImage(), aBoost.getX(), aBoost.getY(), this);
		}		
	}
	
	/**
	 * Displays 'meteors'.
	 * 
	 * @param graphicEvent	Used to draw images in window.
	 */
	private void drawMeteor(Graphics graphicEvent) {
		Graphics2D graphics2D = (Graphics2D) graphicEvent;
		ArrayList<Meteor> meteorList = game.getProjectileCollection().getMeteorList();
		for (Object meteorElement : meteorList) {
			Meteor aMeteor = (Meteor) meteorElement;
			graphics2D.drawImage(aMeteor.getImage(), aMeteor.getX(), aMeteor.getY(), this);
		}		
	}

	/**
	 * Displays background.
	 *
	 * @param graphicEvent	Used to draw images in window.
	 */
    private void drawBackdrop(Graphics graphicEvent) {
    	Graphics2D graphics2D = (Graphics2D) graphicEvent;
    	graphics2D.drawImage(game.getBackground().getImage(), game.getBackground().getX(), game.getBackground().getY(), this);
    }

	/**
	 * Displays (high) score.
	 * 
	 * @param graphicEvent	Used to draw images in window.
	 */
	public void drawScores(Graphics graphicEvent) {
		Font font = new Font("Verdana", 25, 25);
		graphicEvent.setFont(font);
		graphicEvent.setColor(new Color(21, 41, 78));
		graphicEvent.drawString("Score: " + HUD.getScore(), GameConfig.SCORE_X, GameConfig.SCORE_Y);
		graphicEvent.drawString("High Score: " + HUD.getHighScore(), GameConfig.HIGHSCORE_X, GameConfig.HIGHSCORE_Y);	
	}

	/**
	 * Displays introduction screen.
	 * 
	 * @param graphicEvent Used to draw images in window.
	 */
	public void drawScreenIntro(Graphics graphicEvent)
	{
		Backdrop logo = new Backdrop(GameConfig.LOGO_IMAGE);
		logo.getDimensions();
		logo.setY((int) (GameConfig.HEIGHT * (0.25)));
		logo.setX((GameConfig.WIDTH/2) - (logo.getWidth()/2));
		graphicEvent.setColor(Color.WHITE);
		graphicEvent.fillRect(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
		graphicEvent.drawImage(logo.getImage(), logo.getX(), logo.getY(), this);
		
	}
	
	/**
	 * Displays instructions of game.
	 * 
	 * @param graphicEvent	Used to draw images in window.
	 */
	public void drawTutorial(Graphics graphicEvent)
	{
		Backdrop logo = new Backdrop(GameConfig.TUTORIAL_IMAGE);
		logo.getDimensions();
		logo.setY((int) (GameConfig.HEIGHT * (0.25)));
		logo.setX((GameConfig.WIDTH/2) - (logo.getWidth()/2));
		graphicEvent.drawImage(logo.getImage(), logo.getX(), logo.getY(), this);
	}
	
	/**
	* Displays the 'game over' screen.
	*
	* @param graphicEvent	Used to draw images in window.
	*/
	public void drawGameOver(Graphics graphicEvent) {
		Backdrop logo = new Backdrop(GameConfig.GAME_OVER_IMAGE);
		logo.getDimensions();
		logo.setY((int) (GameConfig.HEIGHT * (0.25)));
		logo.setX((GameConfig.WIDTH/2) - (logo.getWidth()/2));
		//graphicEvent.setColor(Color.WHITE);
		//graphicEvent.fillRect(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
		graphicEvent.drawImage(logo.getImage(), logo.getX(), logo.getY(), this);
		
		Font font = new Font("Verdana", 120, 40);
		graphicEvent.setFont(font);
		graphicEvent.setColor(new Color(21, 41, 78));
		graphicEvent.drawString("High Score: " + HUD.getHighScore(), GameConfig.WIDTH/8, logo.getY());
		graphicEvent.drawString("Your Score: " + HUD.getScore(), GameConfig.WIDTH/8, logo.getY() + 40);
	}

	/**
	* Displays 'PAUSED' when game state is in PAUSED.
	*
	* @param graphicEvent	Used to draw images in window.
	*/
	public void drawPaused(Graphics graphicEvent) {
		Backdrop logo = new Backdrop(GameConfig.PAUSED_IMAGE);
		logo.getDimensions();
		logo.setY((int) (GameConfig.HEIGHT * (0.25)));
		logo.setX((GameConfig.WIDTH/2) - (logo.getWidth()/2));
		//graphicEvent.setColor(Color.WHITE);
		//graphicEvent.fillRect(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
		graphicEvent.drawImage(logo.getImage(), logo.getX(), logo.getY(), this);
	}

	/**
	 * Initializes dimensions for JPanel.
	 * 
	 * @return Dimension	The dimensions used for JFrame
	 */
    public Dimension getPreferredSize() {
        return new Dimension(GameConfig.WIDTH, GameConfig.HEIGHT);
    }
}
