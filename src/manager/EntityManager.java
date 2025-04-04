
package manager;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entities.AttackEntity;
import entities.Unit;
import entities.Entities;
import entities.Faction;
import entities.NormalEnemy;
import entities.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.GameConfigureManager;

public class EntityManager {
    private static EntityManager instance;
    //CopyOnWrite is for preventing concurrent thread problem
    private final List<Entities> entities = new CopyOnWriteArrayList<>();
    private final List<Player> players = new CopyOnWriteArrayList<>();
    private final List<Unit> enemies = new CopyOnWriteArrayList<>();
    private final List<AttackEntity> attackEntities = new CopyOnWriteArrayList<>();
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
        if (entity instanceof AttackEntity) {
        	attackEntities.add((AttackEntity) entity);
        }
        if (entity instanceof Unit) {
        	enemies.add((Unit) entity);
        }
    }

    public void removeEntity(Entities entity) {
        entities.remove(entity);
        if (entity instanceof Player) {
            players.remove(entity);
        }
        if (entity instanceof AttackEntity) {
        	attackEntities.remove(entity);
        }
        if (entity instanceof Unit) {
        	enemies.remove(entity);
        }
    }

    public List<Entities> getEntities() {
        return entities;
    }
    
    public List<Unit> getEnemies() {
    	return enemies;
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    public List<AttackEntity> getAttackEntities() {
    	return attackEntities;
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
    
	public void removeOffScreenAttackEntity() {
		for (AttackEntity AE : getAttackEntities()) {
			if (AE.isOffScreen()) {
				removeEntity(AE);
			}
		}
	}
	
	public void attackHit() {
	    for (AttackEntity AE : getAttackEntities()) {
	        for (Entities entity : entities) {
	            if (entity instanceof Unit && checkCollision(AE, entity)) {
	                if ((AE.getFaction() == Faction.ALLY && entity.getFaction() == Faction.ENEMY) || 
	                    (AE.getFaction() == Faction.ENEMY && entity.getFaction() == Faction.ALLY)) {
	                    
	                    Unit unit = (Unit) entity;
	                    unit.takeDamage(AE.getDamage());
	                    removeEntity(AE);
	                    GameConfigureManager.getInstance().playsfx("arrowhit");

	                    if (unit.isDead()) {
	                        removeEntity(unit);
	                        GameConfigureManager.getInstance().playsfx("mondead");
	                    }
	                    
	                    break; // Stop checking after hitting one target
	                }
	            }
	        }
	    }
	}
	
	// TODO : Add playerAttacked method
	public void playerAttacked() {
		for (Player p : getPlayers()) {
			for (Entities entity : entities) {
				if (entity instanceof NormalEnemy && checkCollision(p, entity)) {
					
					// TODO : ADD delay between hit
					NormalEnemy enemy = (NormalEnemy) entity;

	                if (enemy.canAttack()) {
	                    p.takeDamage(enemy.getDmg());
	                    enemy.registerAttack();
	                    System.out.println("HIT");

	                    if (p.isDead()) {
	                        System.out.println("DEAD");
	                    }
	                }

				}
			}
		}
	}
	
    public static boolean checkCollision(Entities ent1, Entities ent2) {
    	Rectangle2D rect1 = ent1.getHitbox();
    	Rectangle2D rect2 = ent2.getHitbox();
    	return rect1.intersects(rect2);
    }

    public static void destroyInstance() {
        instance = null; // Removes reference, GC can clean up
    }
    
    
    /////Methods below are for debug
    

	private void renderDebugElements(Pane root, Entities entity) {
	    // Existing code for rendering sprite debug elements
		
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
	
//		System.out.println(entity.getHitbox().getHeight());
	    // New code for rendering hitbox debug elements
	    Rectangle hitboxBorder = new Rectangle(
	        entity.getHitbox().getMinX(),
	        entity.getHitbox().getMinY(),
	        entity.getHitbox().getWidth(),
	        entity.getHitbox().getHeight()
	    );
	    hitboxBorder.setStroke(Color.BLACK);
	    hitboxBorder.setFill(Color.TRANSPARENT);
	
	    if (!root.getChildren().contains(hitboxBorder)) {
	        root.getChildren().add(hitboxBorder);
	    }
	}
	
	    
    
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
