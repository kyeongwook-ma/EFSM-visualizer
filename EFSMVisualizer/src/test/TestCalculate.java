package test;

import model.WholeBMGenerator;
import model.db.DBHelper;
import model.db.UserLogGetter;

import org.junit.Test;

public class TestCalculate {
	
	@Test
	public void testAddEqualState() {
		
		DBHelper.getInstance().constructModel(new UserLogGetter());
		
		WholeBMGenerator.calculateRatio();
	}
}
