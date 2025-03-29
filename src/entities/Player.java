
package entities;


import javafx.scene.layout.Pane;

public class Player extends Entities {
    private float speedFactor = 10.0f;
    private float sizeFactor = 5.0f;
    private float xSpeed = 0;
    private float ySpeed = 0;
    private int walkSpriteCount = 0;
    private int frameCounter = 0;
    private Bow bow = null;
    private boolean facingRight = true;

    public Player(float xPos, float yPos) {	
        super(xPos, yPos);
        super.setSizeFactor(sizeFactor);
        

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
        sprite.setX(xPos);
        sprite.setY(yPos);

        frameCounter++;

        if (bow != null) {
            bow.update(deltaTime);
            if (bow.isFinished()) {
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

    @Override
    public void render(Pane root) {
        //ImageView sprite = this.getSprite();
        if (!root.getChildren().contains(sprite)) {
            root.getChildren().add(sprite);
        }
        if (bow != null) {
            bow.render(root);
        }
    }

    public void calculateSpeed(String direction) {
        switch (direction) {
            case "Left":
                xSpeed += -1 * speedFactor;
                if (facingRight) {
                    facingRight = false;
                }
                break;
            case "Right":
                xSpeed += 1 * speedFactor;
                if (!facingRight) {
                    facingRight = true;
                }
                break;
            case "Up":
                ySpeed += -1 * speedFactor;
                break;
            case "Down":
                ySpeed += 1 * speedFactor;
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

    public void shootArrow() {
        if (bow == null) {
            bow = new Bow(this, facingRight);
        }
    }
}
