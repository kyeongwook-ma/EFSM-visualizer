package model;

/**
 * @author se
 * EFSM �ٷ�� Ŭ����
 * K �� GK-tail�� �����
 */
public class EFSMUtil {

	/**
	 * EFSM �� GK-tail�˰������� ���ս�Ų��.
	 * @param src 
	 * @param dst
	 * @throws Exception 
	 */
	public static Automata merge(Automata src, Automata dst, int k) throws Exception {

		if(src == null || dst == null) throw new Exception();

		Automata mergedAutomata = new Automata();

		int range = minSize(src, dst) + 1;

		if(range < 0 || k > range) throw new IndexOutOfBoundsException();

		for(int i = 0; i < range - k; ++i) {			
			for(int j = i; j < i + k;  ++j) {
				Transition srcTran = src.get(j);
				Transition dstTran = dst.get(j);

				if(srcTran.equals(dstTran)) {
					mergedAutomata.addStateSeq(srcTran);
				}
			}
		}

		return mergedAutomata;
	}

	private static int minSize(Automata src, Automata dst) {
		return src.size() > dst.size() ? dst.size() : src.size();
	}

}
