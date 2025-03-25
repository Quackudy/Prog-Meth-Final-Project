package scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;  // Use VBox for vertical alignment
import manager.SceneManager;
import model.GameConfigureManager;
import model.GameMode;

public class MainMenuState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
    	// Button
        Button singlePlayerButton = new Button("Singleplayer");
        singlePlayerButton.setOnAction(e -> {sceneManager.setState(new PlayGameState());
        										GameConfigureManager.getInstance().setGameMode(GameMode.SINGLEPLAYER);
        									});

        Button multiplayerButton = new Button("Multiplayer");
        multiplayerButton.setOnAction(e -> {sceneManager.setState(new PlayGameState());
												GameConfigureManager.getInstance().setGameMode(GameMode.MULTIPLAYER);
											});

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

	@Override
	public void stop(SceneManager sceneManager) {
		// This do nothing na
		System.out.println("Main Menu is stopping...");
	}
}
