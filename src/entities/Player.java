
package entities;


import java.util.concurrent.CopyOnWriteArraySet;

import input.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.GameConfigureManager;

public class Player extends Unit  {
    private final float SPEEDFACTOR = 10.0f;
    private final float SIZEFACTOR = 5.0f;
    private float xSpeed = 0;
    private float ySpeed = 0;
    private int walkSpriteCount = 0;
    private int frameCounter = 0;
    private Bow bow = null;
    private boolean facingRight = true;
    private  boolean facingDown = true; // Add FacingDown animation with eyes

    public Player(float xPos, float yPos) {	
        super(xPos, yPos);
        super.setSizeFactor(SIZEFACTOR);
        super.setSize(60, 60);
        super.setFaction(Faction.ALLY);
       
        // Face Back
        this.loadSprite("walk_1", "images/Blue_player/blue_walk_1.png");
        this.loadSprite("walk_2", "images/Blue_player/blue_walk_2.png");
        this.loadSprite("walk_3", "images/Blue_player/blue_walk_3.png");
        this.loadSprite("walk_4", "images/Blue_player/blue_walk_4.png");
        this.loadSprite("walk_5", "images/Blue_player/blue_walk_5.png");
        this.loadSprite("walk_6", "images/Blue_player/blue_walk_6.png");
        this.loadSprite("flip_walk_1", "images/Blue_player/flip_blue_walk_1.png");
        this.loadSprite("flip_walk_2", "images/Blue_player/flip_blue_walk_2.png");
        this.loadSprite("flip_walk_3", "images/Blue_player/flip_blue_walk_3.png");
        this.loadSprite("flip_walk_4", "images/Blue_player/flip_blue_walk_4.png");
        this.loadSprite("flip_walk_5", "images/Blue_player/flip_blue_walk_5.png");
        this.loadSprite("flip_walk_6", "images/Blue_player/flip_blue_walk_6.png");
        //TODO : ADD FaceDown Sprite
        this.loadSprite("fdwalk_1", "images/Blue_player/flip_blue_walk_1_facedown.png"); // flip and normal were swap lol
        this.loadSprite("fdwalk_2", "images/Blue_player/flip_blue_walk_2_facedown.png");
        this.loadSprite("fdwalk_3", "images/Blue_player/flip_blue_walk_3_facedown.png");
        this.loadSprite("fdwalk_4", "images/Blue_player/flip_blue_walk_4_facedown.png");
        this.loadSprite("fdwalk_5", "images/Blue_player/flip_blue_walk_5_facedown.png");
        this.loadSprite("fdwalk_6", "images/Blue_player/flip_blue_walk_6_facedown.png");
        this.loadSprite("fdflip_walk_1", "images/Blue_player/blue_walk_1_facedown.png");
        this.loadSprite("fdflip_walk_2", "images/Blue_player/blue_walk_2_facedown.png");
        this.loadSprite("fdflip_walk_3", "images/Blue_player/blue_walk_3_facedown.png");
        this.loadSprite("fdflip_walk_4", "images/Blue_player/blue_walk_4_facedown.png");
        this.loadSprite("fdflip_walk_5", "images/Blue_player/blue_walk_5_facedown.png");
        this.loadSprite("fdflip_walk_6", "images/Blue_player/blue_walk_6_facedown.png");
        
        this.setSprite("walk_1");
    }

    @Override
    public void update(float deltaTime) {
        normalizeDiagonalSpeed();
        xPos += xSpeed * deltaTime;
        yPos += ySpeed * deltaTime;
        updateSpritePosition(); 
        updateHitbox();

        frameCounter++;

        if (bow != null) {
            bow.update(deltaTime);
            if (bow.isFinished()) {
            	EntityFactory.removeEntity(bow);
                bow = null;
            }
        }
        
        

        if (isWalking() && frameCounter % 10 == 0) {
            walkSpriteCount = (++walkSpriteCount) % 6;
            // TODO : Change all this to handle facingDown case
            if (!facingDown) {
            	if (facingRight) {
            		this.setSprite("walk_" + (walkSpriteCount+1));
            	} else {
            		this.setSprite("flip_walk_" + (walkSpriteCount+1));
            	}
            } else {
            	// Shadow MISSING WHY!??
            	if (facingRight) {
            		this.setSprite("fdwalk_" + (walkSpriteCount+1));
            	} else {
            		this.setSprite("fdflip_walk_" + (walkSpriteCount+1));
            	}
            }
        } else if (!isWalking()) {
        	if (!facingDown) {
        		if (facingRight) {
        			this.setSprite("walk_1");
        		} else {
        			this.setSprite("flip_walk_1");
        		}
        	} else {
        		// Shadow MISSING WHY!??
        		if (facingRight) {
        			this.setSprite("fdwalk_1");
        		} else {
        			this.setSprite("fdflip_walk_1");
        		}
        	}
            walkSpriteCount = 0;
        }

        resetSpeed();
    }

