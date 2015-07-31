package main.model.analyzer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import main.model.DiffTransitionExtractor;
import main.model.entity.EFSM;
import main.model.entity.Transition;

public class BadSymptomClassifier {

	public GUIBadSymptom analyze(EFSM mergedModel, EFSM designedModel, String targetClass) {

		assert mergedModel.checkCompletness() != false;

		if(isWrongPosition(mergedModel, designedModel, targetClass))
			return GUIBadSymptom.WRONG_POS_GUI;
		else if(isUndefinedGesture(mergedModel, designedModel, targetClass))
			return GUIBadSymptom.UNDEFINED_GESTURE;
		else if(isLongLatency(mergedModel, designedModel))
			return GUIBadSymptom.LONG_LATENCY;


		return null;
	}

	private boolean isWrongPosition(EFSM mergedModel, EFSM designedModel, String targetClass) {

		final int DEPTH = 4;

		List<Transition> diffTransition = DiffTransitionExtractor
				.extractDiff(mergedModel, designedModel);

		int count = 0;

		for(Transition t : diffTransition) {
			Iterator<String> targets = t.getTargets();
			++count;

			while(targets.hasNext()) {
				String target = targets.next();
				if(target.equals(targetClass)) {
					break;
				}
			}						
		}

		return count < DEPTH ? true : false;
	}

	private boolean isUndefinedGesture(EFSM mergedModel, EFSM designedModel, String targetClass) {

		List<Transition> diffTransition = DiffTransitionExtractor
				.extractDiff(mergedModel, designedModel);

		for(Transition t : diffTransition) {
			if(t.getEvent().equals(GUIBadSymptom.UNKNOWN))
				return true;
		}

		return false;
	}

	private boolean isRepeated(EFSM mergedModel, EFSM designedModel, String targetClass) {

		final int REPEAT_COUNT = 3;

		List<Transition> diffTransition = DiffTransitionExtractor
				.extractDiff(mergedModel, designedModel);

		Iterator<Transition> uniqueTransition = getUniqueTransitionIter(diffTransition);

		while(uniqueTransition.hasNext()) {
			Transition t = uniqueTransition.next();

			if(countTransition(diffTransition, t) > REPEAT_COUNT)
				return true;
		}

		return false;		
	}

	private int countTransition(List<Transition> transitions, Transition countTransition) {

		int count = 0;

		for(Transition t : transitions) {
			if(t.equals(countTransition))
				++count;
		}

		return count;
	}


	private Iterator<Transition> getUniqueTransitionIter(List<Transition> diffTransition) {
		HashSet<Transition> transitionSet = new HashSet<Transition>(diffTransition);

		return transitionSet.iterator();
	}

	private boolean isLongLatency(EFSM mergedModel, EFSM designedModel) {

		int mergedElapsedTime = mergedModel.getElapsedTime();	
		int designedElapsedTime = designedModel.getElapsedTime();

		return mergedElapsedTime > designedElapsedTime ? true : false;
	}


}
