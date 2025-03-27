package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.GameConfigureManager;

import java.util.HashMap;
import java.util.Map;

public abstract class Entities {
    protected int xPos, yPos;  
    protected ImageView sprite; 
    protected Map<String, ImageView> sprites; 

    public Entities(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

        // Initialize the sprites map
        this.sprites = new HashMap<>();

        // Initialize the sprite (can be set later with subclass-specific logic)
        this.sprite = new ImageView();
        sprite.setX(xPos);
        sprite.setY(yPos);
    }

    // Abstract methods for subclass implementation
    public abstract void update(long deltaTime);  // Update the entity's logic
    public abstract void render(StackPane root);  // Render the entity's sprite

    // Method to load sprite images into the map
    public void loadSprite(String name, String imagePath) {
        Image image = new Image(imagePath);  // Load image from the provided path
        ImageView imageView = new ImageView(image);  // Create ImageView for the sprite
        imageView.setFitWidth(GameConfigureManager.TILESIZE);
        imageView.setFitHeight(GameConfigureManager.TILESIZE);
        imageView.setPreserveRatio(false);
        sprites.put(name, imageView);  // Add it to the map with the provided name
    }

    // Set the current sprite for the entity
    public void setSprite(String name) {
        this.sprite = sprites.get(name);  // Get the sprite by name from the map
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

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }
}
