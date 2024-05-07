package adv.java.rio.Otto;

import java.io.File;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class Song {
	
	String _name;
	
	public Song(String name) {
		_name = name;
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return _name;
	}
	
	
	public String getParent() {
		File file = new File(_name);
		System.out.println(file.getParentFile().toString());
		return file.getParentFile().toString();
	}
	
	
	synchronized public void playThisSong() throws InterruptedException {
		System.out.println(_name + " is working");
	
//		TimeUnit.SECONDS.sleep(1);
		TimeUnit.MILLISECONDS.sleep(100);
		
		
////		below does not work
//		Media playSong = new Media(file.toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(playSong);
//		mediaPlayer.play();
	}
	
	
}
