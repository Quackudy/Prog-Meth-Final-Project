package scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;  // Use VBox for vertical alignment
import javafx.scene.text.Font;
import manager.SceneManager;
import model.GameConfigureManager;
import model.GameMode;
import model.Sound;

public class MainMenuState implements SceneState {
    @Override
    public void start(SceneManager sceneManager) {
    	// Title
    	Label Title = new Label("Oiia Leveling");

        Font.loadFont(getClass().getResource("/fonts/pixel.otf").toExternalForm(), 10);
        
        
    	// Button
        Button singlePlayerButton = new Button("Singleplayer");
        singlePlayerButton.setOnAction(e -> {sceneManager.setState(new PlayGameState());
        										GameConfigureManager.getInstance().setGameMode(GameMode.SINGLEPLAYER);
        										GameConfigureManager.getInstance().playsfx("click");
        									});
        singlePlayerButton.setOnMouseEntered(e -> {
        	GameConfigureManager.getInstance().playsfx("hover");
        });

        Button multiplayerButton = new Button("Multiplayer");
        multiplayerButton.setOnAction(e -> {sceneManager.setState(new PlayGameState());
												GameConfigureManager.getInstance().setGameMode(GameMode.MULTIPLAYER);
												GameConfigureManager.getInstance().playsfx("click");
											});
        multiplayerButton.setOnMouseEntered(e -> {
        	GameConfigureManager.getInstance().playsfx("hover");
        });
        
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> {
        	sceneManager.setState(new SettingsState());
        	GameConfigureManager.getInstance().playsfx("click");
        });
        settingsButton.setOnMouseEntered(e -> {
        	GameConfigureManager.getInstance().playsfx("hover");
        });

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
        	// GameConfigureManager.getInstance().playsfx("click"); -> Sound not playing because it's close too fast need to add timer if want to hear it.
        	System.exit(0);
        });
        quitButton.setOnMouseEntered(e -> {
        	GameConfigureManager.getInstance().playsfx("hover");
        });
        
        
        // Use VBox for vertical arrangement
        VBox buttons = new VBox(singlePlayerButton, multiplayerButton, settingsButton, quitButton);
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(5, 5, 5, 5)); // Set padding around the VBox
        buttons.setStyle("-fx-alignment: center;"); // Center the buttons horizontally
        AnchorPane root = new AnchorPane(Title, buttons);
        
        // Set buttons' position in AnchorPane (optional)
        AnchorPane.setTopAnchor(buttons, 200.0);
        AnchorPane.setLeftAnchor(buttons, 60.0);
        AnchorPane.setTopAnchor(Title, 60.0);  
        AnchorPane.setLeftAnchor(Title, 40.0); 
   
        root.setStyle("-fx-alignment: center;"); // Center the buttons horizontally
        String cssPath = getClass().getClassLoader().getResource("css/MainMenu.css").toExternalForm();
        root.getStylesheets().add(cssPath);
        

        // Add Background
        String path = ClassLoader.getSystemResource("images/MainMenuImg/chatGPTbg.png").toString();
        Image image = new Image(path);
        ImageView bgView = new ImageView(image);
        bgView.setTranslateX(30); 
        bgView.setFitWidth(900);
        bgView.setFitHeight(700);
        StackPane pane = new StackPane();
        pane.getChildren().addAll(bgView, root);
      
        
        sceneManager.getStage().setScene(new Scene(pane, 800, 600));
        sceneManager.getStage().setTitle("Main Menu");
        sceneManager.getStage().show();
    }

	@Override
	public void stop(SceneManager sceneManager) {
		// This do nothing na
		
	}
}
