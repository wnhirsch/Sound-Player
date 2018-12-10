package controls;

import org.jfugue.player.Player;

public class PlayerThreadJFugue5 implements Runnable {
	private static final long TEMPORAL_DELAY = 0;
	
	Player player;
	String musicString;

	public PlayerThreadJFugue5(Player p, ControlManager c) {
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