    public void calculateSpeed(Direction direction) {
        switch (direction) {
            case Direction.LEFT:
                xSpeed += -1 * SPEEDFACTOR;
                if (facingRight) {
                    facingRight = false;
                }
                break;
            case Direction.RIGHT:
                xSpeed += 1 * SPEEDFACTOR;
                if (!facingRight) {
                    facingRight = true;
                }
                break;
            case Direction.UP:
            	// TODO: Change facingDown = false;
                ySpeed += -1 * SPEEDFACTOR;
                if (facingDown) {
                	facingDown = false;
                }
                break;
            case Direction.DOWN:
            	// TODO: Change facingDown = true;
                ySpeed += 1 * SPEEDFACTOR;
                if (!facingDown) {
                	facingDown = true;
                }
                break;
        }
    }

    public void normalizeDiagonalSpeed() {
        if (xSpeed != 0 && ySpeed != 0) {
            xSpeed *= 0.7071;
            ySpeed *= 0.7071;
        }
    }

    public void resetSpeed() {
        xSpeed = 0;
        ySpeed = 0;
    }

    public boolean isWalking() {
        return xSpeed != 0 || ySpeed != 0;
    }

    public void shootArrow(CopyOnWriteArraySet<KeyCode> activeKeys) {
        if (bow == null) {
        	
        	// TODO : Fix this
        	if (activeKeys.contains(KeyCode.Y) && activeKeys.contains(KeyCode.G)) {
        		bow = new Bow(this, facingRight, Direction.LEFTUP);
        		bow = EntityFactory.createBow(this, facingRight, Direction.LEFTUP);
        	}
        	else if (activeKeys.contains(KeyCode.Y) && activeKeys.contains(KeyCode.J)) {
        		bow = new Bow(this, facingRight, Direction.RIGHTUP);
        		bow = EntityFactory.createBow(this, facingRight, Direction.RIGHTUP);
        	}
        	else if (activeKeys.contains(KeyCode.H) && activeKeys.contains(KeyCode.G)) {
        		bow = new Bow(this, facingRight, Direction.LEFTDOWN);
        		bow = EntityFactory.createBow(this, facingRight, Direction.LEFTDOWN);
        	}
        	else if (activeKeys.contains(KeyCode.H) && activeKeys.contains(KeyCode.J)) {
        		bow = new Bow(this, facingRight, Direction.RIGHTDOWN);
        		bow = EntityFactory.createBow(this, facingRight, Direction.RIGHTDOWN);
        	}
        	else if (activeKeys.contains(KeyCode.Y)) {
        		bow = new Bow(this, facingRight, Direction.UP);
        		bow = EntityFactory.createBow(this, facingRight, Direction.UP);
        	}
        	else if (activeKeys.contains(KeyCode.H)) {
        		bow = new Bow(this, facingRight, Direction.DOWN);
        		bow = EntityFactory.createBow(this, facingRight, Direction.DOWN);
        	}
        	else if (activeKeys.contains(KeyCode.G)) {
        		bow = new Bow(this, facingRight, Direction.LEFT);
        		bow = EntityFactory.createBow(this, facingRight, Direction.LEFT);
        	}
        	else if (activeKeys.contains(KeyCode.J)) {
        		bow = new Bow(this, facingRight, Direction.RIGHT);
        		bow = EntityFactory.createBow(this, facingRight, Direction.RIGHT);
        	}
        
            GameConfigureManager.getInstance().playsfx("shoot"); // Add Shoot sound (Have to be here to prevent sound bugs)
        }
    }

}
