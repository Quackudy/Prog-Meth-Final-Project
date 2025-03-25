package scene;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.GameLoop;
import manager.SceneManager;
import map.GrassLand;
import model.GameConfigureManager;
import model.GameMode;

public class PlayGameState implements SceneState {
    private GameLoop gameLoop;
    private GrassLand grassLand;

    @Override
    public void start(SceneManager sceneManager) {
        // Initialize the game loop (Not done)
        //gameLoop = new GameLoop();
        
        // Start the game loop (Not done)
        //gameLoop.start();

        
    	grassLand = new GrassLand("images/grass/centerGrass.png");
			

        StackPane root = new StackPane();
        root.getChildren().add(grassLand.createGrassLand()); 

        root.setPrefSize(800, 600); 


        sceneManager.getStage().setScene(new Scene(root, 800, 600)); 
        sceneManager.getStage().setTitle("Playing Game");
        sceneManager.getStage().show();
    }

    @Override
    public void stop(SceneManager sceneManager) {
      
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
}
