package main.model.entity;

public class State {

	private int stateId;
	
	public static State newInstance(int stateId) {
		return new State(stateId);
	}
	
	private State(int id) {
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
	
	@Override
	public String toString() {
		return String.valueOf(stateId);
	}



}
