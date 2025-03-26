package main;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.SceneManager;
import model.GameConfigureManager;
import scene.MainMenuState;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.getInstance(stage).setState(new MainMenuState()); // Set the main menu as the initial state
        GameConfigureManager.getInstance();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
