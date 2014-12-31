package model;

import java.util.List;

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
	private static EFSM merge(EFSM src, EFSM dst, int k) throws Exception {

		if(src == null || dst == null) throw new Exception();

		EFSM mergedEFSM = new EFSM();

		int range = minSize(src, dst) + 1;

		if(range < 0 || k > range) throw new IndexOutOfBoundsException();

		for(int i = 0; i < range - k; ++i) {			
			for(int j = i; j < i + k;  ++j) {
				Transition srcTran = src.get(j);
				Transition dstTran = dst.get(j);

				if(srcTran.equals(dstTran)) {
					mergedEFSM.addStateSeq(srcTran);
				}
			}
		}

		return mergedEFSM;
	}
	

	public static EFSM getMergedModel(int k) {
		List<User> users = UserBehaviorModels.getInstance().getAllUsers();

		EFSM firstEFSM = users.get(0).getBehaviorModel();

		for(User user : users) {
			try {
				firstEFSM = merge(firstEFSM, user.getBehaviorModel(), k);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return firstEFSM;
	}

	private static int minSize(EFSM src, EFSM dst) {
		return src.size() > dst.size() ? dst.size() : src.size();
	}

}
