package scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import manager.SceneManager;

public class SettingsState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
    	// Volume Slider
    	Label volumeLabel = new Label("Volume");
        Slider volumeSlider = new Slider(0, 1, 1);
        volumeSlider.setOnMouseReleased(e -> {
            // Save the volume setting or apply it
            System.out.println("Volume set to: " + volumeSlider.getValue());
        });
        
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> sceneManager.setState(new MainMenuState()));
        
        VBox volume = new VBox(volumeLabel, volumeSlider);
        volume.setSpacing(5);
        volume.setStyle("-fx-alignment: center;");
        VBox root = new VBox(volume, backButton);
        root.setStyle("-fx-alignment: center;");
        root.setSpacing(50);
        String cssPath = getClass().getClassLoader().getResource("css/SettingMenu.css").toExternalForm();
        Scene scene = new Scene(root, 800, 600);
        System.out.println("Loading CSS from: " + cssPath);
        scene.getStylesheets().add(cssPath);
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().setTitle("Settings");
        sceneManager.getStage().show();
    }

	@Override
	public void stop(SceneManager sceneManager) {
		System.out.println("Setting is stopping...");
		
	}

}
