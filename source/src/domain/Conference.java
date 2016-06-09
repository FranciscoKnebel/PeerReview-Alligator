package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Interface.ui.TextManager;
import Interface.ui.text.UIUtils;

public class Conference {
	private int id;
	private String acronym;
	private List<Paper> papersList;
	private List<Researcher> membersList;
	
	public Conference(int id, String acronym, List<Researcher> members, List<Paper> papers) {
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

	public List<String> allocate(int numReviewers) {
		UIUtils uiUtils = UIUtils.INSTANCE;
		TextManager textManager = uiUtils.getTextManager();
		
		List<String> log = new ArrayList<>();
		log.add(textManager.getText("log.initiation"));
		
		papersList = orderPapersById(papersList);
		membersList = orderResearchersById(membersList);
		
		for (int i = 0; i < numReviewers; i++) {
			List<Paper> tmpPaperList = new ArrayList<Paper>(papersList);

			int numberOfPapers = papersList.size();

			log.addAll(sortAllocations(tmpPaperList, numberOfPapers, textManager));
		}
		
		log.add(textManager.getText("log.end"));
		return log;
	}
	
	private List<String> sortAllocations(List<Paper> tmpPaperList, int numberOfPapers, TextManager textManager) {
		List<String> log = new ArrayList<>();
		
		for (int j = 0; j < numberOfPapers; j++) {
			List<Researcher> tmpCandidatesList = new ArrayList<Researcher>();

			Paper firstPaper = getFirstPaper(tmpPaperList);

			tmpCandidatesList = qualifyCandidate(firstPaper);

			tmpCandidatesList = orderResearchersByNumReviews(tmpCandidatesList);

			Researcher firstCandidate = getFirstCandidate(tmpCandidatesList);

			log.add(getCandidateInfo(firstCandidate, firstPaper, textManager));
			
			firstPaper.addReviewer(firstCandidate);
			tmpPaperList.remove(firstPaper);
		}
		
		return log;
	}

	private String getCandidateInfo(Researcher researcher, Paper paper, TextManager textManager){
		if (researcher == null) {
			return textManager.getText("log.candidateNull", Integer.toString(paper.getId()));
		} else {
			String[] args = {Integer.toString(paper.getId()), Integer.toString(researcher.getId())};
			return textManager.getText("log.candidate", args);
		}
	}

	private List<Paper> orderPapersById(List<Paper> aPaperList) {
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

	private void orderPapersByMeanGrade(List<Paper> aPaperList) {
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
	
	private List<Researcher> qualifyCandidate(Paper firstPaper) {
		List<Researcher> candidateList = new ArrayList<Researcher>();
		
		for (int k = 0; k < membersList.size(); k++) {
			Researcher candidate = membersList.get(k);
			if (candidate.checkCandidate(firstPaper)) {
				candidateList.add(candidate);
			}
		}
		
		return candidateList;
	}

}