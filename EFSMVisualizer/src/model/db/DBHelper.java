package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
	
	public ResultSet getResultSet(String sql) throws SQLException {
		Statement statement = c.createStatement();
		return statement.executeQuery(sql);
	}

	

}
