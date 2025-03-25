package scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import manager.SceneManager;

public class SettingsState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
        Slider volumeSlider = new Slider(0, 1, 1);
        volumeSlider.setOnMouseReleased(e -> {
            // Save the volume setting or apply it
            System.out.println("Volume set to: " + volumeSlider.getValue());
        });
        
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> sceneManager.setState(new MainMenuState()));

        VBox root = new VBox(volumeSlider, backButton);
        root.setStyle("-fx-alignment: center;");
        root.setSpacing(20);
        sceneManager.getStage().setScene(new Scene(root, 800, 600));
        sceneManager.getStage().setTitle("Settings");
        sceneManager.getStage().show();
    }

	@Override
	public void stop(SceneManager sceneManager) {
		System.out.println("Setting is stopping...");
		
	}

}
