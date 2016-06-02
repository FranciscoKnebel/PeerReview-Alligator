package domain;

import java.util.List;

public class Database {

	private final List<Conference> conferencesList;
	private final List<Paper> papersList;
	private final List<Researcher> researchersList;

	public Database() {
		initData();
	}

	private void initData() {
		conferencesList.add(0, new Conference() );
		conferencesList.add(1, new Conference() );
		conferencesList.add(2, new Conference() );
		
		papersList.add(0, new Paper() );
		papersList.add(1, new Paper() );
		papersList.add(2, new Paper() );
		
		researchersList.add(0, new Researcher() );
		researchersList.add(1, new Researcher() );
		researchersList.add(2, new Researcher() );
	}

	public Collection<Conference> getAllConferences() {
	}

	public Collection<Paper> getAllPapers() {
	}

	public Collection<Researcher> getAllResearchers() {
	}

	public Conference getConference(String conferenceAcronym) {
	}

	public Paper getPaper(Integer paperId) {
	}

	public Researcher getResearcher(Integer researcherId) {
	}

	public void saveConfecerece(Conference conference) {
	}

	public void savePaper( Paper paper ) {
		
	}

	public void saveResearcher(Researcher researcher) {
		
	}
}
