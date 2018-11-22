package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.parser.ParserException;
import org.jfugue.pattern.Pattern;

import controls.ControlInterface;

public class MidiSaverJFugue {
	public static void saveMidiFile(ControlInterface control, String archivePathAndName) throws IOException {
		File file = new File(archivePathAndName);
		Pattern pattern = new Pattern(control.getMusicString());
		
		try {
			MidiFileManager.savePatternToMidi(pattern, file);
		} catch(ParserException e) {
			System.out.println("Exception captured by this almost awesome team");
			System.out.println("Presenting the music string:");
			System.out.println(control.getMusicString());
			e.printStackTrace();
		}
	}
	
	public static void saveMidiFile(ControlInterface control) throws IOException {
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString() + "/defaultName.mid";
		saveMidiFile(control, path);
	}
	
}
