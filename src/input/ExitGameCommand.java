package input;

import main.GameLoop;
import model.GameConfigureManager;

public class ExitGameCommand implements Command {

	private GameLoop gameLoop;

	public ExitGameCommand(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
        
    }

    @Override
    public void execute() {
        gameLoop.stop();
        GameConfigureManager.getInstance().playBGM("Chrono_Trigger_Theme");
    }
}