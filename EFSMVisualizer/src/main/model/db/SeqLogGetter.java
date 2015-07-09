package main.model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.entity.Point;
import main.model.entity.State;
import main.model.entity.Transition;
import main.model.entity.Transition.TransitionBuilder;

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
				
				String subSql = "SELECT * FROM " 
							+ SeqDBscheme.TABLE_NAME + " seq " 
							+ " JOIN " + COORDScheme.TABLE_NAME + " coord "
							+ " ON " + "seq." + SeqDBscheme.COLUMN_SEQ_ID 
							+ "coord." + COORDScheme.COLUMN_SEQ_ID + ";";
				
				ResultSet joinedRS = DBHelper.getInstance().getResultSet(subSql);
				
				List<Point> points = getPoints(joinedRS);
			
				Transition t = new TransitionBuilder(
								State.newInstance(seqId),State.newInstance(seqId+1))
								.point(points)
								.event(touchEvent)
								.target(touchClass)
								.timestamp(timestamp)
								.createTransition();
				
				seqs.add(t);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seqs;
	}

	private List<Point> getPoints(ResultSet joinedRS) throws SQLException {
		ArrayList<Point> points = new ArrayList<Point>();
		
		while(joinedRS.next()) {
			int x = joinedRS.getInt(COORDScheme.COLUMN_X);
			int y = joinedRS.getInt(COORDScheme.COLUMN_Y);
			points.add(new Point(x, y));
		}
		
		return points;
	}
	
}
