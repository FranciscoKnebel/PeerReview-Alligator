package domain;

import java.util.ArrayList;
import java.util.List;

public class Paper {

	private int id;
	private String title;
	private Researcher author;
	private String researchTopic;
	private String conference;
	private List<Review> reviewsList;

	public Paper(int id, String title, Researcher author, String conference, String researchTopic) { // mudanca
		this.id = id;
		this.title = title;
		this.author = author;
		this.conference = conference;
		this.researchTopic = researchTopic;
		this.reviewsList = new ArrayList<Review>();
	}

	public List<Review> getReviewsList() {
		return reviewsList;
	}

	public String getConference() {
		return conference;
	}

	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public List<Researcher> getReviewers() {
		List<Researcher> reviewersList = new ArrayList<Researcher>();

		for (Review review : reviewsList) {
			reviewersList.add(review.getReviewer());
		}

		return reviewersList;
	}

	public float getMeanGrade() {
		float meanGrade = 0;

		for (Review review : reviewsList)
			meanGrade += review.getGrade();

		meanGrade /= reviewsList.size();

		return meanGrade;
	}

	public void addReviewer(Researcher reviewer) {
		if (reviewer != null) {
			Review newReview = new Review(reviewer);
			reviewer.incrementNumberOfReviews();

			reviewsList.add(newReview);
		}
	}

	public void addReviewer(Researcher reviewer, int grade) {
		if (reviewer != null) {
			Review newReview = new Review(reviewer, grade);
			reviewer.incrementNumberOfReviews();

			reviewsList.add(newReview);
		}
	}

	public Researcher getAuthor() {
		return author;
	}

	public String getResearchTopic() {
		return researchTopic;
	}

	public boolean checkReviews() {
		for (Review review : reviewsList) {
			if (review.isConcluded() == false) {
				return false;
			}
		}
		return true;
	}

	public boolean isAllocated() {
		if (conference != null)
			return true;
		return false;
	}

	public void setReviewGrade(Researcher reviewer, float grade) { //
		for (Review review : reviewsList) {
			if (review.getReviewer().equals(reviewer)) {
				review.setGrade(grade);
				review.setReviewStatus(true);
			}
		}
	}

	public void addReviewToList(Researcher reviewer, float grade) {
		reviewsList.add(new Review(reviewer, grade));
	}

	public List<Review> getNotConcludedReviewsList() { // mudanca --->>era o
														// showReviewers
		List<Review> notConcludedReviewsList = new ArrayList<Review>();

		for (Review review : reviewsList) {
			if (!review.isConcluded()) {
				notConcludedReviewsList.add(review);
			}
		}

		return notConcludedReviewsList;
	}

}
