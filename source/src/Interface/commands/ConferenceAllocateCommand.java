package Interface.commands;

import Interface.ChairHelperInterface;
import Interface.exceptions.ArgumentOutOfRangeException;
import Interface.exceptions.ConferenceNotFoundException;
import Interface.ui.text.UIUtils;
import domain.Conference;
import domain.Database;

import java.util.Collection;


public class ConferenceAllocateCommand implements ChairHelperInterface {
	
	private Database database;
	private UIUtils uiUtils;
	
	private int selectedConferenceID;
	private Conference selectedConference;
	
	private int selectedNumReviewers;
	
	private Collection<Conference> conferencesList;
	
	public ConferenceAllocateCommand(UIUtils uiUtils, Database database){
		this.database = database;
		this.uiUtils = uiUtils;
	}
	
	private void askConference(){
		this.selectedConferenceID = uiUtils.readInteger("message.conference");
		
		try {
			this.selectedConference = searchConference( selectedConferenceID );
			
			if(this.selectedConference == null) {
				throw new ConferenceNotFoundException(uiUtils.getTextManager().getText("exception.conferenceNotFound"));
			}
		} catch (ConferenceNotFoundException e) {
			System.out.println(e.getMessage());
			askConference();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			askConference();
		}
	}
	
	private void askNumReviewers(){
		try {
			this.selectedNumReviewers = uiUtils.readInteger("message.conference.reviewers");
			
			if(this.selectedNumReviewers < 2 || this.selectedNumReviewers > 5) {
				throw new ArgumentOutOfRangeException(uiUtils.getTextManager().getText("exception.argumentOutOfRange"));
			}
		} catch (ArgumentOutOfRangeException e) {
			System.out.println(e.getMessage());
			askNumReviewers();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			askNumReviewers();
		}
	}
	
	public void execute() {
		this.conferencesList = database.getAllConferences();
		
		askConference();
		askNumReviewers();
		
		selectedConference.allocate(this.selectedNumReviewers);
	}
	
	private Conference searchConference( int conferenceId ) {
		for ( Conference conference : this.conferencesList) {
			if( conference.getId() == conferenceId ) {
				return conference;
			}
		}
		return null;
	}
}