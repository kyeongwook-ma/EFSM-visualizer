package main.util;

public enum TouchEventType {
	 CLICK("click"), 
	 SCROLL_DOWN("scroll_down"), 
	 SCROLL_UP("scroll_up"), 
	 UNKNOWN("unknown");
	 
	 private String eventType;
	 
	 private TouchEventType(String eventType) {
		 this.setEventType(eventType);
	 }

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	 
}
