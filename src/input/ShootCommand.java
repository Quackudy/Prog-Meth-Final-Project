package input;

import entities.Player;
import model.GameConfigureManager;

public class ShootCommand implements Command {
    private Player player;
    private Direction direction;
    
    public ShootCommand(Player player, Direction direction) {
        this.player = player;
        this.direction = direction;
    }

    @Override
    public void execute() {
        player.shootArrow(direction);
    }
}