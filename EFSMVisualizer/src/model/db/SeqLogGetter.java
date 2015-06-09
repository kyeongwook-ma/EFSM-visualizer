package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Transition;

public class SeqLogGetter {
	
	public List<Transition> getUserFromDB() {
		
		ArrayList<Transition> seqs = new ArrayList<Transition>();
		String sql = "SELECT * FROM " + SeqDBscheme.TABLE_NAME + ";";
		
		try {
			ResultSet rs = DBHelper.getInstance().getResultSet(sql);
			
			while(rs.next()) {
				
				int seqId = rs.getInt(SeqDBscheme.COLUMN_SEQ_ID);
				String touchClass = rs.getString(SeqDBscheme.COLUMN_TOUCHCLASS);
				String touchEvent = rs.getString(SeqDBscheme.COLUMN_TOUCHMODE);
				int timestamp = rs.getInt(SeqDBscheme.COLUMN_TIMESTAMP);
				
				
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
