package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.State;
import model.Transition;

public class UserLogGetter implements IAllRowGetter{


	@Override
	public String getSql() {
		return " SELECT * FROM " + BMDscheme.TABLE_NAME + ";";
	}

	
	
	@Override
	public Transition getTransition(ResultSet rs) {
		Transition transition = null;

		int currState;

		try {
			currState = rs.getInt(BMDscheme.COLUMN_ID);
			transition = 
					new Transition.TransitionBuilder(
							State.newInstance(currState), State.newInstance(currState+1))
			.x(rs.getDouble(BMDscheme.COLUMN_Y)).y(rs.getDouble(BMDscheme.COLUMN_X))
			.target(rs.getString(BMDscheme.COLUMN_CLASS))
			.timestamp(rs.getDouble(BMDscheme.COLUMN_TIMESTAMP))
			.event(rs.getString(BMDscheme.COLUMN_MODE))
			.createTransition();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transition;
	}

}
