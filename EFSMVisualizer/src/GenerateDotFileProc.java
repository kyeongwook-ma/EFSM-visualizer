import java.io.IOException;
import java.util.List;

import model.EFSM;
import model.User;
import model.UserBehaviorModels;
import model.db.DBHelper;
import model.db.UserLogGetter;
import util.DotFileWriter;


public class GenerateDotFileProc {

	public static void main(String[] args)  {
		
		DBHelper.getInstance().constructModel(new UserLogGetter());
		
		List<User> users = UserBehaviorModels.getInstance().getAllUsers();
	
		for(User u : users) {
			int userId = u.getId();
			EFSM bm = u.getBehaviorModel();
			
			try {
				DotFileWriter.write( "User_" + String.valueOf(userId), bm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
