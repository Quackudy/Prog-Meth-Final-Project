package input;

import main.GameLoop;

public class ExitGameCommand implements Command {

	private GameLoop gameLoop;

	public ExitGameCommand(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
        
    }

    @Override
    public void execute() {
        gameLoop.stop();
    }
}