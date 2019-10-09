package fr.tangv.algologi.generated;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class TextRect {

	private ArrayList<String> list;
	private int heightLine;
	
	public TextRect(String string, int width, int height, FontMetrics m, int heightLine, int margeChar) {
		this.heightLine = heightLine;
		list = new ArrayList<String>();
		String part = "";
		for (int i = 0; i < string.length(); i++) {
			if (m.stringWidth(part + string.charAt(i)) <= width) {
				part += string.charAt(i);
			} else {
				for (int ind = 1; ind <= margeChar && part.length() >= ind; ind++) {
					if (part.charAt(part.length()-ind) == ' ') {
						part = part.substring(0,part.length()-ind);
						i -= ind;
						break;
					}
				}
				if (string.charAt(i) != ' ') {
					part = part.substring(0, part.length()-1)+'-';
					i--;
				}
				//add part
				if (!this.add(height, m.getFont(), part)) {
					part = "";
					break;
				}
				part = string.charAt(i) == ' ' ? "" : ""+string.charAt(i);
			}
		}
		if (!part.isEmpty())
			this.add(height, m.getFont(), part);
	}
	
	private boolean add(int height, Font font, String line) {
		if (list.size()*heightLine+font.getSize()/2+2 <= height) {
			list.add(line);
			return true;
		} else {
			
			return false;
		}
	}
	
	public void render(Graphics g, int x, int y) {
		for (int i = 0; i < list.size(); i++) {
			g.drawString(list.get(i), x, y+i*heightLine+g.getFont().getSize()/2+2);
		}
	}
	
}
