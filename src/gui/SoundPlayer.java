package gui;

import controls.ControlManager;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

public class SoundPlayer {

	private ControlManager _ctrl = null;
	private final Stage _stage;

    public SoundPlayer(Stage stage) {
        this._stage = stage;
    }

	public void playSong(String strMusic){
		if(strMusic != "") {
			_ctrl = new ControlManager(strMusic, ControlManager.ParserType.V2);
			_ctrl.execute();
        }
	}

	public int getPosition(){
		return _ctrl.updatePosition();
	}

	public int getNumNotes(){
        return _ctrl.getNumNotes();
    }

	public int getBpm(){
        return _ctrl.getBpm();
    }
	
	public void resumeSong() {
        _ctrl.resume();
	}
	
	public void pauseSong() {
        _ctrl.pause();
	}

    public void stopSong() {
        _ctrl.stop();
    }

	public String readFile(){
		StringBuilder strMusic = new StringBuilder();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Text File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File textFile = fileChooser.showOpenDialog(_stage);

		try{
            FileReader fr = new FileReader(textFile);
            int c = fr.read();
            while(c != -1){
                strMusic.append((char) c);
                c = fr.read();
            }
            fr.close();
        }
        catch (IOException e){
            System.out.println("ERROR: " + e);
        }

		return strMusic.toString();
	}

    public void saveFile(String strMusic){
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Select a directory");
        File audioDir = dirChooser.showDialog(_stage);

        if (audioDir != null) {
            String filename = JOptionPane.showInputDialog("Type the audio or text filename:");
            if (filename != null) {
                int midPos = Math.max(filename.lastIndexOf(".mid"), filename.lastIndexOf(".MID"));
            	
                if(midPos == -1)
            		filename += ".mid";
            	
                if(_ctrl == null) {
                    _ctrl = new ControlManager(strMusic, ControlManager.ParserType.V2);
                    _ctrl.saveMidiFile(audioDir.getPath() + "\\" + filename);
                    _ctrl = null;         
                } else {
                    _ctrl.saveMidiFile(audioDir.getPath() + "\\" + filename);
                }
             }
        }
    }

}


