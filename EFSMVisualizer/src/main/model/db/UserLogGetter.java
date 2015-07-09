package main.model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.entity.User;


public class UserLogGetter{
	

	public List<User> getUserFromDB() {
		String sql = "";
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			ResultSet rs = DBHelper.getInstance().getResultSet(sql);
			
			while(rs.next()) {
				
				String userName = rs.getString(UserScheme.COLUMN_USR_ID);
				int time = rs.getInt(UserScheme.COLUMN_TIME);
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	
}
