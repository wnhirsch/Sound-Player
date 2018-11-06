package soundPlayer;

import java.util.List;

import org.jfugue.player.Player;


public class OuterControl {
	
	Parser _parser;
	Control _playerControl;
	Player _player;
	VisControl _visControl;
	
	private int _int_currentPosition;
	private int _posIndex;
	private List<CommandInterface> _cmdList;
	OuterControl(String theString){
		_player = new Player();
		_parser = new Parser(theString);
		
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
		return _playerControl.getMusicString(_cmdList);
	}
}
