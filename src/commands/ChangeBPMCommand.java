package commands;

import controls.ControlInterface;

public class ChangeBPMCommand implements CommandInterface {
	private int incBpm;
	private int maxBpm = 300;
	public ChangeBPMCommand(int i) {
		incBpm=i;
	}

	@Override
	public void execute(ControlInterface control){
		int newBpm = control.getBpm() + this.incBpm;
		
		if(newBpm > maxBpm)
			newBpm = control.getDefaultBpm();
		else if (newBpm < 1)
			newBpm = control.getDefaultBpm();
			
		control.setBpm(newBpm);
		control.setLastCmd(this);	
	}


}
