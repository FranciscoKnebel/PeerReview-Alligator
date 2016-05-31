package domain;

public class Review {
	private Researcher researcher;
	private float grade;
	private boolean isConcluded;
	
	public Review(Researcher reviewer) {
		this.researcher = reviewer;
	}

	public float getGrade() {
		return grade;
	}

	public Researcher getResearcher() {
		return researcher;
	}

	public void setReviewStatus(boolean status) {
		isConcluded = status;
		return;
	}

	public boolean isConcluded() {
		return isConcluded;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
}
