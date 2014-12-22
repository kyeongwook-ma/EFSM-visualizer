package test;

import model.Automata;
import model.State;
import model.Transition;

public class TestMerge {

	public void testMergeWithGKTail() {
				
		Automata answerAutomata = getAnswerSeq();
		
			
		
	}
	
	private Automata getAnswerSeq() {
		
		Automata answerAutomata = new Automata();
				
		Transition t1 = 
		new Transition.TransitionBuilder(State.newInstance(1), State.newInstance(2))
		.x(30).y(40).event("onSlide").timestamp(100).target("e")
		.createTransition();
		
		Transition t2 = 
		new Transition.TransitionBuilder(State.newInstance(2), State.newInstance(3))
		.x(30).y(40).event("onSlide").timestamp(300).target("d")
		.createTransition();
		
		
		
		return answerAutomata;
		
	}
	
	
	
}
