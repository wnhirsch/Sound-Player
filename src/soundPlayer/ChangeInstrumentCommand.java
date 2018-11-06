package soundPlayer;

public class ChangeInstrumentCommand implements CommandInterface {

	@Override
	public void execute(ControlInterface control){
		control.setInstrument(control.getInstrument()+ 1);
		control.setLastCmd(this);	
	}

}
