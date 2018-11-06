package soundPlayer;

public class DoNothingCommand implements CommandInterface {

	@Override
	public void execute(ControlInterface control){
		control.setLastCmd(this);	
	}
}
