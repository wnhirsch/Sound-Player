package main;

import java.io.IOException;

import controls.OuterControl;
import file.MidiSaver;

public class SoundPlayer {

	public static void main(String[] argv) {
		
		String theString = "0 65463547 0286357 41564fdd12sd v2 3sad4cvs2da152f482 445r284e32sd5f432sd857f4sd325t7445wearusdifkgakdcjrhlfylnudcg ilukhsdafçfidoçwij dçoILKQJÇ \\,.X/.\\ZX.,ÇX, |ZX, XÇ";
		OuterControl ctrl = new OuterControl(theString, OuterControl.ParserType.V1);
		
		ctrl.saveMidiFile();

		
		ctrl.execute();

		//System.out.println(ctrl.getMusicString());
		
		return;
		
	}

}
