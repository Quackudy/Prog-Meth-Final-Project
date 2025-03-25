package map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GrassLand {
    private Image tileImage; // Single tile image
    private int rows = 15;     // Number of rows in the grid
    private int cols = 20;     // Number of columns in the grid
    private double tileWidth = 40;  // Width of each tile (in pixels)
    private double tileHeight = 40; // Height of each tile (in pixels)

    public GrassLand(String tileImagePath) {
        String imagePath = ClassLoader.getSystemResource(tileImagePath).toString();
        this.tileImage = new Image(imagePath);
    }

    // Method to create the GrassLand grid with adjustable tile size
    public GridPane createGrassLand() {
        GridPane grid = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                ImageView tileView = new ImageView(tileImage); // Create new ImageView for each tile

                // Scale the tile to the specified width and height
                tileView.setFitWidth(tileWidth);  // Set the width of each tile
                tileView.setFitHeight(tileHeight); // Set the height of each tile
                tileView.setPreserveRatio(true);  // Keep the aspect ratio intact

                // Add the tile to the grid at the correct position
                grid.add(tileView, col, row);
            }
        }

        return grid;
    }

    // Setter methods to change tile width and height if needed
    public void setTileSize(double width, double height) {
        this.tileWidth = width;
        this.tileHeight = height;
    }
}
