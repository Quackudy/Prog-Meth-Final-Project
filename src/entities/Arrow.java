
package entities;

import javafx.scene.image.ImageView;
import input.Direction;
import javafx.scene.layout.Pane;

public class Arrow extends AttackEntity {
    private Direction direction;
    private float speedFactor = 100.0f;
    private float sizeFactor = 1.5f;

    public Arrow(Bow bow) {
        super(bow);
        direction = bow.getDirection();
        setSizeFactor(sizeFactor);
        setSize(20, 20);
        setFaction(Faction.ALLY);
        setDamage(5);
        
        this.loadSprite("arrow_up", "images/arrow/arrow_up.png");
        this.loadSprite("arrow_down", "images/arrow/arrow_down.png");
        this.loadSprite("arrow_left", "images/arrow/arrow_left.png");
        this.loadSprite("arrow_right", "images/arrow/arrow_right.png");
        this.loadSprite("arrow_leftdown", "images/arrow/arrow_leftdown.png");
        this.loadSprite("arrow_leftup", "images/arrow/arrow_leftup.png");
        this.loadSprite("arrow_rightdown", "images/arrow/arrow_rightdown.png");
        this.loadSprite("arrow_rightup", "images/arrow/arrow_rightup.png");

        switch (direction) {
            case UP :
                this.setSprite("arrow_up");
                break;
            case DOWN:
                this.setSprite("arrow_down");
                break;
            case LEFT:
                this.setSprite("arrow_left");
                break;
            case RIGHT:
                this.setSprite("arrow_right");
                break;
            //TODO:ADD DIAGONAL ARROW
            case LEFTDOWN:
            	this.setSprite("arrow_leftdown");
            	break;
            case LEFTUP:
            	this.setSprite("arrow_leftup");
            	break;
            case RIGHTDOWN:
            	this.setSprite("arrow_rightdown");
            	break;
            case RIGHTUP:
            	this.setSprite("arrow_rightup");
            	break;
        }

        this.xPos = bow.getX();
        this.yPos = bow.getY();
    }

    @Override
    public void update(float deltaTime) {
//        this.xPos += speedFactor * deltaTime * (direction==Direction.LEFT ? -1 : (direction==Direction.RIGHT ? 1 : 0));
//        this.yPos += speedFactor * deltaTime * (direction==Direction.UP ? -1 : (direction==Direction.DOWN ? 1 : 0));
    	
    	// Vertical movement (up or down)
        if (direction == Direction.UP) {
            this.yPos -= speedFactor * deltaTime;
        } else if (direction == Direction.DOWN) {
            this.yPos += speedFactor * deltaTime;
        }
        
        // Horizontal movement (left or right)
        if (direction == Direction.LEFT) {
            this.xPos -= speedFactor * deltaTime;
        } else if (direction == Direction.RIGHT) {
            this.xPos += speedFactor * deltaTime;
        }

        // Diagonal movement (combinations of left/right and up/down)
        if (direction == Direction.LEFTUP) {
            this.xPos -= speedFactor * deltaTime;
            this.yPos -= speedFactor * deltaTime;
        } else if (direction == Direction.RIGHTUP) {
            this.xPos += speedFactor * deltaTime;
            this.yPos -= speedFactor * deltaTime;
        } else if (direction == Direction.LEFTDOWN) {
            this.xPos -= speedFactor * deltaTime;
            this.yPos += speedFactor * deltaTime;
        } else if (direction == Direction.RIGHTDOWN) {
            this.xPos += speedFactor * deltaTime;
            this.yPos += speedFactor * deltaTime;
        }
        
        updateSpritePosition();
        updateHitbox();
    }


    @Override
    public boolean isOffScreen() {
        return (getX() < 0 || getX() > 800 || getY() < 0 || getY() > 600);
    }
}
