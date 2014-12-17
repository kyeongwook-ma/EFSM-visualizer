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
	
		/* start state�� ��� �׳� �߰� */
		if(seqs.size() == 0) {
			seqs.add(transition);
			return;
		}
		
		/* ������ transition �� ���Ͽ� ������ Ȯ���Ŵ */
		Transition lastTransition = seqs.getLast();
			
		if(lastTransition.equals(transition)) {
			lastTransition.expend(transition);			
		} else {
			seqs.add(transition);
		}
	}
	
	public Transition endTransition() {
		return seqs.getLast();
	}
	
	
	
}
