package model;
import java.util.LinkedList;


public class Automata {
	
	private LinkedList<Transition> seqs;
	
	public Automata() {
		seqs = new LinkedList<Transition>();
	}
	
	public int size() {
		return seqs.size();
	}
	
	public Transition get(int idx) {
		return seqs.get(idx);
	}
	
	public void addStateSeq(Transition transition) {
	
		/* start state인 경우 그냥 추가 */
		if(seqs.size() == 0) {
			seqs.add(transition);
			return;
		}
		
		/* 마지막 transition 과 비교하여 같으면 확장시킴 */
		Transition lastTransition = seqs.getLast();
			
		if(lastTransition.equals(transition)) {
			lastTransition.expend(transition);			
		}
	}
	
	public Transition endTransition() {
		return seqs.getLast();
	}
	
	
	
}
