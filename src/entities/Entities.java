package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.GameConfigureManager;

import java.util.HashMap;
import java.util.Map;

public abstract class Entities {
    protected float xPos;
	protected float yPos;  
	protected float sizeFactor;
    protected ImageView sprite; 
    protected Map<String, ImageView> sprites; 

    public Entities(float xPos, float yPos, float sizeFactor) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.sprites = new HashMap<>();
    
        this.sprite = new ImageView();
        sprite.setX(xPos);
        sprite.setY(yPos);
    }

    public abstract void update(float deltaTime);  
    public abstract void render(Pane root); 

    //This is for setting name for a sprite, and you can use setSprite without specifying path again!
    public void loadSprite(String name, String imagePath) {
        Image image = new Image(imagePath);  
        ImageView imageView = new ImageView(image);  
        imageView.setFitWidth(GameConfigureManager.TILESIZE*sizeFactor);
        imageView.setFitHeight(GameConfigureManager.TILESIZE*sizeFactor);
        imageView.setPreserveRatio(false);
        sprites.put(name, imageView); 
    }

    public void setSprite(String name) {
        this.sprite = sprites.get(name);  
        if (this.sprite != null) {
            sprite.setX(xPos);
            sprite.setY(yPos);
        } else {
            System.out.println("Sprite not found: " + name);
        }
    }
    
    public ImageView getSprite() {
        return sprite;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

}
