package input;

import main.GameLoop;
import manager.SceneManager;
import model.GameConfigureManager;
import scene.MainMenuState;

public class ExitGameCommand implements Command {

	private GameLoop gameLoop;

	public ExitGameCommand(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
        
    }

    @Override
    public void execute() {
        gameLoop.stop();
        SceneManager.getInstance().setState(new MainMenuState());
        GameConfigureManager.getInstance().playBGM("Chrono_Trigger_Theme");
    }
}