package fr.tangv.algologi.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Algo {

	private Map<String, Methode> methodes;
	
	public Algo() {
		methodes = new HashMap<String, Methode>();
	}
	
	public Map<String, Methode> getMethodes() {
		return methodes;
	}
	
	public BufferedImage renderAction(Action action) {
		if (action == null) return new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
		Action ac = action;
		while (ac.getPrev() != null) {
			ac = ac.getPrev();
		}
		return ac.render();
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
				Image imgg = renderAction(methode.getAction());
				int i = 0;
				Action ac = methode.getAction();
				while (ac != null) {
					g.drawImage(imgg, 0, 1000+(i*-30), null);ac.getPrev();
					ac = ac.getPrev();
					i++;
				}
				/*JFrame t = new JFrame();
				t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				t.getContentPane().add(new JLabel(new ImageIcon(imgg)));
				t.pack();
				t.setVisible(true);*/
				break;
			}
		}
		
		//return
		return img;
	}
	
}
