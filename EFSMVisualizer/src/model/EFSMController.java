package model;

/**
 * @author se
 * EFSM �ٷ�� Ŭ����
 * K �� GK-tail�� �����
 */
public class EFSMController {

	private int K = 2;
	
	/**
	 * EFSM �� GK-tail�˰������� ���ս�Ų��.
	 * @param src 
	 * @param dst
	 */
	public void merge(Automata src, Automata dst) {
		
		int range = src.size() > dst.size() ? src.size() : dst.size();
		
		for(int i = 0; i < range;  ++i) {
			Transition srcT1 = src.get(i);
			Transition srcT2 = src.get(i+1);
			
			Transition dstT1 = dst.get(i);
			Transition dstT2 = dst.get(i+1);
			
		}
		
	}
	
}
