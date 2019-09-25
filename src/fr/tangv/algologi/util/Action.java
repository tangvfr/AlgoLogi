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
}
