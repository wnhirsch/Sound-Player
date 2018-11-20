package commands;

import controls.ControlInterface;

public class ChangeOctaveCommand implements CommandInterface {
	private OctaveDef changeOctave;
	private int maxOctave = 10;
	private int newOctave = 0;
	
	public ChangeOctaveCommand(OctaveDef i) {
		changeOctave = i;
	}


	@Override
	public void execute(ControlInterface control){
		//System.out.println(control.getOctave());
		if(changeOctave == OctaveDef.MINUS) {
			newOctave = control.getOctave() - 1;
			if(newOctave < 0)
				newOctave = 0;
			
		}else if(changeOctave == OctaveDef.PLUS){
			newOctave = control.getOctave() + 1;
			
			if(newOctave > maxOctave)
				newOctave = control.getDefaultOctave();
			
		} else if(changeOctave == OctaveDef.SAME){
			newOctave = control.getDefaultOctave();
		}
		control.setOctave(newOctave);
		control.setLastCmd(this);	
	}


}
