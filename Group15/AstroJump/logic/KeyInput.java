package logic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * AstroJumpGame.logic: KeyInput class.
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 * 
 * Controller for game class and creates for specific keystrokes entered
 */

public class KeyInput extends KeyAdapter {

	private Game game;

	/**
	 * Constructor to give value to game instance variable
	 *
	 * @param game Reference to game class.
	 */
	public KeyInput(Game game){

		this.game = game;

	}

	/**
	 * Method initializes action when key is pressed
	 */
	public void keyPressed(KeyEvent keyEvent){
		int key = keyEvent.getKeyCode();

				// Move avatar left
				if(key == KeyEvent.VK_LEFT){
					game.moveLeft();
				}
				// Move avatar right
				else if(key == KeyEvent.VK_RIGHT){
					game.moveRight();
				}
				// Launch avatar
				else if(key == KeyEvent.VK_UP){
					game.moveUp();
				}
				// Resumes game from pause state
				if (key == KeyEvent.VK_ENTER) {
	        		game.start();
	        		return;
	        	}
				// Triggers pause state
	            if (key == KeyEvent.VK_P) {
	                game.pause();
	                return;
	            }
	            // Restarts game
	            if (key == KeyEvent.VK_R) {
	            	game.restart();
	            }



	}

	/**
	 * Method stops movement when keys are released
	 */
	public void keyReleased(KeyEvent keyEvent){
		int key = keyEvent.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	game.stopMove();
        }
        
        if (key == KeyEvent.VK_RIGHT) {
        	game.stopMove();
        	}
        }
	}
