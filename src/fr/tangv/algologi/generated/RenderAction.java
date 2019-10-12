package fr.tangv.algologi.generated;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.tangv.algologi.util.Action;

public class RenderAction {

	public BufferedImage render(Action action) {
		BufferedImage img = new BufferedImage(200, 120, BufferedImage.TYPE_4BYTE_ABGR);
		//----------------------
		Graphics g = img.getGraphics();
		Font font = new Font("Courier", Font.PLAIN, 11);
		g.setFont(font);
		//var
		TextRect textRect;
		//var
		switch (action.getType()) {
			case action:
				g.setColor(Color.BLACK);
				g.fillRect(99, 0, 2, 120);
				g.setColor(Color.yellow);
				g.fillRect(20, 20, 160, 80);
				g.setColor(Color.BLACK);
				g.drawRect(20, 20, 160, 80);
				g.fillPolygon(new int[]{90,100,110}, new int[]{10,20,10}, 3);
				textRect = new TextRect(action.getText(), 156, 76, g.getFontMetrics(), 13, 4);
				textRect.render(g, 22, 22);
				break;
			case condiction:
				g.setColor(Color.yellow);
				g.fillPolygon(new int[]{10, 100, 190, 100}, new int[]{60, 10, 60, 110}, 4);
				g.setColor(Color.BLACK);
				g.drawPolygon(new int[]{10, 100, 190, 100}, new int[]{60, 10, 60, 110}, 4);
				g.fillPolygon(new int[]{90,100,110}, new int[]{0,10,0}, 3);
				if (action.getNext().size() >= 1)
					g.fillRect(190, 59, 20, 2);
				if (action.getNext().size() >= 2)
					g.fillRect(99, 110, 2, 10);
				if (action.getNext().size() >= 3)
					g.fillRect(0, 59, 10, 2);
				textRect = new TextRect(action.getText(), 90, 50, g.getFontMetrics(), 13, 4);
				textRect.render(g, 55, 35);
				break;
			case end:
				g.setColor(Color.BLACK);
				g.fillRect(99, 0, 2, 60);
				g.setColor(Color.red);
				g.fillOval(20, 20, 160, 80);
				g.setColor(Color.BLACK);
				g.drawOval(20, 20, 160, 80);
				g.fillPolygon(new int[]{90,100,110}, new int[]{10,20,10}, 3);
				textRect = new TextRect("End", 156, 76, g.getFontMetrics(), 13, 4);
				textRect.render(g, 22, 22);
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
				textRect = new TextRect("Start", 156, 76, g.getFontMetrics(), 13, 4);
				textRect.render(g, 22, 22);
				break;
			case gotoPoint:
				g.setColor(Color.BLACK);
				g.fillRect(99, 0, 2, 60);
				g.fillRect(97, 58, 6, 6);
				break;
			default:
				g.setColor(Color.orange);
				g.fillOval(20, 20, 160, 80);
				g.setColor(Color.BLACK);
				g.drawOval(20, 20, 160, 80);
				textRect = new TextRect("Error !", 156, 76, g.getFontMetrics(), 13, 4);
				textRect.render(g, 22, 22);
				break;
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, 4, 4);
		return img;
	}
	
}
