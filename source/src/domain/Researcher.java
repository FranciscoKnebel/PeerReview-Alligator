package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Researcher {

	private int id;
	private String name;
	private String affiliation;
	private List<String> researchTopics;
	private int numberOfReviews;
	private HashMap<Conference, Integer> numberOfAllocatedPapersByConference;
	
	public Researcher(int id, String name, String affiliation, List<String> researchTopics) {
		this.id = id;
		this.name = name;
		this.affiliation = affiliation;
		this.researchTopics = new ArrayList<String>();
		this.researchTopics = researchTopics;
		this.numberOfReviews = 0;
		this.numberOfAllocatedPapersByConference = new HashMap<Conference, Integer>();
	}
	
	public Integer getNumberOfAllocatedPapersForConference(Conference conference) {
		Integer number = this.numberOfAllocatedPapersByConference.get(conference);
		return (number == null) ? 0 : number;
	}
	
	public void incrementNumberOfAllocatedPapersForConference(Conference conference) {
		Integer number = this.numberOfAllocatedPapersByConference.get(conference);
		this.numberOfAllocatedPapersByConference.put(conference, ((number == null) ? 1 : number+1));
	}
	public void decrementNumberOfAllocatedPapers(Conference conference) {
		Integer number = this.numberOfAllocatedPapersByConference.get(conference);
		this.numberOfAllocatedPapersByConference.put(conference, ((number == null) || (number == 0) ? 0 : number-1));
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
		return (!checkAffiliation(paper.getAuthor().affiliation) // nao ï¿½ da msm
																	// universidade
				&& compareResearchTopics(paper.getResearchTopic()) // possui o
																	// topico de
																	// pesquisa
				&& !checkAllocated(paper.getReviewers())); // ainda nao foi
															// alocado para
															// revisar esse
															// artigo
	}

	private boolean checkAffiliation(String authorAffiliation) { // mudanï¿½a
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
	
	public Boolean validateForPaper(Paper paper) {
		Boolean sameUniversity = this.getAffiliation().equals(paper.getAuthor().getAffiliation());
		Boolean isAuthor = this.equals(paper.getAuthor());
		Boolean hasResearchTopicInterest = this.getResearchTopics().contains(paper.getResearchTopic());
		Boolean alreadyAssigned = paper.getReviewers().contains(this);
		return !sameUniversity && !isAuthor && hasResearchTopicInterest && !alreadyAssigned;
	}
}