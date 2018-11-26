package controls;

import org.jfugue.parser.ParserException;
import org.jfugue.player.Player;
//import org.jfugue.temporal.TemporalPLP;
import org.staccato.StaccatoParser;

import modifiedJFugueTemporalLib.TemporalPLP;

public class PlayerThreadJFugue implements Runnable {
	private static final long TEMPORAL_DELAY = 0;
	
	Player player;
	String musicString;
	StaccatoParser parser;
	TemporalPLP temporalListener;
	MyParserListenerJFugue positionListener;

	public PlayerThreadJFugue(Player p, ControlManager c) {
		player= p;
		parser = new StaccatoParser();
		temporalListener = new TemporalPLP();
		positionListener = new MyParserListenerJFugue(c);
		
		parser.addParserListener(temporalListener);
		temporalListener.addParserListener(positionListener);
	}
	
	public void setMusicString(String str) {
		musicString= str;	
		try {
			parser.parse(musicString);
		} catch (ParserException e) {
			System.out.println("Exception captured by this almost awesome team");
			System.out.println("Presenting the music string: ");
			System.out.println(musicString);
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		player.delayPlay(TEMPORAL_DELAY, musicString);
		temporalListener.parse();
	}
	

}