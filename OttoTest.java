package adv.java.rio.Otto;

//import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class OttoTest {
	
	
	
	
	
	
	
//	use java from oracle or open jdk
	
	
	

	@Test
	void test() throws IOException, InterruptedException {

		Otto otto = new Otto();

//		gets the files within javaSongs
		File file = new File("javaSongs");
		getFiles(file).forEach(x -> {
			Song song = new Song(x.getName());
			if (!song.getName().equals("javaSongs"))
				otto.addSong(song);
		});

		while (true) {
			otto.playRandomSong();
		}

	}

	public static Stream<File> getFiles(File file) {

		if (!file.isDirectory()) {
			return Stream.of(file);

		} else {
			return Stream.concat(Stream.of(file), Arrays.stream(file.listFiles()).flatMap(f -> getFiles(f)));
		}

	}

}
