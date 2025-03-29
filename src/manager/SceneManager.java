package manager;

import javafx.stage.Stage;
import scene.SceneState;

public class SceneManager {
    private static SceneManager instance; // Static instance of SceneManager
    private SceneState currentState;
    private Stage stage;

    // Private constructor to prevent instantiation from outside
    private SceneManager(Stage stage) {
        this.stage = stage;
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SceneManager has not been initialized with a Stage.");
        }
        return instance;
    }

    public static SceneManager getInstance(Stage stage) {
        if (instance == null) {
            instance = new SceneManager(stage); // Create the instance if it doesn't exist
        }
        return instance;
    }

    public void setState(SceneState newState) {
        if (currentState != null) {
            currentState.stop(this);  
        }

        currentState = newState;
        currentState.start(this); 
    }

    public Stage getStage() {
        return stage;
    }
}
