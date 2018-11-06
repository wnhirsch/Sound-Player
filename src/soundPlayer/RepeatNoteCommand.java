package soundPlayer;

public class RepeatNoteCommand implements CommandInterface {


	@Override
	public void execute(ControlInterface control){
		CommandInterface lastCmd= control.getLastCmd();
		if(lastCmd!=null && lastCmd instanceof AddNoteCommand){
			lastCmd.execute(control);
		}else{
			CommandInterface addNoteCmd= new AddNoteCommand('R');
			addNoteCmd.execute(control); 
		}
	}
}
