package Interface;

import java.util.Collection;
import java.util.List;

import Interface.ui.TextManager;
import Interface.ui.text.UIUtils;
import domain.Conference;
import domain.Database;
import domain.Paper;

public class ConferenceReviewsResultCommand implements ChairHelperInterface {

	private final UIUtils uiUtils;
	private final Database database;
	private TextManager textManager;
	private Collection<Conference> conferenceList;
	private Conference selectedConference;

	public ConferenceReviewsResultCommand(UIUtils uiUtils, Database database) {
		this.database = database;
		this.uiUtils = uiUtils;
		this.textManager = uiUtils.getTextManager();
	}

	@Override
	public void execute() {
		this.conferenceList = database.getAllConferences();
		
		askConference();
		if(!selectedConference.areAllPapersReviewed())
			pendingReviewsAlert();
		else {
			showList(selectedConference.getAcceptedPapers(), true);
			showList(selectedConference.getRejectedPapers(), false);
		}
		
	}

	private void askConference() {
		int conferenceID = uiUtils.readInteger("message.conference");
		this.selectedConference = null;
		
		try {
			for(Conference conference: this.conferenceList) {
				if(conference.getId() == conferenceID) {
					this.selectedConference = conference;
					break;
				}
			}
			
			if(this.selectedConference == null)
				throw new ConferenceNotFoundException(textManager.getText("exception.conferenceNotFound"));
		}
		catch(ConferenceNotFoundException e){
			System.out.println(e.getMessage());
			askConference();
		}
	}

	private void showList(List<Paper> Papers, boolean acceptedPapers) { //
		System.out.println();
		if(acceptedPapers) {
			System.out.println(textManager.getText("message.conference.acceptedPapers"));
		} else {
			System.out.println(textManager.getText("message.conference.rejectedPapers"));
		}
		printPapers(Papers);
	}
	
	private void pendingReviewsAlert() {
		System.out.println();
		System.out.println(textManager.getText("message.pendingReview", Integer.toString(selectedConference.getId())));
	}
	
	private void printPapers(List<Paper> Papers) { //
		for(Paper paper: Papers) {
			System.out.println(paper.getId() + "| " + paper.getTitle() + ": " + paper.getMeanGrade());
		}
	}

}
