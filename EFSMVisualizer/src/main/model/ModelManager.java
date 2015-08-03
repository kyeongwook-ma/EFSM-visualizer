package main.model;

import java.util.ArrayList;
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
		
		for(int i = 0; i < bms.length; ++i) {
			int[] bm = bms[i];
			int usrId = bm[0];
			int seqId = bm[1];
			
			EFSM userAFSM = generateUserEFSM(seqId);
			addUserBM(usrId, userAFSM);
		}
	}
	
	private EFSM generateUserEFSM(int seqId) {
		
		EFSM afsm = new EFSM();
		
		for(Transition t : getTransition(seqId)) {
			afsm.addStateSeq(t);
		}
		
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
