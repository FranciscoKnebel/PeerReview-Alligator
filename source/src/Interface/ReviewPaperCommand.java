package Interface;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import domain.Database;
import domain.Paper;
import domain.Researcher;
import domain.Review;

public class ReviewPaperCommand extends AbstractChairHelperCommand {

	private Collection<Paper> paperList; // mudança
	private Database database;
	private String selectedPaperName; // mudança
	
	private String selectedPaperTitle;
	private Paper selectedPaper;

	private String selectedReviewerName; // mudança
	private Researcher selectedReviewer;

	private Float selectedGrade;
	
	private Scanner scanner;

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
		for (int i = 0; i < selectedPaper.getReviewsList().size(); i++) { // mudança
			if (selectedPaper.getReviewsList().get(i).getReviewer().getName().equals(reviewer)) {
				selectedPaper.getReviewsList().get(i).setGrade(selectedGrade);
				selectedPaper.getReviewsList().get(i).setReviewStatus(true);
			}
		}
	}

	public ReviewPaperCommand(Database database) {
		this.database = database;
	}

	private void searchAllocatedPapers() { // mudança de nome do método // // showAllocatedPapers
		int i = 0;									
		for (Paper paper : paperList) { // mudança
			if(paper.isAllocated()) {
				System.out.println( i ); // falar com INGRID TUPAC
				System.out.println(paper.getTitle());
				i++;
			}
		}
	}
	
	private Paper searchPaper() {
		for (Paper paper : paperList) { // mudança
			if (paper.getTitle() == selectedPaperName) {
				return paper;
			}
		}
		return null;
	}

	private Researcher searchReviewer() {
		for (int i = 0; i < selectedPaper.getReviewsList().size(); i++) { // mudança
			if (selectedPaper.getReviewsList().get(i).getReviewer().getName() == selectedReviewerName) {
				return selectedPaper.getReviewsList().get(i).getReviewer();
			}
		}
		return null;
	}

	private void askPaper() {
		System.out.println("paper:");
		scanner = new Scanner(System.in);
		selectedPaperName = scanner.next(); // ask paper
		scanner.close();
	}

	private void askGrade() {
		selectedGrade = (float) -4; //magic number
		scanner = new Scanner(System.in);
		while (selectedGrade < -3 || selectedGrade > 3) {
			System.out.println("grade (-3 to 3):"); // implementar teste
			selectedGrade = scanner.nextFloat();
			if (selectedGrade < -3 || selectedGrade > 3) {
				System.out.println("please, insert a valid grade\n"); 
			}
		}
		scanner.close();
	}

	private void askReviewer() {
		System.out.println("reviewer: ");
		scanner = new Scanner(System.in);
		selectedReviewerName = scanner.next(); // ask reviewer
		scanner.close();
	}
}
