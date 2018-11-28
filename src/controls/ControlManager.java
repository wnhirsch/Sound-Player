package controls;

import java.io.IOException;
import java.util.List;

import org.jfugue.parser.ParserException;
import org.jfugue.player.Player;

import commands.CommandInterface;
import parser.Parser;
import parser.ParserV1;
import parser.ParserV2;


public class ControlManager {
	public enum ParserType{
		V1, V2;
	}
	
	Parser _parser;
	ControlerJFugue _playerControl;
	Player _player;
	
	private int _int_currentPosition;
	private int _posIndex;
	private int numNotes;
	private List<CommandInterface> _cmdList;

	public ControlManager(String theString, ParserType type){
		_player = new Player();
		switch(type) {
		case V1:
			_parser = new ParserV1(theString);
			break;
		case V2:
			_parser = new ParserV2(theString);
			break;
		}
		
		_cmdList= _parser.getCommandList();
		List<Integer> notePos = _parser.getNotePositions();
		numNotes = notePos.size();
		
		_playerControl = new ControlerJFugue(this, _cmdList, notePos);
	
	}
	
	public void execute() {
		_playerControl.execute();
	}
	
	public void stop() {
		_playerControl.stop();
	}
	
	public int updatePosition() {
		_int_currentPosition = _playerControl.getCurrentPos(_posIndex);
		
		_posIndex++;

		return _int_currentPosition;
	}

	public int getBpm() {
		return _playerControl.getBpm();
	}

	public int getNumNotes(){
		return numNotes;
	}
	
	public String getMusicString() {
		return _playerControl.getMusicString();
	}

	public void saveMidiFile() throws ParserException {
		try {
			_playerControl.saveMidiFile();
		} catch (IOException | ParserException e) {
						e.printStackTrace();
		}
	}
	
	public void saveMidiFile(String archivePathAndName) {
		try {
			_playerControl.saveMidiFile(archivePathAndName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		_playerControl.pause();
	}
	
	public void resume() {
		_playerControl.resume();
	}
	
	
}
