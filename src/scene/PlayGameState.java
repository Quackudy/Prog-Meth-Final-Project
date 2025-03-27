package scene;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import main.GameLoop;
import manager.SceneManager;
import map.GrassLand;

public class PlayGameState implements SceneState {
    private GameLoop gameLoop;
    private GrassLand grassLand;

    @Override
    public void start(SceneManager sceneManager) {
        
    	grassLand = new GrassLand();
			

        StackPane root = new StackPane();
        root.getChildren().add(grassLand.createGrassLand()); 
        
        // Initialize the game loop (Not done)
        gameLoop = new GameLoop(root);
        
        // Start the game loop (Not done)
        gameLoop.start();


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
