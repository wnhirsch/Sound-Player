package controls;

import java.io.IOException;
import java.util.List;

import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

import commands.CommandInterface;
import file.MidiSaver;

public class Control implements ControlInterface{
	public static final int defaultOctave = 5;
	
	private OuterControl _father;
	
	public List<CommandInterface> _commandList;
	
	private int volume;
	private int bpm;
	private int octave;
	private CommandInterface lastCmd;
	private int instrument;
	private int octaveDefault;
	private int volumeDefault;
	private int bpmDefault;

	private String musicString;
	
	private List<CommandInterface> _buffer_input;
	private List<Integer> _notePositions;
			
	private Player _player;
	// Volume, Instrument, Tempo
	private String _str_configString = "X[Volume]=%d I%d T%d ";
	private PlayerThread playerThread;
	private ManagedPlayer managedPlayer;
	
	boolean fetched;

	
	public int getOctave() {
		return octave;
	}
	
	public int getDefaultOctave() {
		return octaveDefault;
	}

	public int getDefaultVolume() {
		return volumeDefault;
	}


	public void setOctave(int octave) {
		this.octave = octave;
		
	}
	
	@Override
	public int getBpm() {
		return bpm;
	}

	@Override
	public void setBpm(int bpm) {
		this.bpm = bpm;
		String cmdString= "T"+ bpm +" ";
		musicString = musicString + cmdString;
	}

	@Override
	public int getDefaultBpm() {
		return bpmDefault;
	}

	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
		String cmdString= "X[Volume]="+volume + " ";
		musicString = musicString + cmdString;
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
	
	public void addNote(Character note){
		String cmdString= 	"I" + Integer.toString(getInstrument()) + " "+
						Character.toString(note)+ 
						Integer.toString(getOctave()) + " ";

		musicString = musicString + cmdString;
	}


	public Control(OuterControl father, List<CommandInterface> buffer, List<Integer> notePos){
		_father = father;
		_player = new Player();
		_buffer_input = buffer;
		_notePositions = notePos;

		bpmDefault = 120;
		bpm = bpmDefault;
		octaveDefault = 5;
		octave = octaveDefault;
		instrument = 0;
		volume = 2500;
		
		fetched = _fetchMusicString();
		
		playerThread= new PlayerThread(_player, _father);
		managedPlayer = _player.getManagedPlayer();
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
	
	public void saveMidiFile() throws IOException {
		MidiSaver.saveMidiFile(this);
	}
	
	public void saveMidiFile(String archivePathAndName) throws IOException {
		MidiSaver.saveMidiFile(this, archivePathAndName);
	}
	
	public void execute() {
		playerThread.setMusicString(musicString);
		Thread thread = new Thread(playerThread);
		thread.start();
	}
	
	public boolean isPlaying() {
		return managedPlayer.isPlaying();
	}
	
	public int getCurrentPos(int index) {
		return _notePositions.get(index);
	}


	

}