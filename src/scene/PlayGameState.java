package scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import manager.SceneManager;

public class PlayGameState implements SceneState {

    @Override
    public void start(SceneManager sceneManager) {

        VBox root = new VBox();

        sceneManager.getStage().setScene(new Scene(root, 800, 600));
        sceneManager.getStage().setTitle("Settings");
        sceneManager.getStage().show();
    }
}
