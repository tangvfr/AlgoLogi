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
		BufferedImage img = new BufferedImage(100, 60, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		switch (type) {
			case action:
				break;
			case condiction:
				break;
			case end:
				System.out.println("render end");
				g.setColor(Color.BLACK);
				g.fillRect(49, 0, 2, 30);
				g.setColor(Color.red);
				g.fillOval(10, 10, 80, 40);
				g.setColor(Color.BLACK);
				g.drawOval(10, 10, 80, 40);
				break;
			case excuteFunction:
				break;
			case point:
				break;
			case start:
				System.out.println("render start");
				g.setColor(Color.BLACK);
				g.fillRect(49, 30, 2, 30);
				g.setColor(Color.green);
				g.fillOval(10, 10, 80, 40);
				g.setColor(Color.BLACK);
				g.drawOval(10, 10, 80, 40);
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
