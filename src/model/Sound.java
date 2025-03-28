package model;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	private double volume;
	private MediaPlayer bgmPlayer;
	
	public Sound(double defaultVolume) {
		this.volume = defaultVolume;
		
		// Play BGM (I commented it out because it's too loud lmao)
//		String bgmpath = ClassLoader.getSystemResource("sound/testbgm.mp3").toString();
//		Media bgm = new Media(bgmpath);
//		bgmPlayer = new MediaPlayer(bgm);
//		bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//		setSoundVolume(defaultVolume);
//		bgmPlayer.play();
	}
	
	public void setSoundVolume(double volume) {
		this.volume = volume;
//		bgmPlayer.setVolume(volume);
	}
	
	public double getVolume() {
		return this.volume;
	}
	
	public void ButtonHover() {
		String sfxpath = ClassLoader.getSystemResource("sound/btnhover.wav").toString();
		Media sfx = new Media(sfxpath);
		MediaPlayer sfxPlayer = new MediaPlayer(sfx);
		sfxPlayer.setVolume(getVolume());
		sfxPlayer.play();		
	}
	
	public void ButtonClick() {
		String sfxpath = ClassLoader.getSystemResource("sound/btnclick.wav").toString();
		Media sfx = new Media(sfxpath);
		MediaPlayer sfxPlayer = new MediaPlayer(sfx);
		sfxPlayer.setVolume(getVolume());
		sfxPlayer.play();		
	}
	// TO-DO : Sound effect [Shoot, die, getHit, etc.]
}
