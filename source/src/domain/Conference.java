package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Conference {
	private String acronym;
	private List<Paper> papersList;
	private List<Researcher> membersList;
	private boolean areMembersAllocated;

	public Conference(String acronym, List<Researcher> members, List<Paper> papers) {
		this.acronym = acronym;
		this.papersList = papers;
		this.membersList = members;
		this.areMembersAllocated = areMembersAllocated();
	}

	public String getAcronym() {
		return this.acronym;
	}

	private boolean areMembersAllocated() {
		for (Paper paper : papersList) {
			for (Researcher researcher : membersList) {

			}
		}
		return true;
	}

	public void allocate(int numReviewers) {

	}

	private void orderPapers() { //mudou de genareteOrderedePapersList
		Collections.sort(papersList, new Comparator<Paper>() {
	        @Override
	        public int compare(Paper p1, Paper p2) {
	            return p1.getId() - p2.getId();
	        }
	    });
	}

	private void orderMembers() {
		Collections.sort(membersList, new Comparator<Researcher>() {
	        @Override
	        public int compare(Researcher p1, Researcher p2) {
	            return p1.getId() - p2.getId();
	        }
	    });
	}

	private Paper getFirstPaper(List<Paper> papers) {

	}

	private Researcher getFirstCandidate(List<Researcher> candidates) {

	}

	private List<Researcher> reorderReviewerList(List<Researcher> reviewers) {

	}

	private void removeTopPaper() {

	}

	private void addReviewerToTempList(Researcher researcher) {

	}

	private void saveReviewers(List<Researcher> tempList) {

	}

	private void showAllocationLog() {

	}

	public List<Paper> getAcceptedPapers() {

	}

	public List<Paper> getRejectedPapers() {

	}

	public boolean areAllPapersReviewed() {
		for (Paper paper : papersList) {
			if (paper.getNotConcludedReviewsList() != null) {
				return false;
			}
		}
		return true;
	}

}
