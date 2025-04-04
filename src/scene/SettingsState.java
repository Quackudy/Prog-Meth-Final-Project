package scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import manager.SceneManager;
import model.GameConfigureManager;

public class SettingsState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
    	// Title
    	Label title = new Label("SETTINGS");
    	title.setId("label-title");
    	
    	// Volume Slider
    	double lastVol = GameConfigureManager.getInstance().getVolume();
    	Label volumeLabel = new Label("Volume");
        Slider volumeSlider = new Slider(0, 1, lastVol); // Default at 0.5
        // Make it real-time when sliding
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            GameConfigureManager.getInstance().setVolume(volumeSlider.getValue());
        });

        
        // Back Button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> {
        	sceneManager.setState(new MainMenuState());
        	GameConfigureManager.getInstance().playsfx("click");
        });
        backButton.setOnMouseEntered(e -> {
        	GameConfigureManager.getInstance().playsfx("hover");
        });
        
        
        // V-Box for vertical Arrangement
        VBox volume = new VBox(volumeLabel, volumeSlider);
        volume.setSpacing(5);
        volume.setStyle("-fx-alignment: center;");
        VBox root = new VBox(title, volume, backButton);
        root.setStyle("-fx-alignment: center;");
        root.setSpacing(50);
        
        // Add Background
        String path = ClassLoader.getSystemResource("images/MainMenuImg/chatGPTbg.png").toString();
        Image image = new Image(path);
        ImageView bgView = new ImageView(image);
        bgView.setTranslateX(30); 
        bgView.setFitWidth(900);
        bgView.setFitHeight(700);
        StackPane pane = new StackPane();
        pane.getChildren().addAll(bgView, root);
        
        // Add all to scene
        String cssPath = getClass().getClassLoader().getResource("css/SettingMenu.css").toExternalForm();
        Scene scene = new Scene(pane, 800, 600);
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
