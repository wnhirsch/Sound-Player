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

public class ParserV2 extends Parser{
	protected int _currentInstrument;
	
	@Override
	protected Map<Character, CommandInterface> _getDefaultDictionary(){
		
		Map<Character, CommandInterface> defaultDictionary = new HashMap<Character, CommandInterface>();

		defaultDictionary.put('A', new AddNoteCommand('A'));
		defaultDictionary.put('B', new AddNoteCommand('B'));
		defaultDictionary.put('C', new AddNoteCommand('C'));
		defaultDictionary.put('D', new AddNoteCommand('D'));
		defaultDictionary.put('E', new AddNoteCommand('E'));
		defaultDictionary.put('F', new AddNoteCommand('F'));
		defaultDictionary.put('G', new AddNoteCommand('G'));
		defaultDictionary.put('a', new RepeatNoteCommand());
		defaultDictionary.put('b', new RepeatNoteCommand());
		defaultDictionary.put('c', new RepeatNoteCommand());
		defaultDictionary.put('d', new RepeatNoteCommand());
		defaultDictionary.put('e', new RepeatNoteCommand());
		defaultDictionary.put('f', new RepeatNoteCommand());
		defaultDictionary.put('g', new RepeatNoteCommand());

		defaultDictionary.put('O', new ChangeVolumeCommand(VolumeDef.PERCENT));
		defaultDictionary.put('o', new ChangeVolumeCommand(VolumeDef.PERCENT));
		defaultDictionary.put('I', new ChangeVolumeCommand(VolumeDef.PERCENT));
		defaultDictionary.put('i', new ChangeVolumeCommand(VolumeDef.PERCENT));
		defaultDictionary.put('U', new ChangeVolumeCommand(VolumeDef.PERCENT));
		defaultDictionary.put('u', new ChangeVolumeCommand(VolumeDef.PERCENT));
		defaultDictionary.put(' ', new ChangeVolumeCommand(VolumeDef.PLUS));

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


		defaultDictionary.put('0', new ChangeInstrumentCommand(0)); 
		defaultDictionary.put('2', new ChangeInstrumentCommand(2)); 
		defaultDictionary.put('4', new ChangeInstrumentCommand(4)); 
		defaultDictionary.put('6', new ChangeInstrumentCommand(6)); 
		defaultDictionary.put('8', new ChangeInstrumentCommand(8)); 

		defaultDictionary.put('1', new ChangeInstrumentCommand(1)); 
		defaultDictionary.put('3', new ChangeInstrumentCommand(3)); 
		defaultDictionary.put('5', new ChangeInstrumentCommand(5)); 
		defaultDictionary.put('7', new ChangeInstrumentCommand(7)); 
		defaultDictionary.put('9', new ChangeInstrumentCommand(9)); 
		
		defaultDictionary.put('?', new ChangeInstrumentCommand());
		defaultDictionary.put('.', new ChangeInstrumentCommand());

		defaultDictionary.put('\n', new ChangeInstrumentCommand(ChangeInstrumentCommand.InstrumentType.TUBULAR_BELLS)); 
		defaultDictionary.put('!', new ChangeInstrumentCommand(ChangeInstrumentCommand.InstrumentType.HARPISCHORD)); 

		defaultDictionary.put(';', new ChangeInstrumentCommand(ChangeInstrumentCommand.InstrumentType.PAN_FLUTE)); 
		defaultDictionary.put(',', new ChangeInstrumentCommand(ChangeInstrumentCommand.InstrumentType.CHURCH_ORGAN)); 
		return defaultDictionary;
	}
	
	public ParserV2(String str) {
		super(str);
		this._char_dictionary = new HashMap<Character, CommandInterface>(_getDefaultDictionary());
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
	
	@Override
	protected void _update() {
		int idx = 0;
		for(int i = 0; i < this._int_inputSize; i++) {
			idx = this._actualPosition + i;
			if(idx < this._int_inputSize) {
				Character theChar = this._str_input.charAt(idx);
				CommandInterface tmpCommand = this._char_dictionary.getOrDefault(theChar, new RepeatNoteCommand());
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
		this._actualPosition = idx;
		this._isReady = true;
	}
	
	public boolean isEmpty() {
		return this._isEmpty;
	}
}