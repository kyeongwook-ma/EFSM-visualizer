package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

	private Connection c;

	public DBHelper(String dbName) {
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

	public <T> List<T> getAllStmt(String statement, IAllRowGetter getter) {
		
		assert c != null;
		ArrayList<T> rows = new ArrayList<T>();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
						
			while(rs.next()) {
				rows.add((T) getter.getObject(rs));
			}
			
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}



}
