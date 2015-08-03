package main.model.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BMGetter {

	private static int countRow(ResultSet rs) {
		int rsCount = 0;

		try {
			while(rs.next()) {
			    rsCount = rsCount + 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rsCount;
	}
	
	public static int[][] getUserBMFromDB() {

		String sql = "SELECT * FROM BMTable;";
		try {
			ResultSet BMCount = DBHelper.getInstance().getResultSet(sql);
			int rowSize = countRow(BMCount);

			ResultSet BM = DBHelper.getInstance().getResultSet(sql);
			int[][] bms = new int[rowSize][];
			int i = 0;

			while(BM.next()) {
				int[] bm = new int[2];
				int userId = BM.getInt(BMDscheme.COLUMN_USR_ID);
				int seqId = BM.getInt(BMDscheme.COLUMN_SEQ_ID);
				bm[0] = userId; bm[1] = seqId;
				bms[i++] = bm;
			}

			return bms;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
