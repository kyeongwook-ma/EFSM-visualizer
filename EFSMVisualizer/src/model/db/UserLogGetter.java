package model.db;

import java.sql.ResultSet;
import java.util.List;

import model.Transition;

public class UserLogGetter implements IAllRowGetter{

	@Override
	public <T> List<T> getObject(ResultSet rs) {
		
		
		/*
		Transition transition = new Transition.TransitionBuilder(src, dst)
		.createTransition();
		*/
		
		return null;
	
	}

}
