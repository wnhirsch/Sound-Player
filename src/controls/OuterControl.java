package controls;

import java.io.IOException;
import java.util.List;

import org.jfugue.parser.ParserException;
import org.jfugue.player.Player;

import commands.CommandInterface;
import parser.Parser;
import parser.ParserV1;
import parser.ParserV2;


public class OuterControl {
	public enum ParserType{
		V1, V2;
	}
	
	Parser _parser;
	Control _playerControl;
	Player _player;
	VisControl _visControl;
	
	private int _int_currentPosition;
	private int _posIndex;
	private List<CommandInterface> _cmdList;
	public OuterControl(String theString, ParserType type){
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
		
		_playerControl = new Control(this, _cmdList, notePos);
		_visControl = new VisControl(theString);
		
	}
	
	public void execute() {
		_playerControl.execute();
	}
	
	public int updatePosition() {
		_int_currentPosition = _playerControl.getCurrentPos(_posIndex);
	
		
		String str = _visControl.updateVis(_int_currentPosition);
		System.out.println(str);
		
		_posIndex++;
		
		return _int_currentPosition;
	}
	
	public void finished() {
		String str = _visControl.finish();
		System.out.println(str);
		
	}


	
	public String getMusicString() {
		return _playerControl.getMusicString();
	}

	public void saveMidiFile() throws ParserException {
		try {
			_playerControl.saveMidiFile();
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveMidiFile(String archivePathAndName) {
		try {
			_playerControl.saveMidiFile(archivePathAndName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
