package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Researcher;
import domain.Review;

public class TestReview {
	Review review, review2;
	Researcher reviewer, reviewer2;
	List<String> researchTopics;
	
	
	@Before
	public void setUp() throws Exception {
		researchTopics = new ArrayList<String>();
		researchTopics.add("Nada");	
		researchTopics.add("Nado");	
		reviewer = new Researcher(1, "Jones o esqueleto", "UFRGS-X", researchTopics);
		reviewer2 = new Researcher(2, "BoboB", "UFRGS2", researchTopics);
		review = new Review(reviewer, 0);
		review2 = new Review(reviewer2);
	}

	@Test
	public void testSetReviewStatus() {
		review.setReviewStatus(true);
		assertTrue(review.isConcluded());
		review.setReviewStatus(false);
		assertFalse(review.isConcluded());
	}

	@Test
	public void testIsConcluded() {
		assertTrue(review.isConcluded());
		assertFalse(review2.isConcluded());
	}
	
	@Test
	public void testSetGrade(){
		review.setGrade(1);
		float grade = review.getGrade();
		assertTrue(grade == 1);
	}
}
