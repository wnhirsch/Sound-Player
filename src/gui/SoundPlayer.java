package gui;

import controls.ControlManager;

public class SoundPlayer {

	private ControlManager ctrl;
	//"bb2cddc1baggabb; ;a,a, bb2cddc1baggaba; ;g,g, aabga;;b2c1b ,,ga;;b2c1b ,,agad bb2cddc1baggaba; g,g"

	public void playSong(String strMusic){
		ctrl = new ControlManager(strMusic, ControlManager.ParserType.V2);
		ctrl.saveMidiFile();
		ctrl.execute();
	}

}


