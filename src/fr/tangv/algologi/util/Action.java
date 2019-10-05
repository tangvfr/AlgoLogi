package fr.tangv.algologi.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
	
	public BufferedImage render() {
		BufferedImage img = new BufferedImage(50, 30, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		switch (type) {
			case action:
				break;
			case condiction:
				break;
			case end:
				
				break;
			case excuteFunction:
				break;
			case point:
				break;
			case start:
				g.setColor(Color.BLACK);
				g.fillRect(25, 15, 2, 15);
				g.setColor(Color.green);
				g.fillOval(5, 5, 40, 20);
				g.setColor(Color.BLACK);
				g.drawOval(5, 5, 40, 20);
				break;
			case gotoPoint:
				
				break;
			default:
				break;
		}
		return img;
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
