package model;

/**
 * @author se
 * EFSM 다루는 클래스
 * K 는 GK-tail의 상수값
 */
public class EFSMController {

	private int K = 2;
	
	/**
	 * EFSM 을 GK-tail알고리즘으로 병합시킨다.
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
