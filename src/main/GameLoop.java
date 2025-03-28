
package main;

import java.util.HashSet;
import java.util.Set;

import manager.EntityManager;
import entities.EnemyFactory;
import entities.NormalEnemy;
import entities.Player;
import input.InputHandler;
import input.MoveDownCommand;
import input.MoveLeftCommand;
import input.MoveRightCommand;
import input.MoveUpCommand;
import input.ShootCommand;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLoop implements Runnable {
    private boolean running = false;
    private final int TARGET_FPS = 60;
    private long lastLoopTime;
    private Pane root;
    private EntityManager entityManager = EntityManager.getInstance();
    private InputHandler inputHandler = new InputHandler();
    private final Set<KeyCode> activeKeys = new HashSet<>();
    Thread gameThread = new Thread(this);

    public GameLoop(Pane root) {
        this.root = root;
        initializeEntities();
        initializeInputHandler();
    }

    private void initializeInputHandler() {
    	//come here to fix code for multiplayer later
    	
        Player player = entityManager.getPlayer(0);
        inputHandler.bindKey(KeyCode.W, new MoveUpCommand(player));
        inputHandler.bindKey(KeyCode.S, new MoveDownCommand(player));
        inputHandler.bindKey(KeyCode.A, new MoveLeftCommand(player));
        inputHandler.bindKey(KeyCode.D, new MoveRightCommand(player));
        inputHandler.bindKey(KeyCode.G, new ShootCommand(player));
    }

    private void initializeEntities() {
        Player player = new Player(300, 400);
        entityManager.addEntity(player);
        NormalEnemy enemy = (NormalEnemy) EnemyFactory.createEnemy("normal", 400, 300);
        entityManager.addEntity(enemy);
    
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
        Set<KeyCode> keysCopy = new HashSet<>(activeKeys);
        for (KeyCode keyCode : keysCopy) {
            inputHandler.handleInput(keyCode);
        }
        entityManager.updateAll(deltaTime);
    }

    private void render() {
        entityManager.renderAll(root);
    }
}
