package test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import model.EFSM;
import model.State;
import model.Transition;

import org.junit.Test;

public class TestAutomata {
	
	@Test
	public void testAddEqualState() {
		EFSM automata = new EFSM();
		
		final double expectedX = 30;
		final double expectedY = 70;
		final String expectedEvent = "touch";
		final HashSet<String> expectedTarget = new HashSet<String>();
		expectedTarget.add("t1");
		expectedTarget.add("t2");
		
		Transition expectedSet
		= new Transition.TransitionBuilder(
				State.newInstance(1), State.newInstance(2))
		.x(expectedX).y(expectedY).event(expectedEvent).target("t1").timestamp(100)
		.createTransition();
		
		Transition t2
		= new Transition.TransitionBuilder(
				State.newInstance(1), State.newInstance(2))
		.x(70).y(50).event("touch").target("t2").timestamp(300)
		.createTransition();
				
		automata.addStateSeq(expectedSet);
		automata.addStateSeq(t2);
		
		Transition t = automata.endTransition();
		
		assertEquals(expectedX, t.getX(), 0.001);
		assertEquals(expectedY, t.getY(), 0.001);
		assertEquals(expectedEvent, t.getEvent());
	}
	
	
	@Test
	public void testState() {
		State s1 = State.newInstance(1);
		State s2 = State.newInstance(1);
		State s3 = State.newInstance(3);
		assertEquals(true, s1.equals(s2));
		assertEquals(false, s1.equals(s3));
	}
	

}
