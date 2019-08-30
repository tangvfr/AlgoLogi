package fr.tangv.algologi.util;

import java.util.HashMap;
import java.util.Map;

public class Action {

	private ActionType type;
	private String text;
	private Map<String, Action> next;
	private Methode methode;
	private Action prev;
	
	public Action(String text, ActionType type, Map<String, Action> next, Action prev) {
		this.text = text;
		this.type = type;
		this.next = next;
		this.prev = prev;
	}
	
	public Action(String text, ActionType type, Action prev) {
		this(text, type, new HashMap<String, Action>(), prev);
	}
	
	public Action getPrev() {
		return prev;
	}
	
	public void setPrev(Action prev) {
		this.prev = prev;
	}
	
	public Methode getMethode() {
		return methode;
	}
	
	public void setMethode(Methode methode) {
		this.methode = methode;
		//replace all utuil this
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public ActionType getType() {
		return type;
	}
	
	public void setType(ActionType type) {
		this.type = type;
	}
	
	public Map<String, Action> getNext() {
		return next;
	}
	
	public void setNext(Map<String, Action> next) {
		this.next = next;
	}
	
	public Action clone(Action prev) {
		Action ac = new Action(text, type, null, null);
		Map<String, Action> next = new HashMap<String, Action>();
		for (String string : this.next.keySet()) {
			next.put(string, this.next.get(string).clone(ac));
		}
		ac.setNext(next);
		ac.setPrev(prev);
		return ac;
	}
	
	public String getHtml(boolean select, boolean error, float size, float cof) {
		String colorF = "#ffffff";
		String colorB = "#000000";
		String colorC = error ? (select ? "#ff00ff" : "#ff0000") : (select ? "#ffffff" : "#777777");
		switch (type) {
			case start:
				colorF = "#eeeeee";
				colorB = "#229922";
				break;
			case end:
				colorF = "#eeeeee";
				colorB = "#992222";
				break;
			case startFonction:
				colorF = "#eeeeee";
				colorB = "#229922";
				break;
			case endFonction:
				colorF = "#eeeeee";
				colorB = "#992222";
				break;
			case excuteFonction:
				colorF = "#eeeeee";
				colorB = "#992299";
				break;
			case action:
				colorF = "#eeeeee";
				colorB = "#999922";
				break;
			case condiction:
				colorF = "#eeeeee";
				colorB = "#229922";
				break;
			case gotoline:
				colorF = "#eeeeee";
				colorB = "#222299";
				break;
		}
		return "<html><div style=\""
				+ "color: "+colorF+";"
				+ "background: "+colorB+";"
				+ "padding: "+size*cof*2F+"px;"
				+ "font-size: "+size*cof*11F+"px;"
				+ "border: "+size*cof*2F+"px solid;"
				+ "border-color: "+colorC+";"
				+ "text-align: center;"
				+ "\">"
				+ text
				+ "</div></html>";
	}
}
