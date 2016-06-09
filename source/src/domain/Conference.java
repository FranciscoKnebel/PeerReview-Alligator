package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Conference {
	private int id;
	private String acronym;
	private List<Paper> papersList;
	private List<Researcher> membersList;
	public Conference(int id, String acronym, List<Researcher> members, List<Paper> papers) { // add id
		this.id = id;
		this.acronym = acronym;
		this.papersList = papers;
		this.membersList = members;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public List<Paper> getPapersList() {
		return this.papersList;
	}

	public void allocate(int numReviewers) {
		System.out.print("Iniciando alocação \n");

		/*if (numReviewers < 2 || numReviewers > 5) {
			// throw exception
		} handled on conferenceallocatecommand

		if (numReviewers > membersList.size()) {
			// throw errooooooou
		} no clue what this is for*/

		papersList = orderPapersById(papersList);
		membersList = orderResearchersById(membersList);
		
		for (int i = 0; i < numReviewers; i++) {
			List<Paper> tmpPaperList = new ArrayList<Paper>(papersList);

			int numberOfPapers = papersList.size();


			for (int j = 0; j < numberOfPapers; j++) {

				List<Researcher> tmpCandidatesList = new ArrayList<Researcher>();

				Paper firstPaper = getFirstPaper(tmpPaperList);
				

				for (int k = 0; k < membersList.size(); k++) {

					Researcher candidate = membersList.get(k);
					if (candidate.checkCandidate(firstPaper)) {
						tmpCandidatesList.add(candidate);
					}

				}

				tmpCandidatesList = orderResearchersByNumReviews(tmpCandidatesList);
//				System.out.print("Testando candidatos para o paper: " + firstPaper.getId() + "\n");
//				for (int k = 0; k < tmpCandidatesList.size(); k++) {
//					TUPAC VIVE!!s!
//					System.out.print("candidate ordenado por numPapers e id?: "
//							+ tmpCandidatesList.get(k).getNumberOfReviews() + " "
//							+ tmpCandidatesList.get(k).getId() + "\n");
//				}

				Researcher firstCandidate = getFirstCandidate(tmpCandidatesList);
	
				showAllocationLog(firstPaper, firstCandidate);

				firstPaper.addReviewer(firstCandidate);
				tmpPaperList.remove(firstPaper);
				
			}
		}
		// areMembersAllocated = areMembersAllocated(numReviewers); implement

	}

	private void showAllocationLog(Paper paper, Researcher researcher) {
		if (researcher == null) {
			System.out.print("Artigo ID: " + paper.getId() + " não tem possíveis candidatos" + "\n");
		} else {
			System.out.print("Artigo ID: " + paper.getId() + " alocado ao revisor ID: " + researcher.getId() + "\n");
		}
	}

	private List<Paper> orderPapersById(List<Paper> aPaperList) { // mudou de generateOrderedPapersList
		Collections.sort(aPaperList, new Comparator<Paper>() {
			@Override
			public int compare(Paper p1, Paper p2) {
				return p1.getId() - p2.getId();
			}
		});
		return aPaperList;
	}

	private List<Researcher> orderResearchersById(List<Researcher> aResearcherList) {
		Collections.sort(aResearcherList, new Comparator<Researcher>() {
			@Override
			public int compare(Researcher p1, Researcher p2) {
				return p1.getId() - p2.getId();
			}
		});
		return aResearcherList;
	}

	private List<Researcher> orderResearchersByNumReviews(List<Researcher> aResearcherList) {
		Collections.sort(aResearcherList, new Comparator<Researcher>() {
			@Override
			public int compare(Researcher p1, Researcher p2) {
				return p1.getNumberOfReviews() - p2.getNumberOfReviews();
			}
		});
		return aResearcherList;
	}

	public List<Paper> getAcceptedPapers() {
		List<Paper> acceptedPapersList = new ArrayList<Paper>();
		for (Paper paper : papersList) {
			if (paper.getMeanGrade() >= 0) {
				acceptedPapersList.add(paper);
			}
		}
		orderPapersByMeanGrade(acceptedPapersList);
		return acceptedPapersList;
	}

	public List<Paper> getRejectedPapers() {
		List<Paper> rejectedPapersList = new ArrayList<Paper>();
		for (Paper paper : papersList) {
			if (paper.getMeanGrade() < 0) {
				rejectedPapersList.add(paper);
			}
		}
		orderPapersByMeanGrade(rejectedPapersList);
		return rejectedPapersList;
	}

	public boolean areAllPapersReviewed() {
		for (Paper paper : papersList) {
			if (paper.checkReviews() == false) {
				return false;
			}
		}
		return true;
	}

	public int getId() {
		return this.id;
	}

	private void orderPapersByMeanGrade(List<Paper> aPaperList) { // ordem
																	// decrescente
		Collections.sort(aPaperList, new Comparator<Paper>() {
			@Override
			public int compare(Paper p1, Paper p2) {
				return p1.getId() + p2.getId();
			}
		});
	}

	private Paper getFirstPaper(List<Paper> papers) {
		return papers.get(0);
	}

	private Researcher getFirstCandidate(List<Researcher> candidates) {
		if (candidates.size() == 0) {
			return null;
		} else {
			return candidates.get(0);
		}
	}

	// private List<Researcher> reorderReviewerList(List<Researcher> reviewers)
	// //agora eh order byNum...
	// {
	//eliminado? n precisa mais dessa funcao? wat
	// }
	
	// private void removeTopPaper() { //eliminado, usando remove
	//
	// }
	//

	// }

}