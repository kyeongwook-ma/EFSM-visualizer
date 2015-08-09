package main.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import main.model.db.BMGetter;
import main.model.db.TransitionGetter;
import main.model.db.UserGetter;
import main.model.entity.EFSM;
import main.model.entity.Transition;
import main.model.entity.User;

public class ModelManager {

	private static ModelManager instance = new ModelManager();
	private List<User> users;

	private ModelManager() {
		new UserGetter();
		users = UserGetter.getUserFromDB();
		int[][] bms = BMGetter.getUserBMFromDB();

		
		HashSet<Transition> transitonSet = new HashSet<Transition>();
		int tmpUsrId = bms[0][0];
				
		for(int i = 0; i < bms.length; ++i) {
			int[] bm = bms[i];
			int seqId = bm[1];			
			int usrId = bm[0];

			if(tmpUsrId == usrId) {
				transitonSet.addAll(getTransition(seqId));
			} else {
				EFSM userAFSM = generateUserEFSM(new ArrayList<Transition>(transitonSet));
				addUserBM(bms[i-1][0], userAFSM);
				tmpUsrId = usrId;
				transitonSet.clear();
				continue;
			}
		}
		
		User lastUser = users.get(users.size()-1);
		EFSM lastUserAFSM = generateUserEFSM(new ArrayList<Transition>(transitonSet));
		addUserBM(bms[bms.length-1][0], lastUserAFSM);
		
		for(User u : users) {
			System.out.println(u.toString());
		}
	}

	private EFSM generateUserEFSM(List<Transition> transitions) {

		EFSM afsm = new EFSM();

		afsm.addStateSeq(transitions.toArray(new Transition[transitions.size()]));
		

		return afsm;
	}

	private List<Transition> getTransition(int seqId) {

		ArrayList<Transition> transitions = new ArrayList<Transition>();

		for(Transition t : TransitionGetter.getTransitionFromDB()) {
			if(t.getSrc().getStateId() == seqId || t.getDst().getStateId() == seqId)
				transitions.add(t);
		}
		return transitions;
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void addUserBM(int usrId, EFSM afsm) {
		for(User user : users) {
			if(user.getId() == usrId)
				user.setBehaviorModel(afsm);
		}
	}

	public List<User> getAllUsers() {	
		return users;
	}


	public static ModelManager getInstance() {
		if(instance == null)
			return new ModelManager();
		else
			return instance;
	}

}
