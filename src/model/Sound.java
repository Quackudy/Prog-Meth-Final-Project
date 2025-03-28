package model;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	private double volume;
	private MediaPlayer bgmPlayer;
	
	public Sound(double defaultVolume) {
		this.volume = defaultVolume;
		
		// Play BGM
		String bgmpath = getClass().getClassLoader().getResource("sound/testbgm.mp3").toString();
		Media bgm = new Media(bgmpath);
		bgmPlayer = new MediaPlayer(bgm);
		bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		setSoundVolume(defaultVolume);
		bgmPlayer.play();
	}
	
	public void setSoundVolume(double volume) {
		bgmPlayer.setVolume(volume);
	}
	
	public void SoundEffect1() {
		// TO-DO : Sound effect [Shoot, die, getHit, etc.]
	}
}
