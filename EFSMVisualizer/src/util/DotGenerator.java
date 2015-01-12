package util;

import model.State;
import model.Transition;

public class DotGenerator {

	public static String transition2Dot(Transition t) {
		
		StringBuilder sb = new StringBuilder();
	
		sb.append(representTransition(t));
		sb.append(representParam(t));		
		
		return "";
	}
	

	
	private static String representParam(Transition t) {
		
		StringBuilder sb = new StringBuilder();
		
		t.getEvent();
		t.getTargets();
		t.getTimestamp();
		t.getX();
		t.getY();
		
		return sb.toString();
	}
	
	private static String representTransition(Transition t) {
		return t.getSrc().toString() + "->" + t.getDst().toString();
	}
	
}
