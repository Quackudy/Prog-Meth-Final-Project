
package main;

import java.util.concurrent.CopyOnWriteArraySet;
import manager.EntityManager;
import manager.SceneManager;
import scene.MainMenuState;
import entities.EntityFactory;
import entities.Player;
import input.Direction;
import input.ExitGameCommand;
import input.InputHandler;
import input.MovePlayerCommand;
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
    private final CopyOnWriteArraySet<KeyCode> activeKeys = new CopyOnWriteArraySet<>();
    Thread gameThread = new Thread(this);

    public GameLoop(Pane root, boolean debugMode) {
        this.root = root;
        initializeEntities();
        initializeInputHandler();
        entityManager.setDebugMode(debugMode);
    }

    private void initializeInputHandler() {
    	//come here to fix code for multiplayer later
    	
        Player player = entityManager.getPlayer(0);
        
        inputHandler.bindKey(KeyCode.W, new MovePlayerCommand(player, Direction.UP));
        inputHandler.bindKey(KeyCode.A, new MovePlayerCommand(player, Direction.LEFT));
        inputHandler.bindKey(KeyCode.D, new MovePlayerCommand(player, Direction.RIGHT));
        inputHandler.bindKey(KeyCode.S, new MovePlayerCommand(player, Direction.DOWN));
        
        inputHandler.bindKey(KeyCode.Y, new ShootCommand(player, Direction.UP));
        inputHandler.bindKey(KeyCode.G, new ShootCommand(player, Direction.LEFT));
        inputHandler.bindKey(KeyCode.J, new ShootCommand(player, Direction.RIGHT));
        inputHandler.bindKey(KeyCode.H, new ShootCommand(player, Direction.DOWN));
        
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
        gameThread.start();
    }

    public void stop() {
        running = false;
        // Delay to not let thread access the to be destroyed variable
        new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                EntityManager.destroyInstance();
                SceneManager.getInstance().setState(new MainMenuState());
            });
        }).start();
    }

    @Override
    public void run() {
    	
    	//Uncomment to print list of initialized entity (only at the start of the game)
    	//entityManager.printEntities();
    	
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
        entityManager.updateAll(deltaTime);
        entityManager.removeOffScreenAttackEntity();
        entityManager.attackHit();
    }

    private void render() {
        entityManager.renderAll(root);
    }
}
