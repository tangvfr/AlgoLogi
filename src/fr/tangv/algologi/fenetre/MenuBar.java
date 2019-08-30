package fr.tangv.algologi.fenetre;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.Border;
import javax.swing.plaf.MenuBarUI;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 356983545601399371L;
	private PanelLogi panel;
	private JMenu menu;
	
	public MenuBar(PanelLogi panel) {
		this.setUI(new MenuBarUI() {});
		this.setBorder(new Border() {
			
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				g.setColor(new Color(100, 100, 100));
				g.drawLine(x, y+height-1, x+width, y+height-1);
			}
			
			@Override
			public boolean isBorderOpaque() {
				return false;
			}
			
			@Override
			public Insets getBorderInsets(Component c) {
				return new Insets(1, 1, 1, 1);
			}
		});
		this.panel = panel;
		//--------------------------------------
		menu = new JMenu("File");
		this.add(menu);
		//--------------------------------------
		
		//--------------------------------------
	}
	
	public PanelLogi getPanel() {
		return panel;
	}
	
}
