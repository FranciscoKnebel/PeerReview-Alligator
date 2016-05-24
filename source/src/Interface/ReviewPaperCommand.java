package Interface;

import domain.Collection;
import domain.Paper;

public class ReviewPaperCommand extends AbstractChairHelperCommand {

	private Collection<Paper> paperList; // mudança
	private Database database;
	private String selectedPaperName; //mudança
	private Paper selectedPaper;

	public void execute() {
		paperList = database.getAllPapers();
		int i = 0;
		for( Paper paper : paperList ) {     //mudança
			System.out.println(i);			 //falar com INGRID TUPAC
			System.out.println(paper.title);
			i++;
		}
		askPaper();
		selectedPaper = searchPaper();
		selectedPaper.showReviewers();
		
		
		
	}

	
	public ReviewPaperCommand(Database database) {

		this.database = database;
		execute();

	}

	private void searchAllocatedPapers() { // mudança de nome do método
											// showAllocatedPapers

	}

	private void showReviwers(Paper paper) {

	}

	private Paper searchPaper() {
		for( Paper paper : paperList ) {     //mudança
			if( paper.title == selectedPaper ) {
				return paper;
			}
		}
	}

	private Researcher searchReviewer(Paper paper) {

	}

	private void askPaper() {
		Scanner scanner = new Scanner(System.in);
		selectedPaperName = scanner.nextString(); // ask paper
	}

	private void askGrade() {

	}

	private void askReviewer() {

	}
}
