package main.model;

import java.util.ArrayList;
import java.util.List;

import main.model.entity.EFSM;
import main.model.entity.Transition;

public class DiffTransitionExtractor {
	
	public static List<Transition> extractDiff(EFSM e1, EFSM e2) {
		
		ArrayList<Transition> diffs;
		
		if(e1.size() > e2.size()) {
			diffs = (ArrayList<Transition>) e1.getAllTransition();
			diffs.removeAll(e2.getAllTransition());
		} else {
			diffs = (ArrayList<Transition>) e2.getAllTransition();
			diffs.removeAll(e1.getAllTransition());
		}
				
		return diffs; 
	}
}
