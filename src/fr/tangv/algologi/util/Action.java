package fr.tangv.algologi.util;

import java.awt.Color;
import java.awt.Font;
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
		BufferedImage img = new BufferedImage(200, 120, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		Font font = new Font("Courier", Font.PLAIN, 11);
		g.setFont(font);
		//var
		String textWrite;
		int widthDecal;
		int heightDecal;
		//var
		switch (type) {
			case action:
				g.setColor(Color.BLACK);
				g.fillRect(99, 0, 2, 120);
				g.setColor(Color.yellow);
				g.fillRect(20, 20, 160, 80);
				g.setColor(Color.BLACK);
				g.drawRect(20, 20, 160, 80);
				g.fillPolygon(new int[]{90,100,110}, new int[]{10,20,10}, 3);
				textWrite = text;
				widthDecal = g.getFontMetrics().stringWidth(textWrite)/2;
				heightDecal = g.getFontMetrics().getHeight()/2;
				g.drawString(textWrite, 100-widthDecal, 60);
				break;
			case condiction:
				break;
			case end:
				g.setColor(Color.BLACK);
				g.fillRect(99, 0, 2, 60);
				g.setColor(Color.red);
				g.fillOval(20, 20, 160, 80);
				g.setColor(Color.BLACK);
				g.drawOval(20, 20, 160, 80);
				g.fillPolygon(new int[]{90,100,110}, new int[]{10,20,10}, 3);
				textWrite = "End";
				widthDecal = g.getFontMetrics().stringWidth(textWrite)/2;
				g.drawString(textWrite, 100-widthDecal, 60);
				break;
			case excuteFunction:
				break;
			case point:
				g.setColor(Color.BLACK);
				g.fillRect(99, 0, 2, 120);
				g.fillOval(95, 55, 9, 9);
				break;
			case start:
				g.setColor(Color.BLACK);
				g.fillRect(99, 60, 2, 60);
				g.setColor(Color.green);
				g.fillOval(20, 20, 160, 80);
				g.setColor(Color.BLACK);
				g.drawOval(20, 20, 160, 80);
				textWrite = "Start";
				widthDecal = g.getFontMetrics().stringWidth(textWrite)/2;
				g.drawString(textWrite, 100-widthDecal, 60);
				break;
			case gotoPoint:
				
				break;
			default:
				break;
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, 4, 4);
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
