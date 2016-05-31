package domain;

import java.util.List;

public class Paper {
	private int id;
	private String title;
	private Researcher author;
	private String researchTopic;
	private String conference;
	private List<Review> reviewList;

	

	public Paper(int id, String title, int author, Conference conference, String reasearchTopic) {

	}

	public float getMeanGrade() {
		return 10; // COMENTARIO MT HORRIVEL, TIRAR
	}

	public void addReviewer(Researcher reviewer) {
		Review newReview = new Review(reviewer);
		reviewList.add(newReview);
	}

	public int getAuthor() {
		return author; // seria o id?
	}

	public String getResearchTopic() {
		return researchTopic;
	}

	public boolean checkReviews() {
		for (int i = 0; i < reviewList.size(); i++){
			if(reviewList.get(i).isConcluded() == false){
				return false;
			}
		}
		return true;
	}

	public boolean isAllocated() {
		
	}

	public void setReviewGrade(Researcher reviewer, float grade) {

	}

	public void showReviewers() { // mudanca

	}
}
