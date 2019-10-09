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
		String textWrite;
		int widthDecal;
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
				TextRect textRect = new TextRect(action.getText(), 156, 76, g.getFontMetrics(), 13, 4);
				textRect.render(g, 22, 22);
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
	
}
