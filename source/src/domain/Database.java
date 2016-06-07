package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Database {

	private final List<Conference> conferenceList;
	private final List<Paper> paperList;
	private final List<Researcher> researcherList;

	public Database() {
		this(true);
	}

	public Database(boolean initializeData) {
		this.conferenceList = new ArrayList<Conference>();
		this.paperList = new ArrayList<Paper>();
		this.researcherList = new ArrayList<Researcher>();

		if (initializeData) {
			initData();
		}
	}

	public Collection<Conference> getAllConferences() {
		return conferenceList;
	}

	public Collection<Paper> getAllPapers() {
		return paperList;
	}

	public Collection<Researcher> getAllResearchers() {
		return researcherList;
	}

	public Conference getConference(String conferenceAcronym) {
		for (int i = 0; i < conferenceList.size(); i++) {
			Conference conference = conferenceList.get(i);

			if (conference.getAcronym() == conferenceAcronym)
				return conference;
		}
		
		return null;
	}

	public Paper getPaper(Integer paperId) {
		for (int i = 0; i < paperList.size(); i++) {
			Paper paper = paperList.get(i);
			
			if(paper.getId() == paperId) {
				return paper;
			}
		}
		
		return null;
	}

	public Researcher getResearcher(Integer researcherId) {
		for (int i = 0; i < researcherList.size(); i++) {
			Researcher researcher = researcherList.get(i);

			if (researcher.getId() == researcherId) {
				return researcher;
			}
		}
		
		return null;
	}

	public void save(Conference conference) { //trocar três save por um, com 3 overloads
		this.conferenceList.add(conference);
	}

	public void save(Paper paper) { //trocar três save por um, com 3 overloads
		this.paperList.add(paper);
	}

	public void save(Researcher researcher) { //trocar três save por um, com 3 overloads
		this.researcherList.add(researcher);
	}
	
	private void initData() {
		//Database Initial data
		//Researchers
		int id = 1;
		
		List<String> researchTopics1 = new ArrayList<>(Arrays.asList("Software Product Lines", "Software Reuse", "Modularity"));
		initResearcher(id++, "João", "UFRGS", researchTopics1);
		
		List<String> researchTopics2 = new ArrayList<>(Arrays.asList("Software Architecture", "Modularity", "Software Reuse"));
		initResearcher(id++, "Ana", "USP", researchTopics2);

		List<String> researchTopics3 = new ArrayList<>(Arrays.asList("Software Product Lines", "Software Testing"));
		initResearcher(id++, "Manoel", "UFRGS", researchTopics3);

		List<String> researchTopics4 = new ArrayList<>(Arrays.asList("Software Product Lines", "Software Reuse", "Software Architecture"));
		initResearcher(id++, "Joana", "UFRJ", researchTopics4);
		
		List<String> researchTopics5 = new ArrayList<>(Arrays.asList("Software Architecture", "Modularity", "Software Testing"));
		initResearcher(id++, "Miguel", "UFRGS", researchTopics5);

		List<String> researchTopics6 = new ArrayList<>(Arrays.asList("Software Reuse", "Software Testing", "Aspect-oriented Programming"));
		initResearcher(id++, "Beatriz", "UFRJ", researchTopics6);

		List<String> researchTopics7 = new ArrayList<>(Arrays.asList("Aspect-Oriented Programming", "Modularity", "Software Reuse"));
		initResearcher(id++, "Suzana", "UFRGS", researchTopics7);
		
		List<String> researchTopics8 = new ArrayList<>(Arrays.asList("Modularity", "Software Reuse", "Software Quality", "Software Product Lines"));
		initResearcher(id++, "Natasha", "UFRJ", researchTopics8);

		List<String> researchTopics9 = new ArrayList<>(Arrays.asList("Aspect-Oriented Programming", "Software Architecture"));
		initResearcher(id++, "Pedro", "USP", researchTopics9);

		List<String> researchTopics10 = new ArrayList<>(Arrays.asList("Software Reuse", "Modularity", "Software Testing"));
		initResearcher(id++, "Carlos", "USP", researchTopics10);
		
		//Papers
		id = 1;		
		initPaper(id++, "Coupling and Cohesion", 	getResearcher(1), 	"SBES", "Modularity");
		initPaper(id++, "Design Patterns", 			getResearcher(6), 	"FSE",  "Software Reuse");
		initPaper(id++, "AspectJ", 					getResearcher(7), 	"FSE",  "Aspect-oriented Programming");
		initPaper(id++, "Feature Model", 			getResearcher(8), 	"FSE",  "Software Product Lines");
		initPaper(id++, "Architecture Recovery", 	getResearcher(9), 	"FSE",  "Software Architecture");
		initPaper(id++, "Functional Testing", 		getResearcher(10), 	"FSE",  "Software Testing");
		initPaper(id++, "COTs", 					getResearcher(6), 	"ICSE", "Software Reuse");
		initPaper(id++, "Pointcut", 				getResearcher(7), 	"ICSE", "Aspect-oriented Programming");
		initPaper(id++, "Product Derivation", 		getResearcher(8), 	"ICSE", "Software Product Lines");
		initPaper(id++, "Architecture Comformance", getResearcher(9), 	"ICSE", "Software Architecture");
		initPaper(id++, "Structural Testing", 		getResearcher(10), 	"ICSE", "Software Testing");

	
		//Conferences
		List<Researcher> conferenceMembers1 = new ArrayList<>();
		conferenceMembers1.add(getResearcher(1));
		conferenceMembers1.add(getResearcher(2));
		conferenceMembers1.add(getResearcher(3));
		conferenceMembers1.add(getResearcher(4));
		conferenceMembers1.add(getResearcher(5));
		conferenceMembers1.add(getResearcher(6));
		conferenceMembers1.add(getResearcher(7));
		initConference("ICSE", conferenceMembers1, getPapersInConference("ICSE"));
		
		List<Researcher> conferenceMembers2 = new ArrayList<>();
		conferenceMembers2.add(getResearcher(1));
		conferenceMembers2.add(getResearcher(2));
		conferenceMembers2.add(getResearcher(3));
		conferenceMembers2.add(getResearcher(4));
		conferenceMembers2.add(getResearcher(5));
		conferenceMembers2.add(getResearcher(6));
		conferenceMembers2.add(getResearcher(7));
		initConference("FSE", conferenceMembers2, getPapersInConference("FSE"));

		List<Researcher> conferenceMembers3 = new ArrayList<>();
		conferenceMembers3.add(getResearcher(4));
		conferenceMembers3.add(getResearcher(5));
		conferenceMembers3.add(getResearcher(6));
		conferenceMembers3.add(getResearcher(7));
		conferenceMembers3.add(getResearcher(8));
		conferenceMembers3.add(getResearcher(9));
		conferenceMembers3.add(getResearcher(10));
		initConference("SBES", conferenceMembers3, getPapersInConference("SBES"));
		
		initReviewPaper(1, 8, 2);
		initReviewPaper(1, 10);
		initReviewPaper(2, 7, 2);
		initReviewPaper(2, 2, 3);
		initReviewPaper(3, 4, -1);
		initReviewPaper(3, 6, 1);
		initReviewPaper(4, 1, 1);
		initReviewPaper(4, 3, 0);
		initReviewPaper(5, 4, -3);
		initReviewPaper(5, 5, -3);
		initReviewPaper(6, 3, -1);
		initReviewPaper(6, 6, 0);
	}

	private void initResearcher(int id, String name, String affiliation, List<String> researchTopics) {
		Researcher researcher = new Researcher(id, name, affiliation, researchTopics);
		save(researcher);
	}

	private void initConference(String acronym, List<Researcher> members, List<Paper> papers) {
		Conference conference = new Conference(acronym, members, papers);
		save(conference);
	}

	private void initPaper(int id, String title, Researcher author, String conference, String researchTopic) {
		Paper paper = new Paper(id, title, author, conference, researchTopic);
		save(paper);
	}

	private void initReviewPaper(int paperID, int reviewerID, int grade) {
		Paper selectedPaper = getPaper(paperID);
		Researcher selectedReviewer = getResearcher(reviewerID);
		
		selectedPaper.addReviewer(selectedReviewer, grade);		
	}
	
	private void initReviewPaper(int paperID, int reviewerID) {
		Paper selectedPaper = getPaper(paperID);
		Researcher selectedReviewer = getResearcher(reviewerID);
		
		selectedPaper.addReviewer(selectedReviewer);
	}
	
	private List<Paper> getPapersInConference(String acronym) {
		List<Paper> paperList = new ArrayList<>();
		
		for(int i = 0; i < paperList.size(); i++) {
			Paper paper = paperList.get(i);
			
			if(paper.getConference() == acronym) {
				paperList.add(paper);
			}
		}
		
		return paperList;
	}
	
}
