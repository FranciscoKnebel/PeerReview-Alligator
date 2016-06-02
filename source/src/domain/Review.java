package domain;

public class Review {
	
	private Researcher reviewer;
	private float grade;
	private boolean isConcluded;
	
	public Review(Researcher reviewer, float grade) {
		if(this.reviewer == null) {
			this.reviewer = reviewer;
		}
		reviewer.incrementNumberOfReviews();
		reviewer.decrementNumberOfReviews();
	}

	public Review(Researcher reviewer) {
		if(this.reviewer == null) {
			this.reviewer = reviewer;
		}
		reviewer.incrementNumberOfReviews();
		reviewer.decrementNumberOfReviews();
	}
	
	public float getGrade() {
		return this.grade;
	}

	public Researcher getReviewer() {
		return reviewer;
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
