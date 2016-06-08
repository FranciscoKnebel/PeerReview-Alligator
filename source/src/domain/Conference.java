package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Conference {
	private int id;
	private String acronym;
	private List<Paper> papersList;
	private List<Researcher> membersList;
	private boolean areMembersAllocated;
	private List<Researcher> tempList;

	public Conference(int id, String acronym, List<Researcher> members, List<Paper> papers) { //add id
		this.id	= id;
		this.acronym = acronym;
		this.papersList = papers;
		this.membersList = members;
	}

	public String getAcronym() {
		return this.acronym;
	}
	
	private boolean areMembersAllocated(int numReviewers) {
		for (Paper paper : papersList) {
			if(paper.getReviewers().size() != numReviewers){
				return false;
			}
		}
		return true;
	}

	public void allocate(int numReviewers) {
		if (numReviewers < 2 || numReviewers > 5){ 
			// throw exception
		}
		
		if (numReviewers > membersList.size()) {
			// throw errooooooou
		}
		
		orderPapers();
		orderMembers();

		
		for (int i = 0; i < numReviewers; i++) {
			List<Paper> tmpPaperList = papersList;
			Collections.sort(tmpPaperList, orderPapersById);
			
			Paper firstPaper = tmpPaperList.get(0);
			List<Researcher> tmpResearcherList = membersList;
			
			int tmpPaperListSize = tmpPaperList.size();
			
			for (int j = 0; j < tmpPaperListSize; j++) {
				Collections.sort(tmpResearcherList, orderResearchersById);
				
				for (Researcher researcher : tmpResearcherList) {
					if (!researcher.validateForPaper(firstPaper)) {
						tmpResearcherList.remove(researcher);
					}				
				}
				orderResearchersByAllocatedPapersForConference(tmpResearcherList, this);
				Researcher firstCandidate = tmpResearcherList.get(0);
				tempList.add(firstCandidate);
				firstCandidate.incrementNumberOfAllocatedPapersForConference(this);
				tmpPaperList.remove(firstPaper);	
			}
		}
		areMembersAllocated = areMembersAllocated(numReviewers);
		
	}
	
	public List<Paper> getAcceptedPapers() {
		 /* 	a. Artigos cuja media de notas e >= 0, aparecem na lista de artigos aceitos,
		 *  	em ordem decrescente
		 */

		return papersList;
	}

	public List<Paper> getRejectedPapers() {
		 /*  	b. Artigos cuja media de notas e < 0, aparecem na lista
		 * 		de artigos rejeitados, em ordem crescente
		 */
		return papersList;
	}

	public boolean areAllPapersReviewed() {
		for (Paper paper : papersList) {
			if (paper.getNotConcludedReviewsList() != null) {
				return false;
			}
		}
		return true;
	}

	public int getId() {
		return this.id;
	}
	
	private Comparator<Paper> orderPapersById = new Comparator<Paper>() {
        @Override
        public int compare(Paper p1, Paper p2) {
            return p1.getId() - p2.getId();
        }
    };
	
	private Comparator<Researcher> orderResearchersById = new Comparator<Researcher>() {
        @Override
        public int compare(Researcher r1, Researcher r2) {
            return r1.getId() - r2.getId();
        }
    };
    
    
    private List<Researcher> orderResearchersByAllocatedPapersForConference(List<Researcher> researchers, Conference conference) {
    	Collections.sort(researchers, new Comparator<Researcher>() {
    		@Override
    		public int compare(Researcher r1, Researcher r2) {
    			Integer diff = r1.getNumberOfAllocatedPapersForConference(conference) - r2.getNumberOfAllocatedPapersForConference(conference);
    			return (diff == 0) ? r1.getId() - r2.getId() : diff;
    		}
    	});
    	return researchers;
    };

	private void orderPapers() { //mudou de genareteOrderedePapersList
		Collections.sort(papersList, new Comparator<Paper>() {
	        @Override
	        public int compare(Paper p1, Paper p2) {
	            return p1.getId() - p2.getId();
	        }
	    });
	}


	private void orderMembers() {
		Collections.sort(membersList, new Comparator<Researcher>() {
	        @Override
	        public int compare(Researcher p1, Researcher p2) {
	            return p1.getId() - p2.getId();
	        }
	    });
	}

	private Paper getFirstPaper(List<Paper> papers) {
		return papers.get(0);
	}

	private Researcher getFirstCandidate(List<Researcher> candidates) {
		return candidates.get(0);
	}

	private List<Researcher> reorderReviewerList(List<Researcher> reviewers) {
		return reviewers; //eliminado
	}

	private void removeTopPaper() { //eliminado,
		
	}

	private void addReviewerToTempList(Researcher researcher) { //nao usa mais

	}

	private void saveReviewers(List<Researcher> tempList) { //nao usa

	}

	private void showAllocationLog() { //implementar

	}

}
