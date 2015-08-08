package main.model.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @author se
 * State ���� transition�� ��Ÿ���� Ŭ����
 * �����(src)�� ������(dst)�� ������ �ִ�.
 */
public class Transition {

	private State src, dst;
	private double timestamp;
	private String event;
	private List<Point> touchPoints;
	private HashSet<String> targets;


	public void setSrc(State src) {
		this.src = src;
	}

	public void setDst(State dst) {
		this.dst = dst;
	}

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


	public List<Point> getTouchPoint() {
		return touchPoints;
	}

	public void setTouchPoint(List<Point> touchPoints) {
		this.touchPoints = touchPoints;
	}

	public void setTargets(HashSet<String> targets) {
		this.targets = targets;
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
	 * Trnasition�� builder Ŭ����
	 * 
	 * ex)
	 * new Transition.TransBuilder(src, dst)
	 * .x(30).y(40)
	 * // ��Ÿ attribute �Ҵ�
	 * .createTransition();
	 * 
	 */
	public static class TransitionBuilder {

		private State src, dst;
		private double timestamp;
		private List<Point> touchPoints;
		private String event;
		private HashSet<String> targets;

		public TransitionBuilder(State src, State dst) {

			this.src = src;
			this.dst = dst;

			targets = new HashSet<String>();
		}
		
		public TransitionBuilder point(List<Point> points) {
			this.touchPoints = points;
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
		 * @return �� ��ü�� ����� ������ ��� transition instance
		 */
		public Transition createTransition() {
			Transition t = new Transition(this.src, this.dst);
			t.setEvent(this.event);
			t.setTouchPoint(this.touchPoints);
			t.addTarget(this.targets);
			t.setTimestamp(this.timestamp);
			return t;
		}
	}

	public Iterator<String> getTargets() {
		return targets.iterator();
	}

	public void expend(Transition transition) {

		/* target �߰� */
		Iterator<String> transitionTarget = transition.getTargets();
			while(transitionTarget.hasNext()) {
				String target = transitionTarget.next();
				this.targets.add(target);
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "\n" + src.toString() + " -> " + dst.toString() + "\n");
		
		for(Point p : this.getTouchPoint()) {
			sb.append("x : " + p.getX() + " ,");
			sb.append("y : " + p.getY() + " ,");
		}
		
		sb.append("event : " + event + " ,");

		Iterator<String> transitionTarget = getTargets();
		StringBuilder targetSB = new StringBuilder();
		while(transitionTarget.hasNext()) {
			String target = transitionTarget.next();
			targetSB.append(" " + target);
		}

		sb.append("target : " + targetSB.toString() + " ,");
		sb.append("timestamp : " + String.valueOf(timestamp));

		return sb.toString();
	}

	/* (non-Javadoc)
	 * Transition�� event�� ���� ��� ���� transition���� ��� 
	 */
	@Override
	public boolean equals(Object obj) {
		Transition t = (Transition) obj;
		return event.equals(t.getEvent());
	}

	@Override
	public int hashCode() {
		return (String.valueOf(src.getStateId()) + 
				String.valueOf(dst.getStateId())).hashCode();
	}


	
}
