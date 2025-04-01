package model;


public class GameConfigureManager {
    // Singleton instance
    private static GameConfigureManager instance;

    public final static int TILESIZE = 40;

    private GameMode gameMode = GameMode.SINGLEPLAYER; // Default is SinglePlayer

    private double volume = 0.5;
    
    private Sound sound;

    // Private constructor to prevent instantiation
    private GameConfigureManager() {
    	sound = new Sound(this.volume);
    }

    // Public method to get the instance of the Singleton
    public static GameConfigureManager getInstance() {
        if (instance == null) {
            instance = new GameConfigureManager();
        }
        return instance;
    }

    // Getter and setter for gameMode
    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    // Getter and setter for volume
    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
    	sound.setSoundVolume(volume);
        this.volume = volume;
    }
    
    public void playsfx(String sfx) {
    	if (sfx.equals("hover")) {
    		sound.ButtonHover();
    	}
    	else if (sfx.equals("click")) {
    		sound.ButtonClick();
    	}
    }
    
	public void playBGM(String name) {
		sound.playBGM(name);
	}


}