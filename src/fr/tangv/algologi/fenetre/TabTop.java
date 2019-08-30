package fr.tangv.algologi.fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TabTop extends JPanel implements MouseListener {

	private static final long serialVersionUID = -6186127198708034057L;
	private JLabel label;
	private JLabel btn;
	private PanelLogi panel;
	private Color normal;
	private Color select;
	private String name;
	
	public TabTop(String name, PanelLogi panel) {
		super();
		//--------------------------------
		this.panel = panel;
		this.name = name;
		btn = new JLabel("X");
		label = new JLabel(name);
		//--------------------------------
		normal = btn.getForeground();
		select = new Color(240, 70, 70);
		//--------------------------------
		btn.addMouseListener(this);
		//--------------------------------
		super.setLayout(new BorderLayout(4, 4));
		//super.add(icone, BorderLayout.WEST);
		super.add(label, BorderLayout.CENTER);
		super.add(btn, BorderLayout.EAST);
	}
	
	public void setNameLabel(String name) {
		this.name = name;
		label.setText(name);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		panel.hideMethode(panel.getNameListReal(name));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		btn.setForeground(select);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		btn.setForeground(normal);
	}

	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	
}
