package main.util;


public class TouchEventClassfier {

	public static TouchEventType getTouchEventType(double xs[], double ys[]) {

		if(isJustClicked(xs, ys)) {
			return TouchEventType.CLICK;
		} 
		
		else {
			if(isDownside(ys))
				return TouchEventType.SCROLL_DOWN;
			else if(isUpside(ys))
				return TouchEventType.SCROLL_UP;
		}
		
		return TouchEventType.UNKNOWN;

	}
	
	private static boolean isDownside(double[] ys) {

		int COORDlength = ys.length;
		
		double tmp = 1;
		for(int i = 0; i < COORDlength / 2; ++i) {
			double diff = ys[i] - ys[i+1];
			tmp *= diff;
		}
		
		if(tmp < 0 && ys[COORDlength-1] - ys[0] > 0) 
			return true;

		return false;
	}
	
	
	private static boolean isUpside(double[] ys) {

		int COORDlength = ys.length;
		
		double tmp = 1;
		for(int i = 0; i < COORDlength / 2; ++i) {
			double diff = ys[i] - ys[i+1];
			tmp *= diff;
		}
		
		if(tmp > 0 && ys[COORDlength-1] - ys[0] < 0) 
			return true;

		return false;
	}

	private static boolean isJustClicked(double[] xs, double[] ys) {
		return xs.length < 5 && ys.length < 5;
	}


}
