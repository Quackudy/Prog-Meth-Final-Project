package scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import manager.SceneManager;

public class MultiplayerState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
        Button startButton = new Button("Start Multiplayer Game");
        startButton.setOnAction(e -> {
            // Transition to the actual multiplayer game logic here
            System.out.println("Starting Multiplayer Game...");
        });

        StackPane root = new StackPane(startButton);
        sceneManager.getStage().setScene(new Scene(root, 800, 600));
        sceneManager.getStage().setTitle("Multiplayer");
        sceneManager.getStage().show();
    }
}
