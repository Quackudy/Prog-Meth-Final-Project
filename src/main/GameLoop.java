package main;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import input.Direction;
import input.ExitGameCommand;
import input.InputHandler;
import input.MovePlayerCommand;
import input.ShootCommand;
import manager.EntityManager;
import manager.SceneManager;
import scene.MainMenuState;

import java.util.concurrent.CopyOnWriteArraySet;

import entities.EntityFactory;
import entities.Player;

public class GameLoop {
    private boolean running = false;
    private final int TARGET_FPS = 60;
    private Pane root;
    private EntityManager entityManager = EntityManager.getInstance();
    private InputHandler inputHandler = new InputHandler();
    private final CopyOnWriteArraySet<KeyCode> activeKeys = new CopyOnWriteArraySet<>();

    public GameLoop(Pane root, boolean debugMode) {
        this.root = root;
        initializeEntities();
        initializeInputHandler();
        entityManager.setDebugMode(debugMode);
    }

    private void initializeInputHandler() {
        Player player = entityManager.getPlayer(0);
        
        inputHandler.bindKey(KeyCode.W, new MovePlayerCommand(player, Direction.UP));
        inputHandler.bindKey(KeyCode.A, new MovePlayerCommand(player, Direction.LEFT));
        inputHandler.bindKey(KeyCode.D, new MovePlayerCommand(player, Direction.RIGHT));
        inputHandler.bindKey(KeyCode.S, new MovePlayerCommand(player, Direction.DOWN));
        
        inputHandler.bindKey(KeyCode.Y, new ShootCommand(player, activeKeys));
        inputHandler.bindKey(KeyCode.G, new ShootCommand(player, activeKeys));
        inputHandler.bindKey(KeyCode.J, new ShootCommand(player, activeKeys));
        inputHandler.bindKey(KeyCode.H, new ShootCommand(player, activeKeys));
        
        inputHandler.bindKey(KeyCode.ESCAPE, new ExitGameCommand(this));
    }

    private void initializeEntities() {
        EntityFactory.createPlayer(300, 400);
        EntityFactory.createNormalEnemy(200, 300);
        EntityFactory.createNormalEnemy(400, 300);
        EntityFactory.createNormalEnemy(700, 600);
    }

    public void handleKeyPressed(KeyEvent event) {
        activeKeys.add(event.getCode());
    }

    public void handleKeyReleased(KeyEvent event) {
        activeKeys.remove(event.getCode());
    }

    public void start() {
        running = true;
        new AnimationTimer() {
        	long lastUpdateTime = 0; // Store the last update time

        	@Override
        	public void handle(long now) {
        	    if (!running) {
        	        stop();
        	        return;
        	    }

        	    if (lastUpdateTime != 0) {
        	        long deltaTime = now - lastUpdateTime;  // Time difference in nanoseconds
        	        // Now convert deltaTime to seconds if needed:
        	        float deltaTimeInSeconds = deltaTime / 100000000.0f;
        	        update(deltaTimeInSeconds);
        	    }
        	    
        	    lastUpdateTime = now;  // Update the last update time
        	    root.getChildren().clear();
        	    render();
        	}
        }.start();
    }

    public void stop() {
        running = false;
        // No need for Platform.runLater(), the scene and entities can be updated directly here
		Platform.runLater(() -> {
			root.getChildren().clear();
	        EntityManager.destroyInstance();
	        SceneManager.getInstance().setState(new MainMenuState());
		});

    }

    private void update(float deltaTime) {
        for (KeyCode keyCode : activeKeys) {
            inputHandler.handleInput(keyCode);
        }
        entityManager.updateAll(deltaTime);
    }

    private void render() {
        entityManager.renderAll(root);
    }
}
