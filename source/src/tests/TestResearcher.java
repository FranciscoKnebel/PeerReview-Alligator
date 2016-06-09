package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Conference;
import domain.Paper;
import domain.Researcher;

public class TestResearcher {
	Researcher author, researcher, researcher2, researcher3;
	Paper paper;
	Conference conference;
	List<String> researchTopics;

	@Before
	public void setUp() throws Exception {
		researchTopics = new ArrayList<String>();
		researchTopics.add("Nadica");	
		researchTopics.add("Nada");	
		researcher = new Researcher(0, "Bob", "UFRGS2", researchTopics);
		
		researchTopics = new ArrayList<String>();
		researchTopics.add("Nada");	
		researchTopics.add("Nadica");	
		researcher2 = new Researcher(1, "BoboB", "UFRGS", researchTopics);
		
		researchTopics = new ArrayList<String>();
		researchTopics.add("Nado");	
		researchTopics.add("Nadica");	
		researcher3 = new Researcher(1, "Master of puppets", "UFRGS2", researchTopics);
		
		researchTopics = new ArrayList<String>();
		researchTopics.add("Nada");	
		researchTopics.add("Nado");	
		author = new Researcher(1, "Dion Dou", "UFRGS", researchTopics);
		
		paper = new Paper(0, "Paper de Teste", author, "ABC", "Nada");
		
		conference = new Conference(0, "ABC", null, null);
	}
	
	@Test
	public void testGetNumberOfAllocatedPapersForConference() {
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 0);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(null) == 0);
		researcher.incrementNumberOfAllocatedPapersForConference(conference);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 1);
		researcher.decrementNumberOfAllocatedPapers(conference);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 0);
	}
	
	@Test
	public void testIncrementNumberOfAllocatedPapersForConference() {
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 0);
		researcher.incrementNumberOfAllocatedPapersForConference(conference);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 1);
		researcher.incrementNumberOfAllocatedPapersForConference(conference);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 2);
	}
	
	@Test
	public void testDecrementNumberOfAllocatedPapers() {
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 0);
		//researcher.decrementNumberOfAllocatedPapers(conference);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 0);
		researcher.incrementNumberOfAllocatedPapersForConference(conference);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 1);
		researcher.decrementNumberOfAllocatedPapers(conference);
		assertTrue(researcher.getNumberOfAllocatedPapersForConference(conference) == 0);
	}
	
	@Test
	public void testIncrementNumberOfReviews() {
		assertTrue(researcher.getNumberOfReviews() == 0);
		researcher.incrementNumberOfReviews();
		assertTrue(researcher.getNumberOfReviews() == 1);
	}

	@Test
	public void testDecrementNumberOfReviews() {
		assertTrue(researcher.getNumberOfReviews() == 0);
		researcher.decrementNumberOfReviews();
		assertTrue(researcher.getNumberOfReviews() == -1);
	}

	@Test
	public void testCheckCandidate() {
		//universidades diferentes  && possui o topico && ainda nao alocado
		assertTrue(researcher.checkCandidate(paper));
		
		//autor e revisor da mesma univesidade
		assertFalse(researcher2.checkCandidate(paper));
		//revisor nao tem o topico de pesquisa do paper
		assertFalse(researcher3.checkCandidate(paper));
		//revisor ja alocado para esse paper
		paper.addReviewer(researcher);
		assertFalse(researcher.checkCandidate(paper));
		
	}
}
