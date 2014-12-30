package model.db;

import java.sql.ResultSet;

import model.Transition;

public interface IAllRowGetter {
	Transition getTransition(ResultSet rs);
	String getSql();
}
