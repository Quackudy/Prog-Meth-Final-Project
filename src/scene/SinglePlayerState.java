package scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import manager.SceneManager;

public class SinglePlayerState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
        Button startButton = new Button("Start Singleplayer Game");
        startButton.setOnAction(e -> {
            // Transition to the actual single-player game logic here
            System.out.println("Starting Singleplayer Game...");
        });

        StackPane root = new StackPane(startButton);
        sceneManager.getStage().setScene(new Scene(root, 800, 600));
        sceneManager.getStage().setTitle("Singleplayer");
        sceneManager.getStage().show();
    }
}
