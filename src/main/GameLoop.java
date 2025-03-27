package main;

import java.util.HashSet;
import java.util.Set;

import entities.Player;
import input.InputHandler;
import input.MoveDownCommand;
import input.MoveLeftCommand;
import input.MoveRightCommand;
import input.MoveUpCommand;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLoop implements Runnable {
    private boolean running = false;
    private final int TARGET_FPS = 60;
    private long lastLoopTime;
    private Pane root;
    private Player player = new Player(300, 400, 2);
    private InputHandler inputHandler = new InputHandler();
    private final Set<KeyCode> activeKeys = new HashSet<>();
    Thread gameThread = new Thread(this);

    public GameLoop(Pane root) {
        this.root = root;
        initializeInputHandler();
    }

    private void initializeInputHandler() {
        inputHandler.bindKey(KeyCode.W, new MoveUpCommand(player));
        inputHandler.bindKey(KeyCode.S, new MoveDownCommand(player));
        inputHandler.bindKey(KeyCode.A, new MoveLeftCommand(player));
        inputHandler.bindKey(KeyCode.D, new MoveRightCommand(player));

    }
    
    
	public void handleKeyPressed(KeyEvent event) {
		activeKeys.add(event.getCode());
	}
	
	public void handleKeyReleased(KeyEvent event) {
		activeKeys.remove(event.getCode());
	}

    public void start() {
        running = true;
        gameThread.start();
    }
    
    public void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        lastLoopTime = System.nanoTime();
        while (running) {
            long now = System.nanoTime();
            float deltaTime = (float) (now - lastLoopTime) / 100000000; // Convert to milliseconds or second idk but it works
            lastLoopTime = now;

            update(deltaTime);
            Platform.runLater(() -> {
                root.getChildren().clear();  
                render();  
            });
    
           

            try {
                long sleepTime = (1000 / TARGET_FPS) - (System.nanoTime() - now) / 1000000;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void update(float deltaTime) {
        for (KeyCode keyCode : activeKeys) {
            inputHandler.handleInput(keyCode);
        }
        player.update(deltaTime);
    }

    private void render() {
        player.render(root);
    }
}
