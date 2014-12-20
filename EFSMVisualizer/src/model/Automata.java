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

	public boolean checkCompletness() {
		/* 0번째 dst State */
		State dstState = seqs.get(0).getDst();
		
		for(int i = 0; i < seqs.size(); ++i) {
			Transition t = seqs.get(i);
			/* dst State와 다음 src State 가 다른 경우 불안정 */
			if(!dstState.equals(t.getSrc())) {
				return false;
			}
			dstState = seqs.get(i).getDst();
		}

		return false;
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

	public Transition startTransition() {
		return seqs.getFirst();
	}

	public Transition endTransition() {
		return seqs.getLast();
	}



}
