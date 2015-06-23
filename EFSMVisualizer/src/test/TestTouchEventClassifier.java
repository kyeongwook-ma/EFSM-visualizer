package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import main.util.TouchEventClassfier;
import main.util.TouchEventType;

import org.junit.Test;

public class TestTouchEventClassifier {
	
	double clickedX[] = new double[] { 1, 3, 5, 6 };
	double clickedy[] = new double[] { 1, 3, 5, 6 };

	
	double touchedX[] = new double[] { 956, 820, 734, 630, 542, 531, 432 };
	double touchedY[] = new double[] { 956, 820, 734, 630, 542, 531, 432 };

	
	@Test
	public void testClicked() {
		TouchEventType touchEvent = TouchEventClassfier.getTouchEventType(clickedX, clickedy);

		assertEquals(TouchEventType.CLICK, touchEvent);
	}
	
	private static double[] reverse(double[] array){
		double[] reversedArray = new double[array.length];
	    for(int i = 0; i < array.length; i++){
	        reversedArray[i] = array[array.length - i - 1];
	    }
	    return reversedArray;
	} 
	
	@Test
	public void testDownside() {
		
		double reveresdTouchX[] = reverse(touchedX);
		double reversedTouchY[] = reverse(touchedY);
		
		TouchEventType touchEvent = TouchEventClassfier.getTouchEventType(reveresdTouchX, reversedTouchY);
	
		assertEquals(TouchEventType.SCROLL_DOWN, touchEvent);
	}
	
	@Test
	public void testUpside() {
		TouchEventType touchEvent = TouchEventClassfier.getTouchEventType(touchedX, touchedY);

		assertEquals(TouchEventType.SCROLL_UP, touchEvent);
	}
	
	@Test
	public void testUnknown() {
		
		double randomY[] = new double[] { 124, 96, 45, 37 , 19 , 145 };
		
		TouchEventType touchEvent = TouchEventClassfier.getTouchEventType(touchedX, randomY);

		assertEquals(TouchEventType.UNKNOWN, touchEvent);
	}
}
