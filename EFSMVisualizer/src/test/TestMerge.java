package test;

import static org.junit.Assert.assertEquals;
import model.Automata;
import model.EFSMController;
import model.State;
import model.Transition;

import org.junit.Test;
public class TestMerge {

	@Test
	public void testMergeWithGKTail() {

		Automata dstAutomata = getDstAutomata();
		Automata srcAutomata = getSrcAutomata();

		Automata mergedAutomata;

		try {
			mergedAutomata = EFSMController.merge(srcAutomata, dstAutomata, 2);
			assertEquals(true, mergedAutomata.equals(getMergedAutomata()));
			
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}

	private Automata getMergedAutomata() {

		Automata mergedAutomata = new Automata();

		Transition t2 = 
				new Transition.TransitionBuilder(State.newInstance(2), State.newInstance(3))
		.x(30).y(40).event("onTouch").timestamp(100).target("더보기")
		.createTransition();

		Transition t3 = 
				new Transition.TransitionBuilder(State.newInstance(3), State.newInstance(4))
		.x(30).y(40).event("onTouch").timestamp(300).target("이벤트")
		.createTransition();

		mergedAutomata.addStateSeq(t2,t3);	
		return mergedAutomata;
	}

	private Automata getSrcAutomata() {

		Automata srcAutomata = new Automata();

		Transition t1 = 
				new Transition.TransitionBuilder(State.newInstance(1), State.newInstance(2))
		.x(30).y(40).event("onTouch").timestamp(100).target("더보기")
		.createTransition();

		Transition t2 = 
				new Transition.TransitionBuilder(State.newInstance(2), State.newInstance(3))
		.x(30).y(40).event("onTouch").timestamp(100).target("더보기")
		.createTransition();

		Transition t3 = 
				new Transition.TransitionBuilder(State.newInstance(3), State.newInstance(4))
		.x(30).y(40).event("onTouch").timestamp(300).target("이벤트")
		.createTransition();
		
		srcAutomata.addStateSeq(t1,t2,t3);

		return srcAutomata;
	}

	private Automata getDstAutomata() {

		Automata dstAutomata = new Automata();

		Transition t1 = 
				new Transition.TransitionBuilder(State.newInstance(1), State.newInstance(2))
		.x(30).y(40).event("onSlide").timestamp(100).target("MainFr")
		.createTransition();

		Transition t2 = 
				new Transition.TransitionBuilder(State.newInstance(2), State.newInstance(3))
		.x(30).y(40).event("onTouch").timestamp(1453).target("더보기")
		.createTransition();

		Transition t3 =
				new Transition.TransitionBuilder(State.newInstance(3), State.newInstance(4))
		.x(30).y(40).event("onTouch").timestamp(1971).target("이벤트")
		.createTransition();

		dstAutomata.addStateSeq(t1,t2,t3);

		return dstAutomata;

	}



}
