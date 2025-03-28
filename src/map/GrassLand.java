
package map;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.GameConfigureManager;

public class GrassLand {
    private int rows = 15;
    private int cols = 20;
    private double tileSize = GameConfigureManager.TILESIZE; // Each tile is 64x64

    private Tile[][] tiles;

    public GrassLand() {
        tiles = new Tile[rows][cols];
    }

    public StackPane createGrassLand() {
        GridPane grid = new GridPane();
        GridPane decorationGrid = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TileType type = TileType.GRASS;

                Tile tile = new Tile("images/grass/centerGrass.png", type, tileSize);
                tiles[row][col] = tile;

                grid.add(tile.getImageView(), col, row);

                // Add decorations on some tiles
                if (Math.random() < 0.1) { // 10% chance to add a decoration
                    String Number = String.valueOf((int) (Math.random() * 18) + 1) ;
					if (Integer.parseInt(Number) < 10) {
						Number = "0" + Number;
					}
                    String Path = "images/deco/" + Number + ".png";
                    Tile decoration = new Tile(Path, TileType.TREE, tileSize);
                    decorationGrid.add(decoration.getImageView(), col, row);
                }
            }
        }

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(grid, decorationGrid);

        return stackPane;
    }

    public Tile getTile(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null; // Out of bounds
        }
        return tiles[row][col];
    }
}
