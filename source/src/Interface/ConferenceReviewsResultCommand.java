package Interface;

import Interface.ui.text.UIUtils;
import domain.Database;

public class ConferenceReviewsResultCommand extends AbstractChairHelperCommand {

	private UIUtils uiUtils;
	private Database database;
	
	public ConferenceReviewsResultCommand(UIUtils uiUtils, Database database) {
		this.database = database;
		this.uiUtils = uiUtils;
	}
	
	private void showList(){
		
	}
	
	private void askConference(){
		
	}
	
	private void pendingReviewsAlert(){
		
	}
}
