package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Point;
import model.State;
import model.Transition;

public class UserLogGetter implements IAllRowGetter{

	@Override
	public String getSql() {
		return " SELECT * FROM " + BMDscheme.TABLE_NAME + ";";
	}

	@Override
	public Transition getTransition(ResultSet rs) {
		// TODO
		return null;
	}
}
