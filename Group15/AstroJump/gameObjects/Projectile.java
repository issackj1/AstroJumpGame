package gameObjects;

import java.util.concurrent.ThreadLocalRandom;

import common.GameConfig;

/**
 * AstroJumpGame.gameObjects: Projectile class.
 *
 * Class representing the lethal projectiles.
 * Since Boost class is a child of GameObjects, coordinates, image are derived.
 *
 * Spawns projectiles randomly and collects them in an arrayList
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class Projectile extends GameObject {

	/** 
	 * Holds visibility state of projectile
	 */
	private boolean isVisible;

    /**
     * Instantiates a new boost.
     */
    public Projectile() {
        initProjectile();
    }

    /**
     * Instantiates a new boost.
     *
     * @param int x the x
     * @param int y the y
     */
    public Projectile(int x, int y) {
    	super(x, y);
    	initProjectile();
    }

    /**
     * Creates arrayList of boost once only
     * Initiates isVisible
     *
     * Initializes width and height of image
     *
     * Boost is represented by an image file that is then sized
     * correctly using getDimensions
     */
    private void initProjectile() {
        isVisible = true;
    }
    
    /**
     * Updates the boost position.
     *
     * Sets Boost image visibility to false if object is out of the lower screens bound.
     *
     */
    public void move() {
    	setY(getY() + getYSpeed());
    	if (getY() > GameConfig.HEIGHT)
    		setVisible(false);
    } 

    /**
     * Randomly generate x and y coordinates of projectile
     */
    public void generatePosition() {
    	setX(ThreadLocalRandom.current().nextInt(0, GameConfig.WIDTH - getWidth()));
    	setY(-ThreadLocalRandom.current().nextInt(0, (int) (GameConfig.HEIGHT * (0.75))));
    }

    /**
     * Gets the visibility of object image
     *
     * @return true, if projectile is in bounds screen
     */
    public boolean getVisible() {
    	return isVisible;
    }

    /**
     * Setter method for isVisible
     *
     * @param boolean visible visibility of object
     */
    public void setVisible(boolean visible) {
    	this.isVisible = visible;
    }
}
