	package tests;
	
	import static org.junit.Assert.*;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import org.junit.Before;
	import org.junit.Test;
	
	import domain.Conference;
	import domain.Paper;
	import domain.Researcher;
	
	public class TestConference {
	
		Researcher author, author2, researcher, researcher2;
		Paper paper1, paper2, paper3, paper4, paper5;
		Conference conference, conference2, conference3, conference4;
		List<String> researchTopics;
		List<Paper> paperList, paperList2, paperList3, emptyPaperList;
		List<Researcher> researcherList;
	
		@Before
		public void setUp() throws Exception {
			researchTopics = new ArrayList<String>();
			researchTopics.add("engenharia explosiva");
			researchTopics.add("engenharia artistica");
			researchTopics.add("engenharia sem putacao");
			researchTopics.add("engenharia com putacao");
			researcher = new Researcher(0, "2pac em pessoa ao vivo", "UFRGN", researchTopics);
	
			researcher2 = new Researcher(1, "2pac madruga", "ehmaiti", researchTopics);
			
			researcherList = new ArrayList<Researcher>();
			researcherList.add(researcher);
			researcherList.add(researcher2);
	
			researchTopics = new ArrayList<String>();
			researchTopics.add("engenharia explosiva");
			researchTopics.add("engenharia artistica");
			author = new Researcher(1, "josh homme", "MIT", researchTopics);
	
			researchTopics = new ArrayList<String>();
			researchTopics.add("engenharia sem putacao");
			researchTopics.add("engenharia com putacao");
			author2 = new Researcher(2, "Dion Dou", "Am I Tea?", researchTopics);
	
			paper1 = new Paper(0, "TuPacMan vs Lady Pacman", author, "ABC", "engenharia explosiva");
			paper2 = new Paper(1, "papel quadrado", author, "ABC", "engenharia artistica");
			paper3 = new Paper(2, "Estudo Trivial sobre Tupac Amaru Shakur", author2, "ABC", "engenharia sem putacao");
			paper4 = new Paper(3, "Estudo Avançado sobre Tupac Amaru Shakur ", author2, "ABC", "engenharia sem putacao");
			paper5 = new Paper(4, "Oceanografia Quantica", author2, "ABC", "engenharia com putacao");
	
			paper1.addReviewer(researcher, 0);
			paper2.addReviewer(researcher, 3);
			paper3.addReviewer(researcher, -3);
			paper4.addReviewer(researcher);
	
			paperList = new ArrayList<Paper>();
			paperList.add(paper1);
			paperList.add(paper2);
			paperList.add(paper3);
	
			paperList2 = new ArrayList<Paper>();
			paperList2.add(paper4);
			
			paperList3 = new ArrayList<Paper>();
			paperList3.add(paper5);
			
			emptyPaperList = new ArrayList<Paper>();
	
			conference = new Conference(0, "ABC", researcherList, paperList);
			conference2 = new Conference(1, "OVO", researcherList, emptyPaperList);
			conference3 = new Conference(2, "PVC", researcherList, paperList2);
			conference4 = new Conference(3, "CO2", researcherList, paperList3);
		}
	
		@Test
		public void testAllocate() {
			//alocaçao de artigos a membros da conferencia
			
			int dois = 2;
			
			conference4.allocate(dois);
			assertFalse(conference4.areAllPapersReviewed());
			int revisoresPorPaper = 0;
			List<Paper> papers = conference4.getPapersList();
			for(Paper paper: papers){
				assertFalse(paper.checkReviews());
				revisoresPorPaper = paper.getReviewers().size();
				assertTrue(revisoresPorPaper == dois);
			}			
		}
	
		@Test
		public void testGetAcceptedPapers() {
			List<Paper> acceptedPapers;// = new ArrayList<Paper>();
			acceptedPapers = conference.getAcceptedPapers();
	
			for (Paper paper : acceptedPapers) {
				assertTrue(paper.getMeanGrade() >= 0);
			}
	
			List<Paper> acceptedPapers2 = conference2.getAcceptedPapers();
			assertTrue(emptyPaperList.equals(acceptedPapers2));
	
		}
	
		@Test
		public void testGetRejectedPapers() {
			List<Paper> rejectedPapers;// = new ArrayList<Paper>();
			rejectedPapers = conference.getRejectedPapers();
	
			for (Paper paper : rejectedPapers) {
				assertTrue(paper.getMeanGrade() < 0);
			}
	
			List<Paper> rejectedPapers2 = conference2.getRejectedPapers();
			assertTrue(emptyPaperList.equals(rejectedPapers2));
		}
	
		@Test
		public void testAreAllPapersReviewed() {
			boolean areAllPapersReviewed = conference.areAllPapersReviewed();
			assertTrue(areAllPapersReviewed);
			
			areAllPapersReviewed = conference2.areAllPapersReviewed();
			assertTrue(areAllPapersReviewed);
			
			for (Paper paper : conference.getPapersList()) {
				assertTrue(paper.getNotConcludedReviewsList() != null);
			}
			
			areAllPapersReviewed = conference3.areAllPapersReviewed();
			assertFalse(areAllPapersReviewed);
			
			areAllPapersReviewed = conference4.areAllPapersReviewed();
			assertFalse(areAllPapersReviewed);
	
			boolean notAllReviewed = false;	
			for (Paper paper : conference3.getPapersList()) {
				if (paper.getNotConcludedReviewsList() != null)
					notAllReviewed = true;
			}
	
			assertTrue(notAllReviewed);
		}
	}