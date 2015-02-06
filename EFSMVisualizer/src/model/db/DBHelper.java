package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.EFSM;
import model.EFSMStorage;
import model.Transition;
import model.User;
import model.UserBehaviorModels;

public class DBHelper {

	private Connection c;
	private static final String dbName = "TestDB";
	private static DBHelper instance = new DBHelper();

	private DBHelper() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			c = DriverManager.getConnection("jdbc:sqlite:"+dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBHelper getInstance() {
		if(instance == null)
			return new DBHelper();
		else
			return instance;
	}

	private void constructUserData() {
		Statement stmt = null;
		String sql = "SELECT * FROM " 
				+ SeqDBscheme.TABLE_NAME + ";";

		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				int id = rs.getInt(SeqDBscheme.COLUMN_U_ID);
				int timestamp = rs.getInt(SeqDBscheme.COLUMN_TIME);
				UserBehaviorModels.getInstance().addUser(new User(id, timestamp));
			}

		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void constructModel(IAllRowGetter getter) {

		constructUserData();
		
		assert c != null;
		Statement stmt = null;

		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(getter.getSql());
			
			while(rs.next()) {
				
				int userId = rs.getInt(BMDscheme.COLUMN_U_ID);
				UserBehaviorModels.getInstance().
					addUsersBehavior(userId, getter.getTransition(rs));
				
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



}
