package domain;

import java.util.ArrayList;
import java.util.List;

public class Researcher {

	private int id;
	private String name;
	private String affiliation;
	private List<String> researchTopics;
	private int numberOfReviews;
	
	public Researcher(int id, String name, String affiliation, List<String> researchTopics) {
		this.id = id;
		this.name = name;
		this.affiliation = affiliation;
		this.researchTopics = new ArrayList<String>();
		this.researchTopics = researchTopics;
		this.numberOfReviews = 0;
	}
	
	public void incrementNumberOfReviews() {
		numberOfReviews++;
	}
	
	public void decrementNumberOfReviews() {
		numberOfReviews--;
	}
	

	public int getNumberOfReviews() {
		return numberOfReviews;
	}
	
	public String getAffiliation() {
		return affiliation;
	}
	
	public String getName() {
		return this.name;
	}

	public List<String> getResearchTopics() {
		return researchTopics;
	}

	public boolean checkCandidate(Paper paper) {
		return (!checkAffiliation(paper.getAuthor().affiliation) // nao é da msm
																	// universidade
				&& compareResearchTopics(paper.getResearchTopic()) // possui o
																	// topico de
																	// pesquisa
				&& !checkAllocated(paper.getReviewers())); // ainda nao foi
															// alocado para
															// revisar esse
															// artigo
	}

	private boolean checkAffiliation(String authorAffiliation) { // mudança
		if (this.affiliation.equals(authorAffiliation)) {
			return true; // autor e revisor sao da mesma universidade
		} else {
			return false; // universidades diferentes
		}
	}

	private boolean compareResearchTopics(String researchTopic) {
		for (String paperResearchTopic : researchTopics) {
			if (researchTopic.equals(paperResearchTopic)) {
				return true; // autor pesquisa sobre esse topico
			}
		}
		return false; // autor nao pesquisa sobre esse topico
	}

	private boolean checkAllocated(List<Researcher> reviewerList) {
		for (Researcher reviewer : reviewerList) {
			if (reviewer.id == this.id)
				return true; // revisor ja foi alocado nesse paper
		}
		return false;
	}

	public int getId() {
		return this.id;
	}
}