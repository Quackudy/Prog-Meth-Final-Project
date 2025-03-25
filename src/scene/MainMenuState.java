package scene;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;  // Use VBox for vertical alignment
import manager.SceneManager;

public class MainMenuState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
        Button singlePlayerButton = new Button("Singleplayer");
        singlePlayerButton.setOnAction(e -> sceneManager.setState(new SinglePlayerState()));

        Button multiplayerButton = new Button("Multiplayer");
        multiplayerButton.setOnAction(e -> sceneManager.setState(new MultiplayerState()));

        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> sceneManager.setState(new SettingsState()));

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> System.exit(0));

        // Use VBox for vertical arrangement
        VBox root = new VBox(10, singlePlayerButton, multiplayerButton, settingsButton, quitButton);
        root.setPadding(new Insets(5, 5, 5, 5)); // Set padding around the VBox
        root.setStyle("-fx-alignment: center;"); // Center the buttons horizontally
        String cssPath = getClass().getClassLoader().getResource("css/MainMenu.css").toExternalForm();
        root.getStylesheets().add(cssPath);

        
        sceneManager.getStage().setScene(new Scene(root, 800, 600));
        sceneManager.getStage().setTitle("Main Menu");
        sceneManager.getStage().show();
    }
}
