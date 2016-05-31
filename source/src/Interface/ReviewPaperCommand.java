package Interface;

import domain.Collection;
import domain.Paper;

public class ReviewPaperCommand extends AbstractChairHelperCommand {

	private Collection<Paper> paperList; // mudança
	private Database database;
	private String selectedPaperName; // mudança
	private Paper selectedPaper;

	private String selectedReviewerName; // mudança
	private Reviewer selectedReviewer;

	private Float selectedGrade;

	public void execute() {
		paperList = database.getAllPapers();

		searchAllocatedPapers();
		askPaper();

		selectedPaper = searchPaper();
		selectedPaper.showReviewers();

		askReviewer();
		selectedReviewer = searchReviewer();
		askGrade();
		setReviewGrade(selectedReviewer, selectedGrade);
	}

	void setReviewGrade(Researcher reviewer, Float grade) {
		for (i = 0; i < selectedPaper.reviews.size(); i++) { // mudança
			if (selectedPaper.reviews[i].researcher.equals(reviewer)) {
				selectedPaper.reviews[i].setGrade(selectedGrade);
				selectedPaper.reviews[i].setReviewStatus(true);
			}
		}
	}

	public ReviewPaperCommand(Database database) {

		this.database = database;
		execute();

	}

	private void searchAllocatedPapers() { // mudança de nome do método // // showAllocatedPapers
		int i = 0;									
		for (Paper paper : paperList) { // mudança
			if(paper.isAllocated()) {
				System.out.println( i ); // falar com INGRID TUPAC
				System.out.println(paper.title);
				i++;
			}
		}
	}

	private void showReviewers() {
		for (i = 0; i < selectedPaper.reviews.size(); i++) { // mudança
			System.out.println(selectedPaper.reviews[i].researcher.name + "\n");
		}
	}

	private Paper searchPaper() {
		for (Paper paper : paperList) { // mudança
			if (paper.title == selectedPaper) {
				return paper;
			}
		}
	}

	private Researcher searchReviewer() {
		for (i = 0; i < selectedPaper.reviews.size(); i++) { // mudança
			if (selectedPaper.reviews[i].researcher.name == selectedResearcherName) {
				return selectedPaper.reviews[i].researcher;
			}
		}
	}

	private void askPaper() {
		System.out.println("paper:");
		Scanner scanner = new Scanner(System.in);
		selectedPaperName = scanner.nextString(); // ask paper
	}

	private void askGrade() {
		selectedGrade = -4; //magic number
		Scanner scanner = new Scanner(System.in);
		while (selectedGrade < -3 || selectedGrade > 3) {
			System.out.println("grade (-3 to 3):"); // implementar teste
			selectedGrade = scanner.nextFloat();
			if (selectedGrade < -3 || selectedGrade > 3) {
				System.out.println("please, insert a valid grade\n"); 
			}
		}
	}

	private void askReviewer() {
		System.out.println("reviewer:");
		Scanner scanner = new Scanner(System.in);
		selectedReviewerName = scanner.nextString(); // ask reviewer
	}
}
