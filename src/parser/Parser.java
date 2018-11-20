package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commands.AddNoteCommand;
import commands.ChangeBPMCommand;
import commands.ChangeInstrumentCommand;
import commands.ChangeOctaveCommand;
import commands.ChangeVolumeCommand;
import commands.CommandInterface;
import commands.DoNothingCommand;
import commands.OctaveDef;
import commands.RepeatNoteCommand;
import commands.VolumeDef;

public abstract class Parser {
	protected String _str_input;
	protected int _int_inputSize;
	protected Map<Character, CommandInterface> _char_dictionary;

	protected List<CommandInterface> commandList;
	protected List<Integer> notePositions;
	protected boolean _isReady;
	protected boolean _isEmpty;
	
	protected int _actualPosition;
	
	public Parser(String str) {
		_actualPosition = 0;
		this._str_input = str;
		this._int_inputSize = this._str_input.length();
		this._isReady = false;
		this._isEmpty = false;
		this.commandList = new ArrayList<CommandInterface>();
		this.notePositions = new ArrayList<Integer>();
		this._char_dictionary = new HashMap<Character, CommandInterface>(_getDefaultDictionary());
		this._update();
	}
	
	protected Map<Character, CommandInterface> _getDefaultDictionary(){
		
		Map<Character, CommandInterface> defaultDictionary= new HashMap<Character, CommandInterface>();

		defaultDictionary.put('A', new AddNoteCommand('A'));
		defaultDictionary.put('B', new AddNoteCommand('B'));
		defaultDictionary.put('C', new AddNoteCommand('C'));
		defaultDictionary.put('D', new AddNoteCommand('D'));
		defaultDictionary.put('E', new AddNoteCommand('E'));
		defaultDictionary.put('F', new AddNoteCommand('F'));
		defaultDictionary.put('G', new AddNoteCommand('G'));
		defaultDictionary.put(' ', new AddNoteCommand('R'));
		defaultDictionary.put('!', new ChangeVolumeCommand(VolumeDef.PLUS));

		defaultDictionary.put('O', new ChangeVolumeCommand(VolumeDef.MINUS));
		defaultDictionary.put('I', new ChangeVolumeCommand(VolumeDef.MINUS));
		defaultDictionary.put('U', new ChangeVolumeCommand(VolumeDef.MINUS));


		defaultDictionary.put('H', new RepeatNoteCommand());
		defaultDictionary.put('J', new RepeatNoteCommand());
		defaultDictionary.put('K', new RepeatNoteCommand());
		defaultDictionary.put('L', new RepeatNoteCommand());
		defaultDictionary.put('M', new RepeatNoteCommand());
		defaultDictionary.put('N', new RepeatNoteCommand());
		defaultDictionary.put('O', new RepeatNoteCommand());
		defaultDictionary.put('P', new RepeatNoteCommand());
		defaultDictionary.put('Q', new RepeatNoteCommand());
		defaultDictionary.put('R', new RepeatNoteCommand());
		defaultDictionary.put('S', new RepeatNoteCommand());
		defaultDictionary.put('T', new RepeatNoteCommand());
		defaultDictionary.put('V', new RepeatNoteCommand());
		defaultDictionary.put('X', new RepeatNoteCommand());
		defaultDictionary.put('Y', new RepeatNoteCommand());
		defaultDictionary.put('W', new RepeatNoteCommand());
		defaultDictionary.put('Z', new RepeatNoteCommand());


		defaultDictionary.put('0', new ChangeOctaveCommand(OctaveDef.PLUS));
		defaultDictionary.put('2', new ChangeOctaveCommand(OctaveDef.PLUS));
		defaultDictionary.put('4', new ChangeOctaveCommand(OctaveDef.PLUS));
		defaultDictionary.put('6', new ChangeOctaveCommand(OctaveDef.PLUS));
		defaultDictionary.put('8', new ChangeOctaveCommand(OctaveDef.PLUS));

		defaultDictionary.put('1', new ChangeOctaveCommand(OctaveDef.MINUS));
		defaultDictionary.put('3', new ChangeOctaveCommand(OctaveDef.MINUS));
		defaultDictionary.put('5', new ChangeOctaveCommand(OctaveDef.MINUS));
		defaultDictionary.put('7', new ChangeOctaveCommand(OctaveDef.MINUS));
		defaultDictionary.put('9', new ChangeOctaveCommand(OctaveDef.MINUS));

		defaultDictionary.put('?', new ChangeOctaveCommand(OctaveDef.SAME));
		defaultDictionary.put('.', new ChangeOctaveCommand(OctaveDef.SAME));

		defaultDictionary.put('\n', new ChangeInstrumentCommand());

		defaultDictionary.put(';', new ChangeBPMCommand(50));
		defaultDictionary.put(',', new ChangeBPMCommand(-50));
		return defaultDictionary;
	}

	public String getString(int beginIdx, int endIdx) {
		if(beginIdx >= 0)
			if(endIdx <= _int_inputSize) {
				return _str_input.substring(beginIdx, endIdx);
			} else {
				return _str_input.substring(beginIdx);
			}
		else
			return "";
	}
		
	public List<CommandInterface> getBuffer(){
		List<CommandInterface> list = null;
		
		if(!_isEmpty) {
			if(!_isReady) {
				this._update();
			}
			list = new ArrayList<CommandInterface>(this.commandList);
			this.commandList.clear();
			this._isReady = false;
		}

		return list;
	}
	
	public CommandInterface getCommand(char c) {
		return this._char_dictionary.getOrDefault(c, new DoNothingCommand());
	}
	
	public List<CommandInterface> getCommandList(){
		return new ArrayList<CommandInterface>(this.commandList);
	}
	
	public List<Integer> getNotePositions() {
		return new ArrayList<Integer>(this.notePositions);
	}
	
	protected abstract void _update();
	
	public boolean isEmpty() {
		return this._isEmpty;
	}
}