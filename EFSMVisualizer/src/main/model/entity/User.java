package main.model.entity;

import java.util.List;

public class User {
	private int id;
	private EFSM behaviorModel;
	private int timestamp;
	
	public User(int id, int timestamp) {
		this.id = id;
		this.timestamp = timestamp;
		this.behaviorModel = new EFSM();
	}
	
	public void addBehavior(Transition t) {
		behaviorModel.addStateSeq(t);
	}
	
	public void addBehavior(List<Transition> transitions) {
		for(Transition t : transitions) {
			addBehavior(t);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EFSM getBehaviorModel() {
		return behaviorModel;
	}
	public void setBehaviorModel(EFSM behaviorModel) {
		this.behaviorModel = behaviorModel;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {

		String userId = String.valueOf(this.id);
		
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("User ID : " + userId + "\n");
		
		for(Transition t : behaviorModel.getAllTransition()) {
			sb.append(t.toString());
		}
		sb.append("\n");
				
		return sb.toString();
	}

}
