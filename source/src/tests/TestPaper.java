package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import domain.Paper;
import domain.Researcher;
import domain.Review;

public class TestPaper {
	Researcher author, reviewer, reviewer2;
	Paper paper, paper2;
	List<String> researchTopics;
	
	
	@Before
	public void setUp() throws Exception {
		researchTopics = new ArrayList<String>();
		researchTopics.add("Nada");	
		researchTopics.add("Nado");	
		author = new Researcher(0, "Bob", "UFRGS", researchTopics);
		reviewer = new Researcher(1, "Jones o esqueleto", "UFRGS-X", researchTopics);
		reviewer2 = new Researcher(2, "BoboB", "UFRGS2", researchTopics);
		paper = new Paper(0, "Paper de Teste", author, "ABC", "Nado");
		paper.addReviewToList(reviewer, 0);
		paper2 = new Paper(1, "Paper suave", author, null, "Nado");
	}
	
	@Test
	public void testGetReviewers() {
		List<Researcher> returnedReviewers, reviewers;
		returnedReviewers = paper.getReviewers();
		reviewers = new ArrayList<Researcher>();
		reviewers.add(reviewer);
		
		assertTrue(reviewers.equals(returnedReviewers));
	}

	@Test
	public void testAddReviewer() {
		paper.addReviewer(reviewer2);
		assertFalse(paper.checkReviews());
	}

	@Test
	public void testCheckReviews() {
		assertTrue(paper.checkReviews());
		
		paper.addReviewer(reviewer2);
		assertFalse(paper.checkReviews());
	}

	@Test
	public void testIsAllocated() {
		assertTrue(paper.isAllocated());
		assertFalse(paper2.isAllocated());
	}

	@Test
	public void testSetReviewGrade() {
		assertTrue(paper2.checkReviews());
		
		paper2.addReviewer(reviewer2);
		assertFalse(paper2.checkReviews());
		
		paper2.setReviewGrade(reviewer2, 0);
		assertTrue(paper2.checkReviews());
	}
	
	@Test
	public void testGetNotConcludedReviewsList() {
		List<Review> notConcludedReviewsList;
		notConcludedReviewsList = paper.getNotConcludedReviewsList();
		for (Review review : notConcludedReviewsList) {
			assertFalse(review.isConcluded());
		}
	}
}
