package Interface;

import java.util.ArrayList;
import java.util.Collection;

import Interface.ui.text.UIUtils;
import domain.Database;
import domain.Paper;
import domain.Researcher;

public class ReviewPaperCommand extends AbstractChairHelperCommand {

	private Collection<Paper> paperList; // mudança
	private Collection<Researcher> researcherList; //
	private Database database;
	
	private Paper selectedPaper;
	private Researcher selectedReviewer;
	private int selectedGrade;
	
	private UIUtils uiUtils;

	public ReviewPaperCommand(UIUtils uiUtils, Database database) {
		this.database = database;
		this.uiUtils = uiUtils;
	}
	
	public void execute() {
		this.paperList = database.getAllPapers();
		this.researcherList = database.getAllResearchers();

		showAllocatedPapers();
		askPaper();
		askReviewer();
		askGrade();
		setReviewGrade();
	}

	private void setReviewGrade() {
		selectedPaper.addReviewer(selectedReviewer, selectedGrade);
	}

	private void showAllocatedPapers() {
		Collection<Paper> allocatedPapers = getAllocatedPapers();
		
		for(Paper paper : allocatedPapers)
			System.out.println( paper.getId() + ": " + paper.getTitle());
	}

	private void askPaper() {
		int paperID = uiUtils.readInteger("message.paper");
		
		try {
			for(Paper paper: paperList) {
				if(paper.getId() == paperID) {
					this.selectedPaper = paper;
				}			
			}
			
			if(this.selectedPaper == null)
				throw new PaperNotFoundException(uiUtils.getTextManager().getText("exception.paperNotFound"));
		} catch (PaperNotFoundException e) {
			System.out.println(e.getMessage());
			askPaper();
		}						
	}

	private void askReviewer() {
		int reviewerID = uiUtils.readInteger("message.reviewer");
		
		try {
			for(Researcher reviewer : researcherList) {
				if(reviewer.getId() == reviewerID)
					this.selectedReviewer = reviewer;
			}
			
			if(this.selectedReviewer == null)
				throw new ReviewerNotFoundException(uiUtils.getTextManager().getText("exception.reviewerNotFound"));
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
