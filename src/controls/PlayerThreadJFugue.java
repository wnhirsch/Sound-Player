package controls;

import org.jfugue.player.Player;

public class PlayerThreadJFugue implements Runnable {
	private static final long TEMPORAL_DELAY = 0;
	
	Player player;
	String musicString;

	public PlayerThreadJFugue(Player p, ControlManager c) {
		player= p;
	}
	
	public void setMusicString(String str) {
		musicString= str;	
	}

	@Override
	public void run() {
		player.delayPlay(TEMPORAL_DELAY, musicString);
	}
	

}
