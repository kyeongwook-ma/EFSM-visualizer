package model;

/**
 * @author se
 * EFSM �ٷ�� Ŭ����
 * K �� GK-tail�� �����
 */
public class EFSMController {

	/**
	 * EFSM �� GK-tail�˰������� ���ս�Ų��.
	 * @param src 
	 * @param dst
	 */
	public static void merge(Automata src, Automata dst, int k) {

		int range = minSize(src, dst);

		if(range < 0) return;

		for(int i = 0; i < range;  ++i) {
			System.out.println("");
			
			for(int j = i; j < k; ++j) {
				System.out.println(j);
				Transition srcTran = src.get(j);
				Transition dstTran = dst.get(j);
				
				System.out.print(srcTran.toString() + dstTran.toString());
			}
		}

	}

	private static int minSize(Automata src, Automata dst) {
		return src.size() > dst.size() ? dst.size() : src.size();
	}

}
