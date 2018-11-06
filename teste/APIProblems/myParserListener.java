package APIProblems;

import org.jfugue.parser.ParserListener;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;


public class myParserListener implements ParserListener {

	@Override
	public void afterParsingFinished() {
		// TODO Auto-generated method stub
		
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
	public void onInstrumentParsed(byte arg0) {
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
		System.out.println(new String("Note: " + arg0));
		
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
		System.out.println(new String("Tempo: " + arg0));
		
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
