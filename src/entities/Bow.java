
package entities;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bow extends Entities {
    private int frameCounter = 0;
    private int bowSpriteCount = 0;
    private boolean finished = false;
    private boolean facingRight;
    private Player player;

    public Bow(Player player, boolean facingRight) {
        super(player.getX(), player.getY());
        setSizeFactor(player.getSizeFactor());
        this.player = player;
        this.facingRight = facingRight;

        this.loadSprite("bow_1", "images/bow/bow_1.png");
        this.loadSprite("bow_2", "images/bow/bow_2.png");
        this.loadSprite("bow_3", "images/bow/bow_3.png");
        this.loadSprite("bow_4", "images/bow/bow_4.png");
        this.loadSprite("bow_5", "images/bow/bow_5.png");
        this.loadSprite("bow_6", "images/bow/bow_6.png");
        this.loadSprite("bow_7", "images/bow/bow_7.png");
        this.loadSprite("bow_8", "images/bow/bow_8.png");
        this.loadSprite("flip_bow_1", "images/bow/flip_bow_1.png");
        this.loadSprite("flip_bow_2", "images/bow/flip_bow_2.png");
        this.loadSprite("flip_bow_3", "images/bow/flip_bow_3.png");
        this.loadSprite("flip_bow_4", "images/bow/flip_bow_4.png");
        this.loadSprite("flip_bow_5", "images/bow/flip_bow_5.png");
        this.loadSprite("flip_bow_6", "images/bow/flip_bow_6.png");
        this.loadSprite("flip_bow_7", "images/bow/flip_bow_7.png");
        this.loadSprite("flip_bow_8", "images/bow/flip_bow_8.png");

        this.setSprite(facingRight ? "bow_1" : "flip_bow_1");
    }

    @Override
    public void update(float deltaTime) {
        frameCounter++;
        
        //System.out.println("Player X: " + player.getX() + " Player Y: " + player.getY());
        
        this.xPos = player.getX();
        this.yPos = player.getY();
        sprite.setX(xPos);
        sprite.setY(yPos);

        if (frameCounter % 6 == 0) {
            bowSpriteCount = (++bowSpriteCount) % 8;
            this.setSprite((facingRight ? "bow_" : "flip_bow_") + (bowSpriteCount + 1));
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
