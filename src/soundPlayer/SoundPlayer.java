package soundPlayer;

public class SoundPlayer {

	public static void main(String[] argv) {
		
		String theString = "a b c ,    ; a b c";
		OuterControl ctrl = new OuterControl(theString);
		
		ctrl.execute();

		//System.out.println(ctrl.getMusicString());
		
		return;
		
	}

}
