package fr.tangv.algologi.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Algo {

	private Map<String, Methode> methodes;
	
	public Algo() {
		methodes = new HashMap<String, Methode>();
	}
	
	public Map<String, Methode> getMethodes() {
		return methodes;
	}
	
	public BufferedImage renderAction(Action action) {
		Action ac = action;
		while (ac.getPrev() != null) {
			ac = ac.getPrev();
		}
		return action.render();
	}
	
	public Image generatedImage() {
		
		//color
		Color backGroundColor = Color.gray;
		//image
		BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		//cls
		g.setColor(backGroundColor);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		//generated
		
		for (String key : methodes.keySet()) {
			Methode methode = methodes.get(key);
			if (methode.isMain()) {
				g.drawImage(renderAction(methode.getAction()), 0, 0, null);
				break;
			}
		}
		
		//return
		return img;
	}
	
}
