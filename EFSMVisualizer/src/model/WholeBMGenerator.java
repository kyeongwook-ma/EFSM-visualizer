package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WholeBMGenerator {


	public static void calculateRatio() {

		HashMap<Transition, Double> transitionRatio
		= new HashMap<Transition, Double>();
		
		List<Transition> allTransitions = getAllTransition();
		int transitionSize = allTransitions.size();
		
		for(Transition t : allTransitions) {

			if(transitionRatio.containsKey(t)) {	
				double preRatio = transitionRatio.get(t);
				double currRatio = getCurrRatio(preRatio, transitionSize);
				transitionRatio.put(t, currRatio);
				
			} else {
				transitionRatio.put(t, (double)(1 / transitionSize));	
			}
			
		}
		System.out.print(transitionRatio.toString());

	}
	
	private static double getCurrRatio(double preRatio, int transitionSize) {
		return (1 + preRatio * transitionSize / 100) 
				/ transitionSize * 100;
	}

	private static List<Transition> getAllTransition() {

		ArrayList<Transition> transitions = new ArrayList<Transition>();

		List<User> allUsers = UserBehaviorModels.getInstance().getAllUsers();

		for(User user : allUsers) {
			EFSM efsm = user.getBehaviorModel();
			transitions.addAll(efsm.getAllTransition());
		}

		return transitions;
	}

}
