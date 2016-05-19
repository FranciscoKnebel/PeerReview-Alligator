package domain;

public class Conference {
	private String acronym;
	private List<Paper> papers;
	private List<Researcher> members;
	private boolean isAllocated;

	public void Conference(String acronym, List<Researcher> members, List<Paper> papers, boolean isAllocated) {
	}

	public void allocate(int numReviewers) {
	}

	private List<Paper> generateOrderedPaperList() {
	}

	private List<Researcher> generateOrderedReviewerList() {
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

	public boolean isAllPapersReviewed() {
	}
}
