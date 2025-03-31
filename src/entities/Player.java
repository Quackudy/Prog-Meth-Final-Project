
package entities;


import input.Direction;
import javafx.scene.layout.Pane;

public class Player extends Unit  {
    private final float SPEEDFACTOR = 10.0f;
    private final float SIZEFACTOR = 5.0f;
    private float xSpeed = 0;
    private float ySpeed = 0;
    private int walkSpriteCount = 0;
    private int frameCounter = 0;
    private Bow bow = null;
    private boolean facingRight = true;

    public Player(float xPos, float yPos) {	
        super(xPos, yPos);
        super.setSizeFactor(SIZEFACTOR);
        super.setSize(60, 60);
        super.setFaction(Faction.ALLY);
       
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
            this.setSprite((facingRight ? "walk_" : "flip_walk_") + (walkSpriteCount + 1));
        } else if (!isWalking()) {
            this.setSprite(facingRight ? "walk_1" : "flip_walk_1");
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
                ySpeed += -1 * SPEEDFACTOR;
                break;
            case Direction.DOWN:
                ySpeed += 1 * SPEEDFACTOR;
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

    public void shootArrow(Direction direction) {
        if (bow == null) {
        	//bow = new Bow(this, facingRight,direction);
            bow = EntityFactory.createBow(this, facingRight, direction);
        }
    }

}
