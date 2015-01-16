package test;

import static org.junit.Assert.assertEquals;
import model.EFSM;
import model.EFSMUtil;
import model.State;
import model.Transition;

import org.junit.Test;
public class TestMerge {

	@Test
	private EFSM getMergedAutomata() {

		EFSM mergedAutomata = new EFSM();

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

	private EFSM getSrcAutomata() {

		EFSM srcAutomata = new EFSM();

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

	private EFSM getDstAutomata() {

		EFSM dstAutomata = new EFSM();

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
