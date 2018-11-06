package soundPlayer;

public class ChangeVolumeCommand implements CommandInterface {
	private int incVolume;

	public ChangeVolumeCommand(int inc){
		incVolume= inc;
	}

	@Override
	public void execute(ControlInterface control){
		int newVolume;
		if (incVolume<0) {
			newVolume= control.getVolume()/2;
		}else {
			if(control.getVolume() > 0)
				newVolume= control.getVolume()*2;
			else
				newVolume = control.getDefaultVolume();
		}
		control.setVolume(newVolume);
		control.setLastCmd(this);	
	}


}
