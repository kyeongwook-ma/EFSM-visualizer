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


		return GUIBadSymptom.UNKNOWN;
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

		int undefinedCount = 
				(int) diffTransition
				.parallelStream()
				.filter(t -> t.getEvent().equals(GUIBadSymptom.UNDEFINED_GESTURE))
				.count();

		return undefinedCount > 0 ? true : false;
	}

	private boolean isRepeated(EFSM mergedModel, EFSM designedModel, String targetClass) {

		final int REPEAT_COUNT = 3;

		List<Transition> diffTransition = DiffTransitionExtractor
				.extractDiff(mergedModel, designedModel);
		
		HashSet<Transition> uniqueTransitions = new HashSet<Transition>();
		
		int uniqueCount = (int) uniqueTransitions
			.parallelStream()
			.filter(t -> countTransition(diffTransition, t) > REPEAT_COUNT)
			.count();

		return uniqueCount > 0 ? true : false;		
	}

	private int countTransition(List<Transition> transitions, Transition countTransition) {

		return (int) transitions.parallelStream()
			.filter(t -> t.equals(countTransition)).count();		
	}

	private boolean isLongLatency(EFSM mergedModel, EFSM designedModel) {

		int mergedElapsedTime = mergedModel.getElapsedTime();	
		int designedElapsedTime = designedModel.getElapsedTime();

		return mergedElapsedTime > designedElapsedTime ? true : false;
	}


}
