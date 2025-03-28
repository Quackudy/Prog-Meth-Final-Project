
package manager;

import java.util.ArrayList;
import java.util.List;

import entities.Entities;
import entities.Player;
import javafx.scene.layout.Pane;

public class EntityManager {
    private static EntityManager instance;
    private List<Entities> entities;
    private List<Player> players;

    private EntityManager() {
        entities = new ArrayList<>();
        players = new ArrayList<>();
    }

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
        for (Entities entity : entities) {
            entity.render(root);
        }
    }
}
