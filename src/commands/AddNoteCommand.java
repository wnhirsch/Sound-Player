package commands;

import controls.ControlInterface;

public class AddNoteCommand implements CommandInterface {
	private char note;
	public AddNoteCommand(char c) {
		note= c;
	}

	public char getNote() {
		return note;
	}

	@Override
	public void execute(ControlInterface control){
		control.addNote(this.note);
		control.setLastCmd(this);	
	}	

}
