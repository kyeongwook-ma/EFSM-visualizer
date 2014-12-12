package model;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author se
 * State 간의 transition을 나타내는 클래스
 * 출발점(src)와 도착점(dst)를 가지고 있다.
 */
public class Transition {
	
	private State src, dst;
	private double timestamp;
	private double x, y;
	private String event;
	private HashSet<String> targets;
		
	public State getSrc() {
		return src;
	}

	public State getDst() {
		return dst;
	}

	public double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	public void addTarget(HashSet<String> targets) {
		this.targets.addAll(targets);
	}
	
	public Transition(State src, State dst) {
		this.src = src;
		this.dst = dst;
		this.targets = new HashSet<String>();
	}
	
	
	
	/**
	 * @author se
	 * Trnasition의 builder 클래스
	 * 
	 * ex)
	 * new Transition.TransBuilder(src, dst)
	 * .x(30).y(40)
	 * // 기타 attribute 할당
	 * .createTransition();
	 * 
	 */
	public static class TransitionBuilder {
		
		private State src, dst;
		private double timestamp;
		private double x, y;
		private String event;
		private HashSet<String> targets;
		
		public TransitionBuilder(State src, State dst) {
			this.src = src;
			this.dst = dst;
			targets = new HashSet<String>();
		}
		
		public TransitionBuilder x(double x) {
			this.x = x;
			return this;
		}
		
		public TransitionBuilder y(double y) {
			this.y = y;
			return this;
		}
		
		public TransitionBuilder event(String event) {
			this.event = event;
			return this;
		}
		
		public TransitionBuilder timestamp(double timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		public TransitionBuilder target(String target) {
			this.targets.add(target);
			return this;
		}
		
		/**
		 * @return 이 객체에 담겨진 정보가 담긴 transition instance
		 */
		public Transition createTransition() {
			Transition t = new Transition(this.src, this.dst);
			t.setEvent(this.event);
			t.setX(this.x);
			t.setY(this.y);
			t.addTarget(targets);
			t.setTimestamp(this.timestamp);
			return t;
		}
	}
		
	public Iterator<String> getTargets() {
		return targets.iterator();
	}
	
	public void expend(Transition transition) {
		
		double transitionX = transition.getX();
		double transitionY = transition.getY();
		
		/* 더 넓은 범위의 값을 할당함 */
		x = x - transitionX > 0 ? transitionX : x; 
		y = y - transitionX > 0 ? transitionY : y; 
		
		/* target 추가 */
		Iterator<String> transitionTarget = transition.getTargets();
		while(transitionTarget.hasNext()) {
			String target = transitionTarget.next();
			this.targets.add(target);
		}
	
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("x : " + x + "\n");
		sb.append("y : " + y + "\n");
		sb.append("event : " + event + "\n");
		sb.append("target : " + targets.toString() + "\n");
		sb.append("timestamp : " + String.valueOf(timestamp) + "\n");
	
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * Transition의 event가 같은 경우 같은 transition으로 취급 
	 */
	@Override
	public boolean equals(Object obj) {
		Transition t = (Transition) obj;
		return this.event.equals(t.event);
	}
}
