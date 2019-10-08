package fr.tangv.algologi.generated;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class TextRect {

	private ArrayList<String> list;
	private int heightLine;
	
	public TextRect(String string, int width, int height, FontMetrics m, int heightLine) {
		this.heightLine = heightLine;
		list = new ArrayList<String>();
		String part = "";
		for (int i = 0; i < string.length(); i++) {
			if (m.stringWidth(part + string.charAt(i)) <= width) {
				part += string.charAt(i);
			} else {
				list.add(part);
				part = ""+string.charAt(i);
			}
		}
		if (!part.isEmpty())
			list.add(part);
	}
	
	public void render(Graphics g, int x, int y) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+": "+list.get(i));
			g.drawString(list.get(i), x, y+i*heightLine+(i == 0 ? g.getFont().getSize() : 0));
		}
	}
	
}
