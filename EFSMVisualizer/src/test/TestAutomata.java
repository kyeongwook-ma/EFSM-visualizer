package test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import model.Automata;
import model.State;
import model.Transition;

import org.junit.Test;

public class TestAutomata {

	private Automata automata = new Automata();
	
	@Test
	public void test() {
		// fail("Not yet implemented");
	}
	
	@Test
	public void testAddEqualState() {
		Automata automata = new Automata();
		
		final double expectedX = 30;
		final double expectedY = 70;
		final String expectedEvent = "touch";
		final HashSet<String> expectedTarget = new HashSet<String>();
		expectedTarget.add("t1");
		expectedTarget.add("t2");
		
		Transition expectedSet
		= new Transition.TransitionBuilder(new State(1), new State(2))
		.x(expectedX)
		.y(expectedY)
		.event(expectedEvent)
		.target("t1")
		.timestamp(100)
		.createTransition();
		
		Transition t2
		= new Transition.TransitionBuilder(new State(1), new State(2))
		.x(70)
		.y(50)
		.event("touch")
		.target("t2")
		.timestamp(300)
		.createTransition();
				
		automata.addStateSeq(expectedSet);
		automata.addStateSeq(t2);
		
		Transition t = automata.endTransition();
		
		assertEquals(expectedX, t.getX(), 0.001);
		assertEquals(expectedY, t.getY(), 0.001);
		assertEquals(expectedEvent, t.getEvent());
		assertEquals(expectedTarget.iterator(), t.getTargets());
	}
	
	

}
