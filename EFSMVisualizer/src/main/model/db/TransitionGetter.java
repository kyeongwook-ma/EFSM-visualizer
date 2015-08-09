package main.model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.entity.Point;
import main.model.entity.State;
import main.model.entity.Transition;
import main.model.entity.Transition.TransitionBuilder;
import main.util.TouchEventClassfier;

public class TransitionGetter {
	
	public static List<Transition> getTransitionFromDB() {
		
		ArrayList<Transition> seqs = new ArrayList<Transition>();
		String sql = "SELECT * FROM " + TransitionDBscheme.TABLE_NAME + ";";
		
		try {
			ResultSet rs = DBHelper.getInstance().getResultSet(sql);
			
			while(rs.next()) {
				
				int seqId = rs.getInt(TransitionDBscheme.COLUMN_SEQ_ID);
				String touchClass = rs.getString(TransitionDBscheme.COLUMN_TOUCHCLASS);
				int timestamp = rs.getInt(TransitionDBscheme.COLUMN_TIMESTAMP);
				
				String subSql = "SELECT * FROM " 
							+ TransitionDBscheme.TABLE_NAME + " seq " 
							+ " JOIN " + COORDScheme.TABLE_NAME + " coord "
							+ " ON " + "seq." + TransitionDBscheme.COLUMN_SEQ_ID 
							+ " = coord." + COORDScheme.COLUMN_SEQ_ID + ";";
				
				ResultSet joinedRS = DBHelper.getInstance().getResultSet(subSql);
				
				List<Point> points = getPoints(joinedRS);
				
				String touchEvent = rs.getString(TransitionDBscheme.COLUMN_TOUCHMODE);
				
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
	
	private static String getEventType(List<Point> points) {

		int pointSize = points.size();
		
		double[] xs = new double[pointSize];
		double[] ys = new double[pointSize];
		
		for(int i = 0; i < pointSize; ++i) {
			Point p = points.get(i);
			xs[i] = p.getX();
			ys[i] = p.getY();
		}
	
		return TouchEventClassfier.getTouchEventType(xs, ys).toString();
	}
	
	private static List<Point> getPoints(ResultSet joinedRS) throws SQLException {
		ArrayList<Point> points = new ArrayList<Point>();
		
		while(joinedRS.next()) {
			int x = joinedRS.getInt(COORDScheme.COLUMN_X);
			int y = joinedRS.getInt(COORDScheme.COLUMN_Y);
			points.add(new Point(x, y));
		}
		
		return points;
	}
	
}
