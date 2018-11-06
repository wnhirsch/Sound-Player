package soundPlayer;

public class ChangeBPMCommand implements CommandInterface {
	private int incBpm;
	public ChangeBPMCommand(int i) {
		incBpm=i;
	}

	@Override
	public void execute(ControlInterface control){
		control.setBpm(control.getBpm()+ this.incBpm);
		control.setLastCmd(this);	
	}


}
