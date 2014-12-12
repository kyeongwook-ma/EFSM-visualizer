
public class State {

	private int stateId;
	private State preState;
	private State nextState;
	
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	
	public State next() {
		return nextState;
	}
	
		public State pre() {
		return preState;
	}
	
	public void addNext(State state) {
		this.nextState = state;
		state.addPre(this);	
	}
	
	public void removeNext() {
		State tmp;
	}
	
	public void addPre(State state) {
		this.preState = state;
	}
	
	
	
	
	
	
}
