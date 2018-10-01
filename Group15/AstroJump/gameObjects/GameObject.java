package gameObjects;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * AstroJumpGame.gameObjects: GameObject class.
 *
 * GameObject class contains coordinates position of object, speed of object,
 * Image of the object, dimension of object and hitBox of the object.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public abstract class GameObject {

    /** The x speed. */
    private int xSpeed;

    /** The y speed. */
    private int ySpeed;

    /** The x. */
    private int x;

    /** The y. */
    private int y;

    private int width;

    private int height;

    /** The object image. */
    private Image objectImage;

    /**
     * Instantiates a new game object.
     */
    public GameObject() {
    	this(0,0);
    }

    /**
     * Instantiates a new game object.
     *
     * @param int x the x
     * @param int y the y
     */
    public GameObject(int x, int y) {
    	this.x = x;
    	this.y = y;
    }

    public void move() {
    	setX(getX() + getXSpeed());
    	setY(getY() + getYSpeed());
    }

    /**
	 * Keeps image within the window.
	 * @param int coord		The current value in the x-/y-coordinate of the image.
	 * @param boolean isX	Checks if 'coord' is passed in x-axis.
	 * @param int min		The lowest value that the image can reach in the axis.
	 * @param int max		The maximum value that the image can reach in the axis.
	 * @return int coord	This is new the x-/y-coordinate with boundary limitations applied.
	 *
	 * If object goes out of bounds on the left side of horizontal axis
	 * object position is directed to the right side of horizontal axis
	 *
	 * Object is bounded in upper and lower screen
	 *
	 */
    public int boundaryCheck(int coord, boolean isX, int min, int max) {
    	if(isX){
    		if (coord <= min)
        		coord = max - 1;
        	if (coord >= max)
        		coord = min + 1;
    	}else{
    		if(coord <= min)
    			coord = min;
    		if(coord >= max)
    			coord = max;
    	}
    	return coord;
    }

    /**
     * Gets the x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x position of avatar.
     *
     * @param int x the new x
     */
    public void setX(int x) {
    	this.x = x;
    }

    /**
     * Sets the y position of avatar.
     *
     * @param int y the new y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the x speed.
     *
     * @return the x speed
     */
    public int getXSpeed() {
        return xSpeed;
    }

    /**
     * Gets the y speed.
     *
     * @return the y speed
     */
    public int getYSpeed() {
        return ySpeed;
    }

    /**
     *
     * @return the width of image
     */
    public int getWidth() {
    	return width;
    }

    /**
     *
     *@return the height of image
     */
    public int getHeight() {
    	return height;
    }

    /**
     * Sets the x speed.
     *
     * @param int xSpeed the new x speed
     */
    public void setXSpeed(int xSpeed) {
    	this.xSpeed = xSpeed;
    }

    /**
     * Sets the y speed.
     *
     * @param int ySpeed the new y speed
     */
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * Sets the image.
     *
     * @param location directory of image file which are defined in GameConfig
     */
    public void setImage(String location) {
    	ImageIcon objectIcon = new ImageIcon(getClass().getResource(location));
        objectImage = objectIcon.getImage();
    }

    /**
     * Gets backdrop image.
     *
     * @return the image
     */
    public Image getImage() {
        return objectImage;
    }

    /**
     * Gets image width and height of the object
     */
    public void getDimensions() {
    	this.height = objectImage.getHeight(null);
    	this.width = objectImage.getWidth(null);
    }

    /**
     * @return rectangle which acts as hitBox of object
     */
    public Rectangle getBounds() {
    	return new Rectangle(x, y, width, height);
    }

}
