package controls;

import org.jfugue.player.Player;
//import org.jfugue.temporal.TemporalPLP;
import org.staccato.StaccatoParser;

import modifiedJFugueTemporalLib.TemporalPLP;

public class PlayerThread implements Runnable {
	private static final long TEMPORAL_DELAY = 0;
	
	Player player;
	String musicString;
	StaccatoParser parser;
	TemporalPLP temporalListener;
	MyParserListener positionListener;

	public PlayerThread(Player p, OuterControl c) {
		player= p;
		parser = new StaccatoParser();
		temporalListener = new TemporalPLP();
		positionListener = new MyParserListener(c);
		
		parser.addParserListener(temporalListener);
		temporalListener.addParserListener(positionListener);
	}
	
	public void setMusicString(String str) {
		musicString= str;	
		parser.parse(musicString);
		
	}
	@Override
	public void run() {
		player.delayPlay(TEMPORAL_DELAY, musicString);
		temporalListener.parse();
	}
	

}
