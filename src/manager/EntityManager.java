
package manager;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import entities.Entities;
import entities.Player;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class EntityManager {
    private static EntityManager instance;
    //CopyOnWrite is for preventing concurrent thread problem
    private final List<Entities> entities = new CopyOnWriteArrayList<>();
    private final List<Player> players = new CopyOnWriteArrayList<>();
    private boolean debugMode = false;

    private EntityManager() {}

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public void addEntity(Entities entity) {
        entities.add(entity);
        if (entity instanceof Player) {
            players.add((Player) entity);
        }
    }

    public void removeEntity(Entities entity) {
        entities.remove(entity);
        if (entity instanceof Player) {
            players.remove(entity);
        }
    }

    public List<Entities> getEntities() {
        return entities;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int index) {
        if (index >= 0 && index < players.size()) {
            return players.get(index);
        }
        return null;
    }

    public void updateAll(float deltaTime) {
        for (Entities entity : entities) {
            entity.update(deltaTime);
        }
    }

    public void renderAll(Pane root) {
    	entities.sort((e1, e2) -> Float.compare(e1.getY(), e2.getY()));
    	
        for (Entities entity : entities) {
            entity.render(root);
			if (debugMode) {
				renderDebugElements(root, entity);
			}
        }
    }

    public static void destroyInstance() {
        instance = null; // Removes reference, GC can clean up
    }
    
    private void renderDebugElements(Pane root, Entities entity) {
        Rectangle border = new Rectangle(
            entity.getSprite().getX(),
            entity.getSprite().getY(),
            entity.getSprite().getFitWidth(),
            entity.getSprite().getFitHeight()
        );
        border.setStroke(Color.RED);
        border.setFill(Color.TRANSPARENT);

        Circle center = new Circle(
            entity.getX(),
            entity.getY(),
            2,
            Color.RED
        );

        if (!root.getChildren().contains(border)) {
            root.getChildren().add(border);
        }
        if (!root.getChildren().contains(center)) {
            root.getChildren().add(center);
        }
    }
    
    
    /////Methods below are for debug
    
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	public void printEntities() {
	    System.out.println("=== EntityManager Debug Info ===");
	    
	    System.out.println("Entities List:");
	    for (Entities entity : entities) {
	        System.out.println(" - " + entity.getClass().getSimpleName() + " at (" + entity.getX() + ", " + entity.getY() + ")");
	    }

	    System.out.println("\nPlayers List:");
	    for (Player player : players) {
	        System.out.println(" - Player at (" + player.getX() + ", " + player.getY() + ")");
	    }

	    System.out.println("================================");
	}
}
