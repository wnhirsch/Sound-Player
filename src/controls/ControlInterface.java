package controls;

import java.io.IOException;

import commands.CommandInterface;

public interface ControlInterface {
	
	public enum InstrumentType{
		PAN_FLUTE, CHURCH_ORGAN, HARPISCHORD, TUBULAR_BELLS;
	}
	
	public void addNote(Character theChar);

	public void setVolume(int newVol);
	public int getVolume();

	public void setBpm(int newBPM);
	public int getBpm();
	public int getDefaultBpm();

	public void setOctave(int newOctave);
	public int getOctave();

	public void setInstrument(int newInstrument);
	public void setInstrument(InstrumentType instrument);
	public int getInstrument();

	public void setLastCmd(CommandInterface cmd);
	public CommandInterface getLastCmd();

	public int getDefaultOctave();
	public int getDefaultVolume();
	
	public String getMusicString();
	
	public void saveMidiFile() throws IOException;
	public void saveMidiFile(String archivePathAndName) throws IOException;

}
