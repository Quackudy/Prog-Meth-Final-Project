package scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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

        Pane rootBackground = new Pane();
        //rootBackGround.getChildren().add(grassLand.createGrassLand());
        
        Pane root = new Pane();

        // Initialize the game loop
        gameLoop = new GameLoop(root);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 800, 600);
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().setTitle("Playing Game");
        sceneManager.getStage().show();

        // Start the game loop
        gameLoop.start();

        // Set key event handlers after the scene is set
        root.requestFocus();
     
        root.setOnKeyPressed(event -> gameLoop.handleKeyPressed(event));
        root.setOnKeyReleased(gameLoop::handleKeyReleased);	
    }

    @Override
    public void stop(SceneManager sceneManager) {
      
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
}
