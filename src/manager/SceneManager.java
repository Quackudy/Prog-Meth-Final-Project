package manager;

import javafx.stage.Stage;
import scene.SceneState;

public class SceneManager {
    private SceneState currentState;
    private Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void setState(SceneState newState) {
        this.currentState = newState;
        currentState.start(this); // Start the new state
    }

    public Stage getStage() {
        return stage;
    }
}
