package display;

/**
 * Stores the value of the score of the current game and high score.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class HUD{

	private static int score = 0;
	private static int highScore = 0;
	
	/**
	 * Score setter.
	 * 
	 * @param aScore	The value for the current score.
	 */
	public static void setScore(int aScore) {
		score = aScore;
	}
	
	/**
	 * High score setter.
	 * 
	 * @param aHighScore	The value for high score.
	 */
	public static void setHighScore(int aHighScore){
		highScore = aHighScore;
	}

	/**
	 * High score getter.
	 * 
	 * @return highScore	High score value.
	 */
	public static int getHighScore(){
		 return highScore;
	}

	/**
	 * Score getter.
	 * 
	 * @return score	Current score value.
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Reinitializes the current score to 0.
	 */
	public void reset() {
		score = 0;
	}
}
