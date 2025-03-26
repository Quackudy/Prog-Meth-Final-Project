package model;


public class GameConfigureManager {
    // Singleton instance
    private static GameConfigureManager instance;


    private GameMode gameMode = GameMode.SINGLEPLAYER; // Default is SinglePlayer

    private double volume = 1.0; // Default volume level
    
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
    	// TO DO: Call Method from Sound class
    	sound.setSoundVolume(volume);
        this.volume = volume;
    }


}
