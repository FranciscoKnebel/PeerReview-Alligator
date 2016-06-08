package Interface;

import Interface.ui.text.UIUtils;
import domain.Conference;
import domain.Database;
import domain.Review;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;


public class ConferenceAllocateCommand implements ChairHelperInterface {
	
	private Database database;
	private UIUtils uiUtils;
	private Scanner scanner;
	
	private int selectedConferenceId;
	private Conference selectedConference;
	
	private int numReviewers;
	
	private Collection<Conference> conferencesList;
	
	public ConferenceAllocateCommand(UIUtils uiUtils, Database database){
		this.database = database;
		this.uiUtils = uiUtils;
	}
	
	private void askConference(){
		System.out.println(": ");
		scanner = new Scanner(System.in);
		selectedConferenceId = scanner.nextInt(); // ask reviewer
		scanner.close();
	}
	
	private void askNumReviewers(){
		System.out.println(": ");
		scanner = new Scanner(System.in);
		numReviewers = scanner.nextInt(); // ask reviewer
		scanner.close();
	}
	
	public void execute() {
		this.conferencesList = database.getAllConferences();
		askConference();
		selectedConference = searchConference( selectedConferenceId );
		askNumReviewers();
		selectedConference.allocate(numReviewers);
		
		
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