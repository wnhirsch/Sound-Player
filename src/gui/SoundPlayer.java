package gui;

import controls.ControlManager;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SoundPlayer {

	private ControlManager ctrl;
	private final Stage stage;

    public SoundPlayer(Stage stage) {
        this.stage = stage;
    }

	public void playSong(String strMusic){
		ctrl = new ControlManager(strMusic, ControlManager.ParserType.V2);
		ctrl.execute();
	}

	public int getPosition(){
		return ctrl.updatePosition();
	}

	public int getNumNotes(){
        return ctrl.getNumNotes();
    }

	public int getBpm(){
        return ctrl.getBpm();
    }

	public String readFile(){
		StringBuilder strMusic = new StringBuilder();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Text File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File textFile = fileChooser.showOpenDialog(stage);

		try{
            FileReader fr = new FileReader(textFile);
            int c = fr.read();
            while(c != -1){
                strMusic.append((char) c);
                c = fr.read();
            }
        }
        catch (IOException e){
            System.out.println("ERROR: " + e);
        }

        System.out.println(strMusic);

		return strMusic.toString();
	}

    public void saveFile(){
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Select a directory");
        File audioDir = dirChooser.showDialog(stage);

        if (audioDir != null) {
            String filename = JOptionPane.showInputDialog("Type the audio filename (.mid):");
            if (filename != null) {
                if (filename.lastIndexOf(".mid") != filename.length() - 4 && filename.lastIndexOf(".MID") != filename.length() - 4) {
                    filename += ".mid";
                }
                System.out.println(audioDir.getPath() + "\\" + filename);
                ctrl.saveMidiFile(audioDir.getPath() + "\\" + filename);
            }
        }
    }

}


