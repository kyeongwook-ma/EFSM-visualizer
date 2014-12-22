package test;

import model.Automata;
import model.EFSMController;
import model.State;
import model.Transition;

public class TestMerge {

	public void testMergeWithGKTail() {
				
		Automata dstAutomata = getDstAutomata();
		Automata srcAutomata = getSrcAutomata();
	
		EFSMController.merge(srcAutomata, dstAutomata, 2);
	}
	
	private Automata getSrcAutomata() {
		
		Automata srcAutomata = new Automata();
		
		Transition t1 = 
		new Transition.TransitionBuilder(State.newInstance(1), State.newInstance(2))
		.x(30).y(40).event("onSlide").timestamp(100).target("e")
		.createTransition();
		
		Transition t2 = 
		new Transition.TransitionBuilder(State.newInstance(2), State.newInstance(3))
		.x(30).y(40).event("onSlide").timestamp(300).target("d")
		.createTransition();
		
		srcAutomata.addStateSeq(t1,t2);
		
		return srcAutomata;
	}
	
	private Automata getDstAutomata() {
		
		Automata dstAutomata = new Automata();
				
		Transition t1 = 
		new Transition.TransitionBuilder(State.newInstance(1), State.newInstance(2))
		.x(30).y(40).event("onSlide").timestamp(100).target("e")
		.createTransition();
		
		Transition t2 = 
		new Transition.TransitionBuilder(State.newInstance(2), State.newInstance(3))
		.x(30).y(40).event("onSlide").timestamp(300).target("d")
		.createTransition();
		
		dstAutomata.addStateSeq(t1,t2);
		
		return dstAutomata;
		
	}
	
	
	
}
