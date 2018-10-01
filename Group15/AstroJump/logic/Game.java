package logic;

import java.awt.Rectangle;
import java.util.ArrayList;
import common.GameConfig;
import common.Types.STATE;
import display.HUD;
import gameObjects.Avatar;
import gameObjects.Backdrop;
import gameObjects.Boost;
import gameObjects.Meteor;
import gameObjects.Projectile;
import gameObjects.ProjectileCollection;

/**
 * Main logic class which handles and connects all the classes.
 * 
 * AstroJumpGame.logic: Game class.
 * @author Group15
 * @version 2.0
 * @since 03-21-2017
 *
 */
public class Game {
	
	/**
	 * The score
	 */
	private Score score;

	/** 
	 * The hud.
	 */
	private HUD hud;

    /** 
     * The avatar.
     */
    private Avatar avatar;
    
    /**
     * The projectileCollection
     */
    private ProjectileCollection projectileCollection;

    /** 
     * The background.
     */
    private Backdrop background;

    /**
     * Holds current state of game
     */
    private STATE gameState;
    
    /**
     * Length of boost duration
     */
    private int boostDuration;
    
    /**
     * Times boost duration
     */
    private int counter;

	/**
	 * Instantiates a new game.
	 */
	public Game() {
        initGame();
    }

	/**
	 * Class constructor.
	 * Initiates HUD, game, avatar, boost, gameState, background, projectileCollection and score
	 */
    private void initGame() {
        gameState = STATE.ScreenIntro;
        avatar = new Avatar();
        projectileCollection = new ProjectileCollection();
        background = new Backdrop(GameConfig.BACKGROUND_IMAGE);
        hud = new HUD();
        score = new Score();
        score.readFile();
        HUD.setHighScore(score.getFileScore());
    }

    /**
     * Updates the current state of the game.
     */
    public void update() {
        updateObjects();
        boostDuration();
        intersectionCheck();
        avatarDie();
    }

	/**
	 * Updates avatar, boosts and meteors.
	 * Continually generates projectiles
	 */
	private void updateObjects() {
        avatar.move();
		projectileCollection.generateProjectiles();
		updateBoost();
		updateMeteor();
	}
	
	/**
	 * Moves boosts and deletes boosts that are off-screen.
	 */
	private void updateBoost() {
		ArrayList<Boost> boostList = projectileCollection.getBoostList();
		ArrayList<Boost> toRemove = new ArrayList<Boost>();
		for (Object boostElement : boostList) {
    		Boost aBoost = (Boost) boostElement;
    		if (aBoost.getVisible())
    			scrollObjects(aBoost, GameConfig.BOOST_SCROLL_UP_SPEED, GameConfig.GRAVITY);
    		else 
    			toRemove.add(aBoost);
    	}
		boostList.removeAll(toRemove);
    }
	
	/**
	 * Moves meteor(s) and deletes meteor(s) that are off-screen.
	 */
	private void updateMeteor() {
		ArrayList<Meteor> meteorList = projectileCollection.getMeteorList();
		ArrayList<Meteor> toRemove = new ArrayList<Meteor>();
		for (Object meteorElement : meteorList) {
    		Meteor aMeteor = (Meteor) meteorElement;
    		if (aMeteor.getVisible())
    		   	scrollObjects(aMeteor, GameConfig.METEOR_SCROLL_UP_SPEED, GameConfig.METEOR_FALL_SPEED);
    		else 
    			toRemove.add(aMeteor);
    	}
		meteorList.removeAll(toRemove);
    }
	
	/**
	 * Moves objects falling down on the screen.
	 * 
	 * @param projectile			The kind of objects that are falling down.
	 * @param projectileScrollSpeed	Objects' fall speed when avatar is at focal point of screen.
	 * @param projectileFallSpeed	Objects' fall speed when screen is not moving.
	 */
	private void scrollObjects(Projectile projectile, int projectileScrollSpeed, int projectileFallSpeed) {
    	if (avatar.aboveFocalPoint() && avatar.getYSpeed() < 0 && gameState != STATE.Paused) {
    		projectile.setYSpeed(projectileFallSpeed * 2);
    	}
    	else if (avatar.aboveFocalPoint() && avatar.getYSpeed() == GameConfig.GRAVITY && gameState != STATE.Paused){
    		projectile.setYSpeed(-projectileScrollSpeed);
    	}
    	else
    		projectile.setYSpeed(0);
    	projectile.move();
    }
	
	/**
	 * Limits the boost duration of the avatar.
	 */
	private void boostDuration() {
        if (avatar.isBoosted() && !avatar.isHit()) {
        	incrementCounter();
        	avatar.launch();
        	if (counter >= boostDuration) {
        		restartCounter();
        		avatar.setImage(GameConfig.AVATAR_FALL_IMAGE);
        		avatar.setAvatarBoosted(false);
        		avatar.setYSpeed(GameConfig.GRAVITY);
            	boostDuration = GameConfig.BOOST_DURATION;
        		
        	}
        }
    }

	/**
	 * Adds counter to boost duration counter.
	 */
	private void incrementCounter() {
		if (gameState != STATE.Paused)
			counter += 10;
	}

