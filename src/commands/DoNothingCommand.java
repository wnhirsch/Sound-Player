package commands;

import controls.ControlInterface;

public class DoNothingCommand implements CommandInterface {

	@Override
	public void execute(ControlInterface control){
		control.setLastCmd(this);
	}
}
