package controls;

//import org.jfugue.player.Player;
import org.jfugue.*;

public class PlayerThreadJFugue4 implements Runnable {
	//private static final long TEMPORAL_DELAY = 0;
	
	Player player;
	String musicString;

	public PlayerThreadJFugue4(Player p, ControlManager c) {
		player= p;
	}
	
	public void setMusicString(String str) {
		musicString= str;	
	}

	@Override
	public void run() {
		player.play(musicString);
	}
	

}
