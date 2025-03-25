package scene;

import manager.SceneManager;

public interface SceneState {
    void start(SceneManager sceneManager); // Method to start the scene
    void stop(SceneManager sceneManager);
}
