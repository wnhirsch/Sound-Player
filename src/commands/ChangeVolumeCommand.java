package commands;

import controls.ControlInterface;

public class ChangeVolumeCommand implements CommandInterface {
	private int maxVolume = 16380;
	private VolumeDef incVolume;

	public ChangeVolumeCommand(VolumeDef inc){
		incVolume = inc;
	}

	@Override
	public void execute(ControlInterface control){
		int newVolume = control.getVolume();
		if (incVolume == VolumeDef.MINUS) {
			newVolume= control.getVolume()/2;
		} else if (incVolume == VolumeDef.PLUS){
			if(control.getVolume() > 0)
				newVolume= control.getVolume()*2;
			else
				newVolume = control.getDefaultVolume();
		} else if (incVolume == VolumeDef.PERCENT) {
			newVolume = (int) (control.getVolume() + newVolume * 0.1);
		}
		if(newVolume > maxVolume)
			newVolume = maxVolume;
		control.setVolume(newVolume);
		control.setLastCmd(this);	
	}


}
