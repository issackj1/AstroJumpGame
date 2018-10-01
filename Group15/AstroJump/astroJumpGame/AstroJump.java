package astroJumpGame;

import java.awt.Color;
import javax.swing.JFrame;
import common.GameConfig;
import display.GUI;

/**
 * AstroJumpGame.logic: Main class - JFrame.
 * The Class AstroJump.
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class AstroJump extends JFrame {

	/**
	 * Instantiates class.
	 */
    public AstroJump() {
        initFrame();
    }

    /**
     * Initiates frame, adds JPanel Gui to frame 
     * and fits size to the preferred size of 
     */
    private void initFrame() {

        setSize(GameConfig.WIDTH, GameConfig.HEIGHT);
        setResizable(false);

        getContentPane().setBackground(Color.white);
        setTitle("Astro Jump");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new GUI());
        pack();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	AstroJump game = new AstroJump();
    	game.setVisible(true);
    }
}
