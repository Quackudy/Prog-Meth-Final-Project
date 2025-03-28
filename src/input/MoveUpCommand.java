package input;

import entities.Player;

public class MoveUpCommand implements Command {
    private Player player;

    public MoveUpCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.calculateSpeed("Up");

    }
}