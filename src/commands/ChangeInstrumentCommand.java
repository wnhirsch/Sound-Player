package commands;

import controls.ControlInterface;
import controls.ControlInterface.InstrumentType;

public class ChangeInstrumentCommand implements CommandInterface {
	
	private boolean _isSet = false;
	private int _instrument = 1;
	private InstrumentType _typedInstrument;
	
	@Override
	public void execute(ControlInterface control){
		if(_isSet) {
			control.setInstrument(_typedInstrument);
		} else {
			control.setInstrument(control.getInstrument() + _instrument);
		}
		control.setLastCmd(this);	
	}

	public ChangeInstrumentCommand() {}
	
	public ChangeInstrumentCommand(InstrumentType instrument) {
		_typedInstrument = instrument;
		_isSet = true;
	}
	
	public ChangeInstrumentCommand(int toBeSummed) {
		_instrument = toBeSummed;
	}
}
