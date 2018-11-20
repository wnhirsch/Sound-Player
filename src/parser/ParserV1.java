package parser;

import java.util.ArrayList;
import java.util.List;

import commands.AddNoteCommand;
import commands.CommandInterface;
import commands.DoNothingCommand;
import commands.RepeatNoteCommand;

public class ParserV1 extends Parser{

	public ParserV1(String str) {
		super(str);
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
		this._actualPosition = idx;
		this._isReady = true;
	}
	
	public boolean isEmpty() {
		return this._isEmpty;
	}
}