	/**
	 * Collision checker.
	 * Checks if the avatar and boost or meteor is in contact.
	 */
	public void intersectionCheck() {
        Rectangle avatarHitBox = avatar.getBounds();
        ArrayList<Boost> boostList = projectileCollection.getBoostList();
        ArrayList<Meteor> meteorList = projectileCollection.getMeteorList();
        
		for (Object boostElement : boostList) {
    		Boost aBoost = (Boost) boostElement;
            Rectangle boostHitBox  = aBoost.getBounds();
            if (avatarHitBox.intersects(boostHitBox)){
                aBoost.setVisible(false);
            	avatar.setImage(GameConfig.AVATAR_FLY_IMAGE);
                avatar.setAvatarBoosted(true);
                
                counter -= GameConfig.BONUS_DURATION;
                HUD.setScore(HUD.getScore() + GameConfig.SCORE_VALUE);
                if(HUD.getScore() > HUD.getHighScore()) {
                	HUD.setHighScore(HUD.getScore());
        		}
            }
        }

		for (Object meteorElement : meteorList) {
    		Meteor aMeteor = (Meteor) meteorElement;
            Rectangle meteorHitBox  = aMeteor.getBounds();
            if (avatarHitBox.intersects(meteorHitBox)) {
                aMeteor.setVisible(false);
            	avatar.setImage(GameConfig.AVATAR_HIT_IMAGE);
        		avatar.setYSpeed(GameConfig.GRAVITY);
        		avatar.setAvatarHit(true);
                avatar.setAvatarBoosted(false);
            }
        }
    }

    /**
     * Changes avatar position and image.
     */
    public void moveLeft() {
    	if (gameState != STATE.Paused)
    		avatar.setXSpeed(-GameConfig.AVATAR_SPEED);

		if (avatar.getY() < (GameConfig.HEIGHT - avatar.getHeight()))
			avatar.setImage(GameConfig.AVATAR_FALL_LEFT_IMAGE);
		else
			avatar.setImage(GameConfig.AVATAR_LEFT_IMAGE);
    }

    /**
     * Changes gameState to 'Play', avatar position and image.
     */
    public void moveUp() {
    	if (!avatar.avatarLaunched() && gameState == STATE.Intro){
    		boostDuration = GameConfig.LAUNCH_DURATION;
    		avatar.setAvatarLaunched(true);
			avatar.setAvatarBoosted(true);
    		gameState = STATE.Play;
			avatar.setImage(GameConfig.AVATAR_FLY_IMAGE);
			start();
    	}
    }

    /**
     * Changes avatar position and image.
     */
    public void moveRight() {
    	if (gameState != STATE.Paused)
    		avatar.setXSpeed(GameConfig.AVATAR_SPEED);

        avatar.setImage(GameConfig.AVATAR_FALL_IMAGE);
		if (avatar.getY() < (GameConfig.HEIGHT - avatar.getHeight()))
			avatar.setImage(GameConfig.AVATAR_FALL_IMAGE);
		else
			avatar.setImage(GameConfig.AVATAR_RIGHT_IMAGE);
    }

    /**
     * Changes image.
     */
    public void stopMove() {
    	avatar.setXSpeed(0);
    	if (avatar.getY() >= (GameConfig.HEIGHT - avatar.getHeight()))
    		avatar.setImage(GameConfig.AVATAR_IMAGE);
    }

	/**
	 * Restarts counter.
	 */
	private void restartCounter() {
        	counter = 0;
    }

	/**
	 * Checks if the avatar fell to bottom of screen.
	 */
	public void avatarDie() {
    	if (avatar.getY() >= (GameConfig.HEIGHT - avatar.getHeight()) && avatar.avatarLaunched() && avatar.getYSpeed() == GameConfig.GRAVITY) {
    		gameOver();
    	}
    }

	/**
	 * Reinitializes to default values.
	 */
	private void reset() {
		avatar.reset();
		hud.reset();
		projectileCollection.reset();
	}

	/**
	 * Restarts game.
	 */
	public void restart() {
		if (gameState == STATE.GameOver) {
			gameState = STATE.Intro;
    		restartCounter();
    		reset();
		}
	}

	/**
	 * Change game state to play or pause.
	 */
	public void start() {
		if (gameState == STATE.ScreenIntro)
			gameState = STATE.Intro;
		if (gameState == STATE.Paused && !avatar.avatarLaunched())
			gameState = STATE.Intro;
		else if (gameState == STATE.Paused)
			gameState = STATE.Play;
		if (!avatar.isBoosted())
			avatar.setYSpeed(GameConfig.GRAVITY);
		else
			avatar.setYSpeed(-GameConfig.BOOST_ACCELERATION);
    }

	/**
	 * Pauses game.
	 */
	public void pause() {
		if (gameState != STATE.GameOver && gameState != STATE.Intro)
	    	gameState = STATE.Paused;
	    	avatar.setYSpeed(0);
    }

	/**
	 * Goes to 'Game Over' state.
	 */
	public void gameOver() {
    	gameState = STATE.GameOver;
    	if(HUD.getScore() > HUD.getHighScore()) {
        	HUD.setHighScore(HUD.getScore()); 
		}
    	score.writeFile("Hello", HUD.getHighScore());

    }

	/**
	 * Setter for 'gameState'.
	 *
	 * @param gameState	State of game.
	 */
	public void setGameState(STATE gameState) {

    	this.gameState = gameState;
    }

    /**
     * Getter for 'gameState'.
     *
     * @return gameState	State of game.
     */
    public STATE getGameState() {
    	return gameState;
    }

	/**
	 * Getter for 'avatar'.
	 *
	 * @return avatar	The position and image of the avatar.
	 */
	public Avatar getAvatar(){
		return avatar;
	}
	
	/**
	 * Getter for 'ProjectileCollection'.
	 * 
	 * @return projectileCollection	The projectile falling down.
	 */
	public ProjectileCollection getProjectileCollection() {
		return projectileCollection;
	}
	
	/**
	 * Getter for 'background'.
	 *
	 * @return background	The background image.
	 */
	public Backdrop getBackground(){
		return background;
	}

	/**
	 * Getter for hud.
	 * @return hud
	 */
	public HUD getHUD(){
		return hud;
	}
}
