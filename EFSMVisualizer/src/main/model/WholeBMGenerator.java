package main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.model.entity.EFSM;
import main.model.entity.Transition;
import main.model.entity.User;

public class WholeBMGenerator {


	public static void calculateRatio() {

		HashMap<Transition, Double> transitionRatio
		= new HashMap<Transition, Double>();
		
		List<Transition> allTransitions = getAllTransition();
		int transitionSize = allTransitions.size();
		
		System.out.println(allTransitions.toString());
		
		for(Transition t : allTransitions) {

			if(transitionRatio.containsKey(t)) {	
				double preRatio = transitionRatio.get(t);
				double currRatio = getCurrRatio(preRatio, transitionSize);
				transitionRatio.put(t, currRatio);
				
			} else {
				transitionRatio.put(t, getRatio(transitionSize));	
			}
			
		}
		System.out.print(transitionRatio.toString());

	}
	
	private static double getRatio(int transitionSize) {
		return ((double)1 / transitionSize  * 100);
	}
	
	private static double getCurrRatio(double preRatio, int transitionSize) {
		int incrementedCount = (int) (preRatio * transitionSize / 100 ) + 1;
		return  (double)incrementedCount / (double)transitionSize * 100;
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
