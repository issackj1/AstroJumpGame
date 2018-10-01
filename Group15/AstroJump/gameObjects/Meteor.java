package gameObjects;

import common.GameConfig;

/**
 * AstroJumpGame.gameObjects: Meteor class.
 *
 * Class representing the meteor projectiles.
 * Since Meteor class is a child of GameObjects, coordinates, image are derived.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class Meteor extends Projectile {

    /**
     * Instantiates a new boost.
     */
    public Meteor() {
        this(0,0);
    }

    /**
     * Instantiates a new boost.
     *
     * @param int x the x
     * @param int y the y
     */
    public Meteor(int x, int y) {
    	super(x, y);
    	initMeteor();
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
    private void initMeteor() {
        setImage(GameConfig.METEOR_IMAGE);
       	getDimensions();
       	generatePosition();
    }
}
