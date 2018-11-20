package main;

import controls.OuterControl;

public class SoundPlayer {

	public static void main(String[] argv) {
		
		String theString = ".;;CDEF FF CDCD DD EGFE EE CDEF FF  ; 2F CCDC  EF 1F";
		OuterControl ctrl = new OuterControl(theString, OuterControl.ParserType.V1);
		
		ctrl.saveMidiFile();

		
		ctrl.execute();

		System.out.println(ctrl.getMusicString());
		
		return;
		
	}

}
