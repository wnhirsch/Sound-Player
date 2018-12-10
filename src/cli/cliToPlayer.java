package cli;
import java.io.File;

import javax.swing.JOptionPane;

import controls.ControlManager;
//import gui.DirectoryChooser;

public class cliToPlayer {

	private ControlManager _ctrl = null;

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
		return strMusic.toString();
	}
	
    public void saveFile(String strMusic){

        File audioDir = new File("/home/ricardo/");

        if (audioDir != null) {
            String filename = JOptionPane.showInputDialog("Type the audio or text filename:");
            if (filename != null) {
                int midPos = Math.max(filename.lastIndexOf(".mid"), filename.lastIndexOf(".MID"));
            	
                if(midPos == -1)
            		filename += ".mid";
            	
                if(_ctrl == null) {
                    _ctrl = new ControlManager(strMusic, ControlManager.ParserType.V2);
                    _ctrl.saveMidiFile(audioDir.getPath() + File.separatorChar + filename);
                    _ctrl = null;         
                } else {
                    _ctrl.saveMidiFile(audioDir.getPath() + File.separatorChar + filename);
                }
             }
        }
    }
	
	
}
