package fr.tangv.algologi.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	
	public Image generatedImage() {
		//color
		Color backGroundColor = Color.gray;
		//generated
		ArrayList<Image> listImage = new ArrayList<Image>();
		int width = 0;
		int height = 0;
		for (String key : methodes.keySet()) {
			Methode methode = methodes.get(key);
			if (methode.isMain()) {
				Action ac = methode.getAction();
				while (ac != null) {
					Image img = ac.render();
					listImage.add(img);
					height += img.getHeight(null);
					width =  img.getWidth(null)>width ? img.getWidth(null) : width;
					ac = ac.getPrev();
				}
				/*JFrame t = new JFrame();
				t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				t.getContentPane().add(new JLabel(new ImageIcon(imgg)));
				t.pack();
				t.setVisible(true);*/
				break;
			}
		}
		//image
		BufferedImage img = new BufferedImage(width<1?1:width, height<1?1:height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		//cls
		g.setColor(backGroundColor);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		//draw
		int y = 0;
		for (int i = 0; i < listImage.size(); i++) {
			Image imgDraw = listImage.get(i);
			g.drawImage(imgDraw, 0, y, null);
			y += imgDraw.getHeight(null);
		}
		//return
		return img;
	}
	
}
