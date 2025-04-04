package scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.GameLoop;
import manager.EntityManager;
import manager.SceneManager;
import map.GrassLand;
import model.GameConfigureManager;

public class PlayGameState implements SceneState {
    private GameLoop gameLoop;
    private GrassLand grassLand;

    @Override
    public void start(SceneManager sceneManager) {
    	
    	GameConfigureManager.getInstance().playBGM("Chrono_Trigger_battle");
    	
        grassLand = new GrassLand();

        Pane rootBackground = new Pane();
        rootBackground.getChildren().add(grassLand.createGrassLand());

        Pane root = new Pane();

        // boolean parameter is for debug mode (currently, just add borders to sprites)
        gameLoop = new GameLoop(root, true);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rootBackground, root);

        Scene scene = new Scene(stackPane, 800, 600);
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().setTitle("Playing Game");
        sceneManager.getStage().show();

        gameLoop.start();

        root.requestFocus();

        root.setOnKeyPressed(event -> gameLoop.handleKeyPressed(event));
        root.setOnKeyReleased(gameLoop::handleKeyReleased);
        
        // Popup when Player Died
        EntityManager.getInstance().setOnPlayerDeath(() -> {
            showDeathPopup(stackPane); // Add popup to a stackPane
            gameLoop.stop();
        });

    }

    @Override
    public void stop(SceneManager sceneManager) {
      
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
    
    // TODO : Fix Styling (Because I style from chat lmao need to redo it)
    
    private void showDeathPopup(StackPane stackPane) {
        // Create a semi-transparent full-screen overlay
        StackPane overlay = new StackPane();
        overlay.setPrefSize(800, 600);
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);");


        // Create the popup box
        VBox popupBox = new VBox(15);
        popupBox.setStyle("-fx-background-color: #5e0603; -fx-background-radius: 15px; -fx-padding: 30px; -fx-border-radius: 15px; -fx-border-color: #000000; -fx-border-width: 2px;");
        popupBox.setAlignment(Pos.CENTER);
        popupBox.setMaxWidth(350);
        popupBox.setMaxHeight(200);

        // Add label
        Label gameOverLabel = new Label("Ha NOOB!");
        gameOverLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0, 2, 2);");

        // Exit button with some styling
        Button exitButton = new Button("Back to Main Menu");
        exitButton.setStyle("-fx-background-color: #ff3366; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-padding: 10px 20px;");

        exitButton.setOnAction(e -> {
        	SceneManager.getInstance().setState(new MainMenuState());
        	GameConfigureManager.getInstance().playBGM("Chrono_Trigger_Theme");
        });
        
        //Button Style
        exitButton.setOnMouseEntered(event -> {
            exitButton.setStyle("-fx-background-color: #ff1a47; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-padding: 10px 20px;");
        });

        // Reset the button style on mouse exit
        exitButton.setOnMouseExited(event -> {
            exitButton.setStyle("-fx-background-color: #ff3366; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-padding: 10px 20px;");
        });

        // Click effect - add visual feedback on click (e.g., darker shade)
        exitButton.setOnMousePressed(event -> {
            exitButton.setStyle("-fx-background-color: #e6003c; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-padding: 10px 20px;");
        });

        // Reset the button style after click is released
        exitButton.setOnMouseReleased(event -> {
            exitButton.setStyle("-fx-background-color: #ff3366; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-padding: 10px 20px;");
        });
    
        // Add to popupBox
        popupBox.getChildren().addAll(gameOverLabel, exitButton);

        // Add popupBox to overlay (centered because StackPane)
        overlay.getChildren().add(popupBox);
        StackPane.setAlignment(popupBox, Pos.CENTER); // Make sure it's centered

        // Add overlay to the main game StackPane
        stackPane.getChildren().add(overlay);
    }



}
