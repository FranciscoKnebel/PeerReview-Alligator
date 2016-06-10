package Interface.commands;

import java.util.ArrayList;
import java.util.Collection;

import Interface.ChairHelperInterface;
import Interface.exceptions.PaperNotFoundException;
import Interface.exceptions.ReviewerNotFoundException;
import Interface.ui.TextManager;
import Interface.ui.text.UIUtils;
import domain.Database;
import domain.Paper;
import domain.Researcher;

public class ReviewPaperCommand implements ChairHelperInterface {

	private Collection<Paper> paperList;
	private Collection<Researcher> researcherList;
	private Database database;
	
	private Paper selectedPaper;
	private Researcher selectedReviewer;
	private int selectedGrade;
	
	private UIUtils uiUtils;
	private TextManager textManager;

	public ReviewPaperCommand(UIUtils uiUtils, Database database) {
		this.database = database;
		this.uiUtils = uiUtils;
		this.textManager = uiUtils.getTextManager();
	}
	
	public void execute() {
		this.paperList = database.getAllPapers();
		this.researcherList = database.getAllResearchers();
		
		showAllocatedPapers();
		askPaper();
		askReviewer();
		askGrade();
		this.selectedPaper.setReviewGrade(this.selectedReviewer, this.selectedGrade);
	}

	private void showAllocatedPapers() {
		Collection<Paper> allocatedPapers = getAllocatedPapers();
		
		for(Paper paper : allocatedPapers)
			System.out.println(paper.getId() + ": " + paper.getTitle());
	}

	private void askPaper() {
		int paperID = uiUtils.readInteger("message.paper");
		this.selectedPaper = null;
		
		try {
			for(Paper paper: paperList) {
				if(paper.getId() == paperID) {
					this.selectedPaper = paper;
					break;
				}			
			}
			
			if(this.selectedPaper == null)
				throw new PaperNotFoundException(textManager.getText("exception.paperNotFound"));
		} catch (PaperNotFoundException e) {
			System.out.println(e.getMessage());
			askPaper();
		}						
	}

	private void askReviewer() {
		int reviewerID = uiUtils.readInteger("message.reviewer");
		this.selectedReviewer = null;
		
		try {
			for(Researcher reviewer : researcherList) {
				if(reviewer.getId() == reviewerID) {
					this.selectedReviewer = reviewer;
					break;
				}				
			}
			
			if(this.selectedReviewer == null)
				throw new ReviewerNotFoundException(textManager.getText("exception.reviewerNotFound"));
		} catch (ReviewerNotFoundException e) {
			System.out.println(e.getMessage());
			askReviewer();
		}
	}
	
	
	private void askGrade() {
		this.selectedGrade = uiUtils.readInteger("message.grade", -3, 3);
	}
	
	private Collection<Paper> getAllocatedPapers() {
		Collection<Paper> allocatedPapers = new ArrayList<Paper>();
		
		for(Paper paper : paperList) {
			if(paper.isAllocated())
				allocatedPapers.add(paper);
		}
		
		return allocatedPapers;
	}
	
}
