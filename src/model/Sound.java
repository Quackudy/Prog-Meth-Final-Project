package model;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	private double volume;
	private MediaPlayer bgmPlayer;
	
	public Sound(double defaultVolume) {
		this.volume = defaultVolume;
		String bgmpath = ClassLoader.getSystemResource("").toString();
		Media bgm = new Media(bgmpath);
		bgmPlayer = new MediaPlayer(bgm);
		bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		setSoundVolume(defaultVolume);
		bgmPlayer.play();
	}
	
	public void setSoundVolume(double volume) {
		bgmPlayer.setVolume(volume);
	}
}
