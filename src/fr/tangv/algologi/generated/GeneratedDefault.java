package fr.tangv.algologi.generated;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.tangv.algologi.util.Action;
import fr.tangv.algologi.util.ActionType;
import fr.tangv.algologi.util.Algo;
import fr.tangv.algologi.util.AlgoGenrated;
import fr.tangv.algologi.util.Methode;

public class GeneratedDefault implements AlgoGenrated {

	@Override
	public Image generatedImage(Algo algo) {
		//color
		Color backGroundColor = Color.gray;
		//generated
		RenderAction rednerAction = new RenderAction();
		ArrayList<Image> listImage = new ArrayList<Image>();
		Map<String, Point> mapPoint = new HashMap<String, Point>();
		ArrayList<Goto> listGoto = new ArrayList<Goto>();
		int width = 0;
		int height = 0;
		for (String key : algo.getMethodes().keySet()) {
			Methode methode = algo.getMethodes().get(key);
			if (methode.isMain()) {
				Action ac = methode.getAction();
				while (ac != null) {
					Image img = rednerAction.render(ac);
					listImage.add(img);
					height += img.getHeight(null);
					width = img.getWidth(null)>width ? img.getWidth(null) : width;
					//point faire les calc coordoner
					if (ac.getType() == ActionType.point) {
						if (!mapPoint.containsKey(ac.getText()))
							mapPoint.put(ac.getText(), new Point(0,0));
					} else if (ac.getType() == ActionType.gotoPoint) {
						listGoto.add(new Goto(0, 0, ac.getText()));
					}
					//ac remplace prev
					ac = ac.getPrev();
				}
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
		for (int i = listImage.size()-1; i >= 0; i--) {
			Image imgDraw = listImage.get(i);
			g.drawImage(imgDraw, 0, y, null);
			y += imgDraw.getHeight(null);
		}
		//return
		return img;
	}
	
}
