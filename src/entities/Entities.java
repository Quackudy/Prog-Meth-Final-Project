
package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.GameConfigureManager;

import java.util.HashMap;
import java.util.Map;

public abstract class Entities {
    protected Faction faction = Faction.NEUTRAL;
    protected float xPos, yPos;
    protected double hitboxWidth, hitboxHeight;
    protected float sizeFactor = 1.0f;
    protected Rectangle2D hitbox;
    protected ImageView sprite;
    protected Map<String, ImageView> sprites;

    public Entities(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.sprites = new HashMap<>();
        this.sprite = new ImageView();
        
        this.hitboxWidth = getSprite().getFitWidth();
        this.hitboxHeight = getSprite().getFitHeight();
        
        this.hitbox = new Rectangle2D(xPos-hitboxWidth/2, yPos-hitboxHeight/2, hitboxWidth, hitboxHeight);
    }
    
    public Rectangle2D getHitbox() {
        return hitbox;
    }

    public void updateHitbox() {
        this.hitbox = new Rectangle2D(xPos-hitboxWidth/2, yPos-hitboxHeight/2, hitboxWidth, hitboxHeight);
    }
    
    public void setSize(double width, double height) {
    	this.hitboxWidth = width;
    	this.hitboxHeight = height;
    }

    public abstract void update(float deltaTime);
    
    public void render(Pane root) {
        if (!root.getChildren().contains(sprite)) {
            root.getChildren().add(sprite);
        }
    };

    public void loadSprite(String name, String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(GameConfigureManager.TILESIZE * sizeFactor);
        imageView.setFitHeight(GameConfigureManager.TILESIZE * sizeFactor);
        imageView.setPreserveRatio(false);
        sprites.put(name, imageView);
    }

    public void updateSpritePosition() {
        if (sprite != null && sprite.getImage() != null) {
            sprite.setX(xPos - sprite.getFitWidth() / 2);
            sprite.setY(yPos - sprite.getFitHeight() / 2);
        }
    }

    public void setSprite(String name) {
        ImageView newSprite = sprites.get(name);
        if (newSprite != null) {
            this.sprite = newSprite;
            updateSpritePosition();
        } else {
            System.err.println("Sprite not found: " + name + " for entity: " + this.getClass().getSimpleName());
        }
    }

    public ImageView getSprite() {
        return sprite;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public float getSizeFactor() {
        return sizeFactor;
    }

    public void setSizeFactor(float sizeFactor) {
        this.sizeFactor = sizeFactor;
    }
}
