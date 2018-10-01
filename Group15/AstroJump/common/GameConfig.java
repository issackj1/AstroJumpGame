package common;

/**
 * The Class GameConfig.
 * Allows programmer to easily change variables
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */

public class GameConfig {

	/**
	 * Refresh rate in millisecond
	 */
	public static final int REFRESH_RATE = 10;
	
	/**
	 *  JFrame dimensions
	 */
	public static final int WIDTH = 440;
	public static final int HEIGHT = WIDTH * 2;

	/**
	 *  Falling rate of simulated gravity 
	 */
	public static final int GRAVITY = 5;
	public static final int AVATAR_SPEED = 6;

	/**
	 *  Falling speed and scroll speed of boost
	 */
	public static final int BOOST_ACCELERATION = 10;
	public static final int BOOST_SCROLL_UP_SPEED = 5;
	
	/**
	 * Falling speed and scroll speed of meteor
	 */
	public static final int METEOR_FALL_SPEED = 2;
	public static final int METEOR_SCROLL_UP_SPEED = -7;

	/**
	 * Duration of boost effect  
	 */
	public static final int BOOST_DURATION = 500;
	public static final int LAUNCH_DURATION = 1500;
	public static final int BONUS_DURATION = 100;

	/**
	 *  Margin that puts camera to player perspective
	 */
	public static final int FOCAL_POINT = (int)(HEIGHT *(0.75));
	
	/**
	 *  Maximum number of boosts in a time 
	 */
	public static final int EASY_BOOST_NUM = 5;
	public static final int MED_BOOST_NUM = 4;
	public static final int HARD_BOOST_NUM = 3;
	
	/**
	 *  Maximum number of meteor in a time 
	 */
	public static final int EASY_METEOR_NUM = 1;
	public static final int MED_METEOR_NUM = 2;

	/**
	 *  Highscore string position
	 */
	public static final int HIGHSCORE_X = 225;
	public static final int HIGHSCORE_Y = 30;

	/**
	 *  Score string position
	 */
	public static final int SCORE_X = 10;
	public static final int SCORE_Y = 30;
	
	/**
	 *  Score value for each point 
	 */
	public static final int SCORE_VALUE = 10;

	/**
	 *  Image path for different avatar states
	 */
	public static final String AVATAR_LEFT_IMAGE = "/images/WalkLeft.gif";
	public static final String AVATAR_RIGHT_IMAGE = "/images/WalkRight.gif";
	public static final String AVATAR_IMAGE = "/images/stand.png";
	public static final String AVATAR_FLY_IMAGE = "/images/fly.png";
	public static final String AVATAR_FALL_LEFT_IMAGE = "/images/fall_left.png";
	public static final String AVATAR_FALL_IMAGE = "/images/fall.png";
	public static final String AVATAR_HIT_IMAGE = "/images/hit.png";

	/**
	 *  Image path for projectile elements
	 */
	public static final String BOOST_IMAGE = "/images/boost.png";
	public static final String METEOR_IMAGE = "/images/meteorBrown_med3.png";
	
	/**
	 *  Image path for backdrop elements
	 */
	public static final String BACKGROUND_IMAGE = "/images/background.png";
	public static final String FOREGROUND_IMAGE = "/images/foreground.png";
	
	/**
	 *  Image path for menu elements
	 */
	public static final String LOGO_IMAGE = "/images/logo.png";
	public static final String PAUSED_IMAGE = "/images/pause.png";
	public static final String GAME_OVER_IMAGE = "/images/gameOver.png";
	public static final String TUTORIAL_IMAGE = "/images/tutorial.png";
}
