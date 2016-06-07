package Interface;

import Interface.ui.text.UIUtils;
import domain.Database;

public class ConferenceAllocateCommand extends AbstractChairHelperCommand {
	
	private Database database;
	private UIUtils uiUtils;

	public ConferenceAllocateCommand(UIUtils uiUtils, Database database){
		this.database = database;
		this.uiUtils = uiUtils;
	}
	
	private void askConference(){
		
	}
	
	private void askNumReviewers(){
		
	}
	
	public void execute(){
		
	}
}
