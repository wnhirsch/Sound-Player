package soundPlayer;

public class VisControl {

	String _theString; 
	private int _endString;

	String _tmpString;	
	private int _blockIdx;
	private int _position;
	private int _blockSize;
	private int _blockMax;
	
	VisControl(String theString){
		_theString = theString;
		_position = 0;
		_blockSize = 50; // hardcoded for now
		_blockIdx = 1;
		_endString = theString.length();
		fetchString(0);
	}
	
	public String updateVis(int position) {
		int _blockMin = (_blockIdx-1)*_blockSize/2;
		_blockMax = (_blockIdx)*_blockSize/2;

		if(position > _blockMax) {
			++_blockIdx;
			fetchString(_blockMax);
			if(_blockMax != 0)
				_position = position%_blockMax;
			else
				_position = position;
		} else {
			if(_blockMin != 0)
				_position = position%_blockMin;
			else
				_position = position;
		}
		
		return getString();
	}
	
	private void fetchString(int start) {
		if(_endString < start + _blockSize)
			_tmpString = _theString.substring(start, _theString.length());
		else 
			_tmpString = _theString.substring(start, start + _blockSize);
		_position = 0;
	}
	
	public String getString() {
		String str;
		if(_blockMax + _position < _endString)
			str = _tmpString.substring(0, _position) + "_HERE_" + _tmpString.substring(_position, _tmpString.length());
		else
			str = _tmpString.substring(0, _position) + "_HERE_" + _tmpString.substring(_position, _tmpString.length());
		return str;
	}
	
	public String finish() {
		updateVis(_endString);
		
		return getString();
	}
}
