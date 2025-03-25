package main;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.SceneManager;
import scene.MainMenuState;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.setState(new MainMenuState()); // Set the main menu as the initial state
    }

    public static void main(String[] args) {
        launch(args);
    }
}
