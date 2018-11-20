package gui;

import controls.OuterControl;

public class SoundPlayer {

	private OuterControl ctrl;
	//"bb2cddc1baggabb; ;a,a, bb2cddc1baggaba; ;g,g, aabga;;b2c1b ,,ga;;b2c1b ,,agad bb2cddc1baggaba; g,g"

	public void playSong(String strMusic){
		ctrl = new OuterControl(strMusic, OuterControl.ParserType.V1);
		ctrl.saveMidiFile();
		ctrl.execute();
	}

}


