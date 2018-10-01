package gameObjects;

import common.GameConfig;

/**
 * AstroJumpGame.gameObjects: Boost class.
 *
 * Class representing the boost projectiles.
 * Since Boost class is a child of GameObjects, coordinates, image are derived.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class Boost extends Projectile {

    /**
     * Instantiates a new boost.
     */
    public Boost() {
        this(0,0);
    }

    /**
     * Instantiates a new boost.
     *
     * @param int x the x
     * @param int y the y
     */
    public Boost(int x, int y) {
    	super(x, y);
    	initBoost();
    }

    /**
     * Creates arrayList of boost once only
     * Initiates isVisible
     *
     * Initializes width and height of image
     *
     * Boost is represented by an image file that is then sized
     * correctly using getDimensions
     * 
     * Generates a random position for the boost element
     */
    private void initBoost() {
        setImage(GameConfig.BOOST_IMAGE);
       	getDimensions();
       	generatePosition();
    }
    
}
