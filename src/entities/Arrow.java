
package entities;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Arrow extends Entities {
    private String direction;
    private float speedFactor = 100.0f;
    private float sizeFactor = 1.5f;

    public Arrow(Bow bow, String direction) {
        super(bow.getX(), bow.getY());
        setSizeFactor(sizeFactor);
        this.direction = direction;

        this.loadSprite("arrow_up", "images/arrow/arrow_up.png");
        this.loadSprite("arrow_down", "images/arrow/arrow_down.png");
        this.loadSprite("arrow_left", "images/arrow/arrow_left.png");
        this.loadSprite("arrow_right", "images/arrow/arrow_right.png");

        switch (direction) {
            case "up":
                this.setSprite("arrow_up");
                break;
            case "down":
                this.setSprite("arrow_down");
                break;
            case "left":
                this.setSprite("arrow_left");
                break;
            case "right":
                this.setSprite("arrow_right");
                break;
        }

        this.xPos = bow.getX();
        this.yPos = bow.getY();
    }

    @Override
    public void update(float deltaTime) {
        this.xPos += speedFactor * deltaTime * (direction.equals("left") ? -1 : (direction.equals("right") ? 1 : 0));
        this.yPos += speedFactor * deltaTime * (direction.equals("up") ? -1 : (direction.equals("down") ? 1 : 0));
        sprite.setX(xPos);
        sprite.setY(yPos);
    }

    @Override
    public void render(Pane root) {
        ImageView sprite = this.getSprite();
        if (!root.getChildren().contains(sprite)) {
            root.getChildren().add(sprite);
        }
    }

    public boolean isFinished() {
        return (getX() < 0 || getX() > 800 || getY() < 0 || getY() > 600);
    }
}
