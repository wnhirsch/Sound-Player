package commands;

import controls.ControlInterface;

public class ChangeInstrumentCommand implements CommandInterface {
	
	private boolean _isSet = false;
	private int _instrument = 1;
	
	public enum InstrumentType{
		PAN_FLUTE, CHURCH_ORGAN, HARPISCHORD, TUBULAR_BELLS;
	}
	
	@Override
	public void execute(ControlInterface control){
		if(_isSet) {
			control.setInstrument(_instrument);
		} else {
			control.setInstrument(control.getInstrument() + _instrument);
		}
		control.setLastCmd(this);	
	}

	public ChangeInstrumentCommand() {}
	
	public ChangeInstrumentCommand(InstrumentType instrument) {
		switch(instrument) {
		case PAN_FLUTE:
			_instrument = 76;
			_isSet = true;
			break;
		case CHURCH_ORGAN:
			_instrument = 20;
			_isSet = true;
			break;
		case HARPISCHORD:
			_instrument = 7;
			_isSet = true;
			break;
		case TUBULAR_BELLS:
			_instrument = 15;
			_isSet = true;
			break;
		}
	}
	
	public ChangeInstrumentCommand(int toBeSummed) {
		_instrument = toBeSummed;
	}
}
