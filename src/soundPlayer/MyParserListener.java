package soundPlayer;

import org.jfugue.parser.ParserListener;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

public class MyParserListener implements ParserListener {

	OuterControl _control;
	
	MyParserListener (OuterControl control){
		_control = control;
	}
	
	@Override
	public void afterParsingFinished() {
		_control.finished();		
	}

	@Override
	public void beforeParsingStarts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBarLineParsed(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChannelPressureParsed(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChordParsed(Chord arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerEventParsed(byte arg0, byte arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFunctionParsed(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInstrumentParsed(byte arg1) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onKeySignatureParsed(byte arg0, byte arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLayerChanged(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLyricParsed(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerParsed(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNoteParsed(Note arg0) {
		int pos = _control.updatePosition();
	}

	@Override
	public void onNotePressed(Note arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNoteReleased(Note arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPitchWheelParsed(byte arg0, byte arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPolyphonicPressureParsed(byte arg0, byte arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSystemExclusiveParsed(byte... arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTempoChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTimeSignatureParsed(byte arg0, byte arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackBeatTimeBookmarkRequested(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackBeatTimeBookmarked(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackBeatTimeRequested(double arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackChanged(byte arg0) {
		// TODO Auto-generated method stub
		
	}

}
