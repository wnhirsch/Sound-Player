package main;

import java.io.IOException;

import controls.OuterControl;
import file.MidiSaver;

public class SoundPlayer {

	public static void main(String[] argv) {
		
		String theString = "a0 6a5463547 02df86357 v2 3sadsdf4cvs2gsda152f482 445r284edg32sd5f432sd857f4sd325t7445wearusdifkgakdcjrhlfylnudcg ilukhsdafçfidoçwij dçoILKQJÇ \\,.X/.\\ZX.,ÇX, |ZX, XÇ";
		OuterControl ctrl = new OuterControl(theString, OuterControl.ParserType.V1);
		
		ctrl.saveMidiFile();

		
		ctrl.execute();

		//System.out.println(ctrl.getMusicString());
		
		return;
		
	}

}
