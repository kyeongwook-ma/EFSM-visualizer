package model;

public class State {

	private int stateId;
	
	public State(int id) {
		this.stateId = id;
	}
	
	public int getStateId() {
		return stateId;
	}
	
	@Override
	public boolean equals(Object obj) {
		State s = (State) obj;
		return this.stateId == s.getStateId() ? true : false;
	}




}
