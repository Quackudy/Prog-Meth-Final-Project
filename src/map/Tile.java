package map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {
    private ImageView imageView;
    private TileType type;  // Enum to define different tile types

    public Tile(String imagePath, TileType type, double tileSize) {
        String path = ClassLoader.getSystemResource(imagePath).toString();
        Image image = new Image(path);
        this.imageView = new ImageView(image);

        // Set size
        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);
        imageView.setPreserveRatio(false); // Ensure it scales correctly

        this.type = type;

    }


    public ImageView getImageView() {
        return imageView;
    }

    public TileType getType() {
        return type;
    }
}
