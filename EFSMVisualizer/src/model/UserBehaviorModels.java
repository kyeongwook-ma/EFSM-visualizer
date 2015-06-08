package model;

import java.util.ArrayList;
import java.util.List;

public class UserBehaviorModels {

	private static UserBehaviorModels instance = new UserBehaviorModels();
	private List<User> users;
	
	private UserBehaviorModels() {
		users = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void addUsersBehavior(int id, Transition t) {
		for(User user : users) {
			if(user.getId() == id) {
				user.addBehavior(t);
			}
		}
	}
	
	public List<User> getAllUsers() {
		
		
		
		
		
		return users;
	}
	
	private String generateXMLString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(XMLGenerator xmlGen : users) {
			sb.append(xmlGen.toXML());
		}
		
		return sb.toString();
	}
	
	public static UserBehaviorModels getInstance() {
		if(instance == null)
			return new UserBehaviorModels();
		else
			return instance;
	}
	
}
