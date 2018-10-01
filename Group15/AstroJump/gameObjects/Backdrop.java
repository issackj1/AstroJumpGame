package gameObjects;

/**
 * AstroJumpGame.gameObjects: Backdrop class.
 *
 * Class representing the backdrop, used by menu and background images.
 * Since backdrop class is a child of GameObjects, coordinates, images are derived.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class Backdrop extends GameObject {

	/**
	 * Puts the image position in JFrame.
	 *
	 * @param location
	 */
	public Backdrop(String location) {
		setImage(location);
		setY(-20);
	}
}
