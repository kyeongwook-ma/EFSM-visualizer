package main.model;

import java.util.List;

import main.model.entity.EFSM;
import main.model.entity.Transition;
import main.model.entity.User;

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
	private static EFSM gkTail(EFSM src, EFSM dst, int k) throws Exception {

		if(src == null || dst == null) throw new Exception();

		EFSM mergedEFSM = new EFSM();

		int range = minSize(src, dst) + 1;

		if(range < 0 || k > range) throw new IndexOutOfBoundsException();

		for(int i = 0; i < range - k; ++i) {			
			for(int j = i; j < i + k;  ++j) {
				Transition srcTran = src.get(j);
				Transition dstTran = dst.get(j);

				if(srcTran.equals(dstTran)) {
					
					srcTran.setDst(dstTran.getSrc());
					dstTran.setSrc(srcTran.getSrc());
					
					mergedEFSM.addStateSeq(srcTran);
				}
			}
		}
		if(mergedEFSM.size() == 0) 
			return src;
		return mergedEFSM;
	}
	

	public static EFSM getMergedModel(int k) {
		List<User> users = UserBehaviorModels.getInstance().getAllUsers();

		EFSM firstEFSM = users.get(0).getBehaviorModel();

		for(int i = 1; i < users.size(); ++i) {
			try {
				firstEFSM = gkTail(firstEFSM, users.get(i).getBehaviorModel(), k);
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
