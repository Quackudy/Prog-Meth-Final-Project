
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
        }

        this.xPos = bow.getX();
        this.yPos = bow.getY();
    }

    @Override
    public void update(float deltaTime) {
        this.xPos += speedFactor * deltaTime * (direction==Direction.LEFT ? -1 : (direction==Direction.RIGHT ? 1 : 0));
        this.yPos += speedFactor * deltaTime * (direction==Direction.UP ? -1 : (direction==Direction.DOWN ? 1 : 0));
        updateSpritePosition();
        updateHitbox();
    }


    @Override
    public boolean isOffScreen() {
        return (getX() < 0 || getX() > 800 || getY() < 0 || getY() > 600);
    }
}
