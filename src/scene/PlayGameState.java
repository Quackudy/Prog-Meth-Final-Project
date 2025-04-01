package scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.GameLoop;
import manager.SceneManager;
import map.GrassLand;
import model.GameConfigureManager;

public class PlayGameState implements SceneState {
    private GameLoop gameLoop;
    private GrassLand grassLand;

    @Override
    public void start(SceneManager sceneManager) {
    	
    	GameConfigureManager.getInstance().playBGM("Chrono_Trigger_battle");
    	
        grassLand = new GrassLand();

        Pane rootBackground = new Pane();
        rootBackground.getChildren().add(grassLand.createGrassLand());

        Pane root = new Pane();

        // boolean parameter is for debug mode (currently, just add borders to sprites)
        gameLoop = new GameLoop(root, true);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rootBackground, root);

        Scene scene = new Scene(stackPane, 800, 600);
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().setTitle("Playing Game");
        sceneManager.getStage().show();

        gameLoop.start();

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
