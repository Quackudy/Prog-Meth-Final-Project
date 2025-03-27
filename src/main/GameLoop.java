package main;

import entities.Player;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;

public class GameLoop implements Runnable {
    private boolean running = false;
    private final int TARGET_FPS = 60; // You can adjust this based on your needs
    private long lastLoopTime;
    private StackPane root;
    
    private Player player = new Player(300,400);
    Thread gameThread = new Thread(this);
    
    public GameLoop(StackPane root) {
        this.root = root;
    }

    public void start() {
        running = true;
        gameThread.start(); // Start the loop in a new thread
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
    	
        lastLoopTime = System.nanoTime();
        while (running) {
            long now = System.nanoTime();
            long deltaTime = now - lastLoopTime;
            lastLoopTime = now;

            // Update game state (e.g., player, enemies, etc.)
            update(deltaTime);

            // Render game scene
            Platform.runLater(() -> render());

            // Control frame rate (sleep to maintain the target FPS)
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
    
    private StackPane getRoot() {
    	return this.root;
    }

    private void update(long deltaTime) {
        // Update game objects (e.g., player position, enemies)
        // You would call methods to move the player, check for collisions, etc.
    	player.update(deltaTime);
    }

    private void render() {
        // Render game scene to screen (redraw everything, e.g., players, enemies)
        // For example: gameScene.render();
    	player.render(getRoot());
    }
}
