package gameObjects;

import java.util.ArrayList;
import common.GameConfig;
import display.HUD;

/**
 * AstroJumpGame.gameObjects: ProjectileCollection class.
 *
 * Class representing the arrayList of projectiles.
 * Collects boosts and meteors into an array
 *
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 */
public class ProjectileCollection {

	/**
	 * Collection of boosts
	 */
	private ArrayList<Boost> boostList;
	/**
	 * Collection of boosts
	 */
	private ArrayList<Meteor> meteorList;

	/**
	 * Creates arraylist of Boost and Meteor
	 */
	public ProjectileCollection() {
		boostList = new ArrayList<Boost>();
		meteorList = new ArrayList<Meteor>();
	}

    /**
     * Spawns max number of projectiles with randomly generated x and y coordinate
     */
    public void generateProjectiles() {
    	generateBoosts();
    	generateMeteors();
    }

	/**
	 * Generates max number of boost 
	 */
    private void generateBoosts() {
    	int maxBoostNum = GameConfig.EASY_BOOST_NUM;
    	
    	if (HUD.getScore() >= 400)
    		maxBoostNum = GameConfig.MED_BOOST_NUM;
    	else if (HUD.getScore() >= 600)
    		maxBoostNum = GameConfig.HARD_BOOST_NUM;
        for (int boostIndex = 1; boostIndex <= maxBoostNum; boostIndex++) {
        	if (boostList.size() < maxBoostNum)
	        	boostList.add(new Boost());
        }
    }
    
    /**
	 * Generates max number of meteors 
	 */
    private void generateMeteors() {
    	int maxMeteorNum = 0;
    	
    	if (HUD.getScore() >= 100)
    		maxMeteorNum = GameConfig.EASY_METEOR_NUM;
    	else if (HUD.getScore() >= 300)
    		maxMeteorNum = GameConfig.MED_METEOR_NUM;
    	for (int meteorIndex = 1; meteorIndex <= maxMeteorNum; meteorIndex++) {
	        if (meteorList.size() < maxMeteorNum)
		        	meteorList.add(new Meteor ());
    	}
    }

    /**
     * Gets array list of boostList
     * Privacy leak is intended
     *
     * @return boostList collection of boost
     */
    public ArrayList<Boost> getBoostList() {
    	return boostList;
    }
    
    /**
     * Gets array list of meteorList
     * Privacy leak is intended
     *
     * @return meteorList collection of meteorites
     */
    public ArrayList<Meteor> getMeteorList() {
    	return meteorList;
    }

    /**
     * Empties projectile's arrayList
     */
    public void reset() {
    	boostList.clear();
    	meteorList.clear();
    }


}
