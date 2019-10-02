package fr.tangv.algologi.util;

public enum ActionType {

	start(false, true),
	end(true, false),
	startFunction(false, true),
	endFunction(true, false),
	excuteFunction(false, false),
	condiction(false, false),
	action(false, false),
	point(false, false),
	gotoPoint(false, false);
	
	private boolean isEnd;
	private boolean isStart;
	
	private ActionType(boolean isEnd, boolean isStart) {
		this.isEnd = isEnd;
		this.isStart = isStart;
	}
	
	public boolean isEnd() {
		return isEnd;
	}
	
	public boolean isStart() {
		return isStart;
	}
	
}
