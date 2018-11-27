package controls;

import java.io.IOException;
import java.util.List;

import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

import commands.CommandInterface;
import controls.ControlInterface.InstrumentType;
import file.MidiSaverJFugue;

public abstract class Controler implements ControlInterface {
public static final int defaultOctave = 5;
	
	protected ControlManager _father;
	
	public List<CommandInterface> _commandList;
	
	protected int volume;
	protected int bpm;
	protected int octave;
	protected CommandInterface lastCmd;
	protected int instrument;
	protected int octaveDefault;
	protected int volumeDefault;
	protected int bpmDefault;

	protected String musicString;
	
	protected List<CommandInterface> _buffer_input;
	protected List<Integer> _notePositions;
			
	// Volume, Instrument, Tempo
	protected String _str_configString;;
	
	protected boolean fetched;

	
	public int getOctave() {
		return octave;
	}
	
	public int getDefaultOctave() {
		return octaveDefault;
	}

	public int getDefaultVolume() {
		return volumeDefault;
	}

	@Override
	public int getBpm() {
		return bpm;
	}

	@Override
	public int getDefaultBpm() {
		return bpmDefault;
	}

	
	public int getVolume() {
		return volume;
	}

	public void addCommand(CommandInterface c) {
		_commandList.add(c);
	}

	public CommandInterface getLastCmd() {
		return lastCmd;
	}

	public void setLastCmd(CommandInterface cmd) {
		lastCmd= cmd;
	}
	
	
	public int getInstrument() {
		return instrument;
	}

	@Override
	public void setInstrument(int instrument) {
		this.instrument = instrument;
	}
	
	private boolean _fetchMusicString() {
		musicString= String.format(_str_configString, volume, instrument, bpm);
		for(int i=0; i< _buffer_input.size(); i++) {
			_buffer_input.get(i).execute(this);
		}
		
		return true;
	}
	
	public String getMusicString() {
		return musicString;
	}
	
	public abstract void saveMidiFile() throws IOException;
	
	public abstract void saveMidiFile(String archivePathAndName) throws IOException;
	
	public abstract void execute();
	public abstract void stop();
	
	public abstract boolean isPlaying();
	
	public int getCurrentPos(int index) {
		return _notePositions.get(index);
	}
	
}
