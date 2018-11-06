package soundPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
	private String _str_input;
	private int _int_inputSize;
	private int _int_actualPosition;
	private Map<Character, CommandInterface> _char_dictionary;

	private List<CommandInterface> commandList;
	private List<Integer> notePositions;
	private boolean _isReady;
	private boolean _isEmpty;
	
	static public Map<Character, CommandInterface> getDefaultDictionary(){
		
		Map<Character, CommandInterface> defaultDictionary= new HashMap<Character, CommandInterface>();

		defaultDictionary.put('A', new AddNoteCommand('A'));
		defaultDictionary.put('B', new AddNoteCommand('B'));
		defaultDictionary.put('C', new AddNoteCommand('C'));
		defaultDictionary.put('D', new AddNoteCommand('D'));
		defaultDictionary.put('E', new AddNoteCommand('E'));
		defaultDictionary.put('F', new AddNoteCommand('F'));
		defaultDictionary.put('G', new AddNoteCommand('G'));
		defaultDictionary.put(' ', new AddNoteCommand('R'));
		defaultDictionary.put('!', new ChangeVolumeCommand(1));

		defaultDictionary.put('O', new ChangeVolumeCommand(-1));
		defaultDictionary.put('I', new ChangeVolumeCommand(-1));
		defaultDictionary.put('U', new ChangeVolumeCommand(-1));


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
		defaultDictionary.put('z', new RepeatNoteCommand());


		defaultDictionary.put('0', new ChangeOctaveCommand(1));
		defaultDictionary.put('2', new ChangeOctaveCommand(1));
		defaultDictionary.put('4', new ChangeOctaveCommand(1));
		defaultDictionary.put('6', new ChangeOctaveCommand(1));
		defaultDictionary.put('8', new ChangeOctaveCommand(1));

		defaultDictionary.put('1', new ChangeOctaveCommand(-1));
		defaultDictionary.put('3', new ChangeOctaveCommand(-1));
		defaultDictionary.put('5', new ChangeOctaveCommand(-1));
		defaultDictionary.put('7', new ChangeOctaveCommand(-1));
		defaultDictionary.put('9', new ChangeOctaveCommand(-1));

		defaultDictionary.put('?', new ChangeOctaveCommand(0));
		defaultDictionary.put('.', new ChangeOctaveCommand(0));

		defaultDictionary.put('\n', new ChangeInstrumentCommand());

		defaultDictionary.put(';', new ChangeBPMCommand(50));
		defaultDictionary.put(',', new ChangeBPMCommand(-50));
		return defaultDictionary;
	}
	
	public Parser(String str, Map<Character, CommandInterface> char_dictionary) {
		this._str_input = str;
		this._int_inputSize = this._str_input.length();
		this._int_actualPosition = 0;
		this._isReady = false;
		this._isEmpty = false;
		this.commandList = new ArrayList<CommandInterface>();
		this.notePositions = new ArrayList<Integer>();
		this._char_dictionary = new HashMap<Character, CommandInterface>(char_dictionary);
		this._update();
	}
	
	public Parser(String str){
		this(str, getDefaultDictionary());


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
	
	private void _update() {
		int idx = 0;
		for(int i = 0; i < this._int_inputSize; i++) {
			idx = this._int_actualPosition + i;
			if(idx < this._int_inputSize) {
				Character theChar = this._str_input.charAt(idx);
				theChar = Character.toUpperCase(theChar);
				CommandInterface tmpCommand = this._char_dictionary.getOrDefault(theChar, new DoNothingCommand());
				this.commandList.add(tmpCommand);
				if(tmpCommand instanceof AddNoteCommand)
					notePositions.add(idx);
				else if(tmpCommand instanceof RepeatNoteCommand)
					notePositions.add(idx);
			} else {
				this._isEmpty = true;
				break;
			}
		}
		this._int_actualPosition = idx;
		this._isReady = true;
	}
	
	public boolean isEmpty() {
		return this._isEmpty;
	}
}