package input;

import java.util.concurrent.CopyOnWriteArraySet;

import entities.Player;
import javafx.scene.input.KeyCode;
import model.GameConfigureManager;

public class ShootCommand implements Command {
    private Player player;
    private Direction direction;
    private CopyOnWriteArraySet<KeyCode> activeKeys;
    
    public ShootCommand(Player player, CopyOnWriteArraySet<KeyCode> activeKeys) {
        this.player = player;
        this.activeKeys = activeKeys;
    }

    @Override
    public void execute() {
        player.shootArrow(activeKeys);
    }
}