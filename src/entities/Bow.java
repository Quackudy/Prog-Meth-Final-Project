
package entities;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bow extends Entities {
    private int frameCounter = 0;
    private int bowSpriteCount = 0;
    private boolean finished = false;

    public Bow(float xPos, float yPos, float sizeFactor) {
        super(xPos, yPos, sizeFactor);

        this.loadSprite("bow_1", "images/bow/bow_1.png");
        this.loadSprite("bow_2", "images/bow/bow_2.png");
        this.loadSprite("bow_3", "images/bow/bow_3.png");
        this.loadSprite("bow_4", "images/bow/bow_4.png");
        this.loadSprite("bow_5", "images/bow/bow_5.png");
        this.loadSprite("bow_6", "images/bow/bow_6.png");
        this.loadSprite("bow_7", "images/bow/bow_7.png");
        this.loadSprite("bow_8", "images/bow/bow_8.png");

        this.setSprite("bow_1");
    }

    @Override
    public void update(float deltaTime) {
        frameCounter++;
        if (frameCounter % 10 == 0) {
            bowSpriteCount = (++bowSpriteCount) % 8;
            this.setSprite("bow_" + (bowSpriteCount + 1));
            if (bowSpriteCount == 7) {
                finished = true;
            }
        }
    }

    @Override
    public void render(Pane root) {
        ImageView sprite = this.getSprite();
        if (!root.getChildren().contains(sprite)) {
            root.getChildren().add(sprite);
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
