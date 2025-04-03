package input;

import entities.Player;

public class MovePlayerCommand implements Command {
    private Player player;
    private Direction direction;

    public MovePlayerCommand(Player player, Direction direction) {
        this.player = player;
        this.direction = direction;
    }

    @Override
    public void execute() {
        player.calculateSpeed(direction);
        //TO-DO : Add walking sound -> Add when load sprite might make more sense
    }
}