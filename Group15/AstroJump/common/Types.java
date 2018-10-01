package common;

/**
 * AstroJumpGame.common: Types class.
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 *
 * The class type is responsible for setting the game state of the game.
 */

public class Types {

	/**
	 * Method creates constants that can call game states
	 * by including a pause menu.
	 * Intro will be separate from this as it initializes the game.
	 */
	public enum STATE {
		// Displays game intro
		ScreenIntro,
		// Initial state of game; When player first starts to game, after restarting
		Intro,
		// Play state triggers when avatar launches to the air
		Play,
		// Triggers when avatar hits bottom screen
		GameOver,
		// Triggers when game is paused
		Paused;
	}
}
