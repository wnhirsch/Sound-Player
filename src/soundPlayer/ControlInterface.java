package soundPlayer;

public interface ControlInterface {
	public enum eChangeType{
		PLUS,
		MINUS,
		DEFAULT,
		NONE
	};


	public void addNote(Character theChar);

	public void setVolume(int newVol);
	public int getVolume();

	public void setBpm(int newBPM);
	public int getBpm();

	public void setOctave(int newOctave);
	public int getOctave();

	public void setInstrument(int newInstrument);
	public int getInstrument();

	public void setLastCmd(CommandInterface cmd);
	public CommandInterface getLastCmd();

	public int getDefaultOctave();
	public int getDefaultVolume();

}
