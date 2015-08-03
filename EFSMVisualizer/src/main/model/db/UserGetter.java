package main.model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.entity.User;


public class UserGetter{
	
	public static List<User> getUserFromDB() {
		String sql = "SELECT * FROM " + UserScheme.TABLE_NAME + ";";
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			ResultSet rs = DBHelper.getInstance().getResultSet(sql);
			
			while(rs.next()) {
				int userId = rs.getInt(UserScheme.COLUMN_USR_ID);
				int time = rs.getInt(UserScheme.COLUMN_TIME);
				User user = new User(userId, time);
				users.add(user);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	
}
