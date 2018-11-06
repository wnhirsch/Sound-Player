package soundPlayer;

public class ChangeOctaveCommand implements CommandInterface {
	private int changeOctave;
	public ChangeOctaveCommand(int i) {
		changeOctave=i;
	}


	@Override
	public void execute(ControlInterface control){
		if(changeOctave!=0) {
			control.setOctave(control.getOctave()+changeOctave);
		}else {
			control.setOctave(control.getDefaultOctave());
		}
		control.setLastCmd(this);	
	}


}
