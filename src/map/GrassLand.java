package map;

import javafx.scene.layout.GridPane;
import model.GameConfigureManager;

public class GrassLand {
    private int rows = 15;
    private int cols = 20;
    private double tileSize = GameConfigureManager.TILESIZE; // Each tile is 64x64

    private Tile[][] tiles;

    public GrassLand() {
        tiles = new Tile[rows][cols];
    }

    public GridPane createGrassLand() {
        GridPane grid = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TileType type = TileType.GRASS;

                Tile tile = new Tile("images/grass/centerGrass.png", type, tileSize);
                tiles[row][col] = tile;

                grid.add(tile.getImageView(), col, row);
            }
        }

        return grid;
    }

    public Tile getTile(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null; // Out of bounds
        }
        return tiles[row][col];
    }
}
