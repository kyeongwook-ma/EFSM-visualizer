package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Transition;

public class DBHelper {

	private Connection c;
	private static final String dbName = "BadUIDB";
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

	public <T> List<T> getAllStmt(IAllRowGetter getter) {
		
		assert c != null;
		List<T> rows = new ArrayList<T>();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(getter.getSql());
						
			while(rs.next()) {
				rows.add((T) getter.<Transition>getObject(rs));
			}
			
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (List<T>)rows;
	}



}
