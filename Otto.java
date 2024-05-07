package adv.java.rio.Otto;

import java.time.LocalDate;
//import java.time.MonthDay;
//import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Date;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import java.io.IOException;
//import java.util.Set;
//import java.lang.Object;

//import javax.print.attribute.standard.Media;

public class Otto {

	private List<Song> _songs = new ArrayList<>();
	private Map<Song, Integer> _songValue = new HashMap<>();

	public void addSongsToList() {
		File javaSongs = new File("javaSongs");
		List<File> listFiles = new ArrayList<>();
		getFiles(javaSongs).forEach(x -> listFiles.add(x));
		listFiles.remove(javaSongs);

		for (int i = 0; i < listFiles.size(); i++) {
			if (listFiles.get(i).canExecute()) {
				Song newSong = new Song(listFiles.get(i).toString());
				_songs.add(newSong);
			}
		}

	}

//	need to take another look, think its done
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void playRandomSong() throws IOException, InterruptedException {
		Random rand = new Random();
		List<Song> listOfSongs = new ArrayList<>();

		for (int i = 0; i < _songs.size(); i++) {
			Song addSong = _songs.get(i);
			for (int j = 0; j < _songValue.get(addSong); j++) {
				listOfSongs.add(addSong);
			}
		}
		int randomSong = rand.nextInt(listOfSongs.size());
//		System.out.println("list " + listOfSongs.size() + " _songs " + _songs.size() + _songValue.size());
		play(listOfSongs.get(randomSong));

	}

//	completed
	/**
	 * plays the selected song
	 * 
	 * @param song
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void play(Song song) throws IOException, InterruptedException {

		if (!_songs.contains(song))
			return;
		_songs.remove(song);
		_songs.add(0, song);
		song.playThisSong();
		addValue();
	}

//	Completed
	public void play(String name) throws IOException, InterruptedException {
		Song song = new Song(name);
		play(song);

	}

//	completed
	public void playRequested(Song song) throws InterruptedException {

		if (!_songs.contains(song))
			return;

		song.playThisSong();
	}

//	should be done
	public void addValue() {
//		if (_songs.size() < 1000)
//			return;
		
		resetValue();

		int size = _songs.size();
		int top50 = size / 2;
		int top10 = (size/10 * 9);
		int top5 = (size/100 * 95);
		
		int value = 0;
		for (int i = _songs.size()-1; i > top50; i--) {
			value = _songValue.get(_songs.get(i));
			_songValue.put(_songs.get(i), value + 1);
		}
		for (int i = _songs.size()-1; i > top10; i--) {
			value = _songValue.get(_songs.get(i));
			_songValue.put(_songs.get(i), value + 1);
		}
		for (int i = _songs.size()-1; i > top5; i--) {
			value = _songValue.get(_songs.get(i));
			_songValue.put(_songs.get(i), value + 1);
		}
	}

	/**
	 * Resets the Value of each song to 1 so the map doesnt grow forever and take up
	 * memory. Resets at the start of each month.
	 */
	public void resetValue() {
		if (getTotalSongValue() < (_songValue.size() * 1000))
			return;

		LocalDate currentDate = LocalDate.now();
		if (currentDate == currentDate.withDayOfMonth(1)) {
			_songValue.clear();
			for (int i = 0; i < _songs.size(); i++) {
				_songValue.put(_songs.get(i), 1);
			}
		}

	}

//	done, gets total value of all songs in map
	/**
	 * gets the total
	 * 
	 * @return
	 */
	public int getTotalSongValue() {
		repOK();
		int total = 0;
		for (int i = 0; i < _songValue.size(); i++) {
			total += _songValue.get(_songs.get(i));
		}
		return total;
	}

//	completed i think
	public void addSong(Song song) {
		if (_songs.contains(song) && ("javaSongs".equals(song.getParent())))
			return;
	
		_songs.add(song);
		_songValue.put(song, 1);

	}

	public void addSong(String str) {
		Song song = new Song(str);
		if (_songs.contains(song) && ("javaSongs".equals(song.getParent())))
			return;

		_songs.add(song);
		_songValue.put(song, 1);
	}

	
	public void removeSong(Song song) {
		if (!_songs.contains(song))
			return;
		File fileSong = new File(song.getName());

		_songs.remove(song);
		_songValue.remove(song);

		if (fileSong.getParent().equals("javaSongs"))
			fileSong.delete();
	}

//	completed
	/**
	 * gets the files within file. if file isn't a directory then returns file. if
	 * file is a directory then it recursively searches for and returns the subfiles
	 * including the given file
	 * 
	 * @param file
	 * @return
	 */
	public static Stream<File> getFiles(File file) {

		if (!file.isDirectory()) {
			return Stream.of(file);

		} else {
			return Stream.concat(Stream.of(file), Arrays.stream(file.listFiles()).flatMap(f -> getFiles(f)));
		}

	}

//	main 
	public static void main(String[] args) {

		String str = "Bruno_Mars_Grenade.mp3";
		Media playSong = new Media(new File(str).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(playSong);
		mediaPlayer.play();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

//	dir "javaSongs" need to have the exact same contents as _songs
	public void repOK() {
		assert _songs.size() == _songValue.size();

		for (int i = 0; i < _songs.size(); i++) {
			assert _songValue.containsKey(_songs.get(i));
		}

	}

}
