package main.model.analyzer;

import java.util.List;

import main.model.entity.EFSM;
import main.model.entity.Transition;

public class BadSymptomClassifier {
	
	public GUIBadSymptom analyze(EFSM mergedModel, EFSM designedModel, String target) {
		
		assert mergedModel.checkCompletness() != false;
		
		List<Transition> transitions = mergedModel.getAllTransition();
		
		if(isWrongPosition(transitions, target))
			return GUIBadSymptom.WRONG_POS_GUI;
		else if(isUndefinedGesture(transitions, target))
			return GUIBadSymptom.UNDEFINED_GESTURE;
		
		
		return null;
	}
	
	private boolean isWrongPosition(List<Transition> transitions, String target) {
		
		
		return true;
	}
	
	private boolean isUndefinedGesture(List<Transition> transitions, String target) {
		return true;
	}
	
	
}
