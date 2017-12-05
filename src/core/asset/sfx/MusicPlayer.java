package core.asset.sfx;

import core.asset.AssetCache;
import core.asset.AssetID;
import javafx.scene.media.AudioClip;

public class MusicPlayer {
	
	private AudioClip audioClip;
	boolean isPlaying = false;
	
	public MusicPlayer(AssetID id, int cycle) {
		audioClip = AssetCache.getAudio(id);
		audioClip.setCycleCount(cycle);
	}

	public void play() {
		audioClip.play();
		isPlaying = true;
	}
	
	public void stop() {
		if (isPlaying)
			audioClip.stop();
		isPlaying = false;
	}
	
	public void setVolume(double vol) {
		audioClip.setVolume(vol);
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}

}
