package commands;

import controls.ControlInterface;

public class ChangeOctaveCommand implements CommandInterface {
	private OctaveDef changeOctave;
	private int maxOctave = 10;
	private int newOctave = 1;
	
	public ChangeOctaveCommand(OctaveDef i) {
		changeOctave = i;
	}


	@Override
	public void execute(ControlInterface control){
		if(changeOctave == OctaveDef.MINUS) {
			newOctave = control.getOctave() - 1;
			
			if(newOctave < 0)
				newOctave = 0;
			
		}else if(changeOctave == OctaveDef.PLUS){
			newOctave = control.getOctave() + 1;
			
			if(newOctave > maxOctave)
				newOctave = control.getDefaultOctave();
			
		} else {
			control.setOctave(control.getDefaultOctave());
		}
		control.setOctave(newOctave);
		control.setLastCmd(this);	
	}


}
