package gameObjects;

import common.GameConfig;

/**
 * AstroJumpGame.gameObjects: Avatar class.
 *
 * Class representing the avatar.
 * Since Avatar class is a child of GameObjects, coordinates, image are derived.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class Avatar extends GameObject {

	/**
	 * holds the state if the avatar is already launched from the bottom
	 */
    private boolean avatarLaunched;
    /**
     *  holds the state if avatar is currently boosted
     */
    private boolean avatarBoosted;
    /**
     *  holds the state if the avatar is hit by meteorite
     */
    private boolean avatarHit;
    
    /**
     * Instantiates a new avatar.
     */
    public Avatar() {
        initAvatar();
    }

    /**
     * Initializes the spawn coordinates for the avatar so that it spawns in the bottom centre of the screen
     * Initializes avatarLaunched and Boosted
     *
     * Initializes width and height of image
     *
     * Avatar is represented by an image file that is then sized
     * correctly using getDimensions
     */
    private void initAvatar() {
        setImage(GameConfig.AVATAR_IMAGE);
       	getDimensions();
        setX((GameConfig.WIDTH/2) - (getWidth()/2));
        setY(GameConfig.HEIGHT);

    	avatarLaunched = false;
    	avatarBoosted = false;
    	avatarHit = false;
    }

    /**
     * Creates the initial boost to the avatar at game start
     *
     * LAUNCH_ACCELERATION and BOOST_ACCELERTION are initialized in
     * class GameConfig.
     */
    public void launch() {
    	setYSpeed(-GameConfig.BOOST_ACCELERATION);
    }


    /**
     * Updates avatar object x and y.
     * And puts contraints to avatar.
     */
    public void move() {
    	super.move();
        setX(boundaryCheck(getX(), true, - getWidth(), GameConfig.WIDTH));
        setY(boundaryCheck(getY(), false, getHeight() * 2, GameConfig.HEIGHT - getHeight()));
    }

    /**
     * Checks if avatar is above focal point.
 	 *
     * @return true, if above focal point
     */
    public boolean aboveFocalPoint() {
    	boolean aboveFocalPoint = false;
    	if (getY() < GameConfig.FOCAL_POINT)
    		aboveFocalPoint = true;
    	return aboveFocalPoint;
    }

    /**
     * getter method for avatarLaunched.
     *
     * @return true, if avatar is launched
     */
    public boolean avatarLaunched() {
    	return avatarLaunched;
    }

    /**
     * getter method for avatarBoosted.
     *
     * @return true, if avatar is currently boosted
     */
    public boolean isBoosted() {
    	return avatarBoosted;
    }
    
    /**
     * getter method for avatarHit
     * 
     * @return true, if avatar is hit by meteorite
     */
    public boolean isHit() {
    	return avatarHit;
    }
    
    /**
     * setter method for avatarLaunched.
     *
     * @param boolean avatarLaunched
     */
    public void setAvatarLaunched(boolean avatarLaunched) {
    	this.avatarLaunched = avatarLaunched;
    }
    
    /**
     * setter method for avatarBoosted.
     *
     * @param boolean avatarBoosted
     */
    public void setAvatarBoosted(boolean avatarBoosted) {
    	this.avatarBoosted = avatarBoosted;
    }
    
    /**
     * setter method for for avatarHit
     * 
     * @param boolean avatarHit
     */
    public void setAvatarHit(boolean avatarHit) {
    	this.avatarHit = avatarHit;
    }

    /**
     * Resets the avatar's position to bottom center of screen, sets image to standing, and resets values of instance variables.
     */
    public void reset() {
    	initAvatar();
    }

}
