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

		for(int i = 0; i < COORDlength / 2; ++i) {
			if(ys[i+1] - ys[i] < 0)
				return true;
		}

		return false;
	}

	private static boolean isUpside(double[] ys) {

		int COORDlength = ys.length;

		for(int i = 0; i < COORDlength / 2; ++i) {
			if(ys[i+1] - ys[i] > 0)
				return true;
		}

		return false;
	}

	private static boolean isJustClicked(double[] xs, double[] ys) {
		return xs.length < 5 && ys.length < 5;
	}


}
