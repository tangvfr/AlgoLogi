package fr.tangv.algologi.fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ListListener implements MouseListener, ActionListener, KeyListener {

	private JList<String> list;
	private PanelLogi panel;
	private JPopupMenu menu;
	
	public ListListener(JList<String> list, PanelLogi panel) {
		this.list = list;
		this.panel = panel;
		menu = new JPopupMenu();
		//-----------------------------
		JMenuItem createMethode = new JMenuItem("Create Methode");
		createMethode.addActionListener(this);
		menu.add(createMethode);
		//-----------------------------
		JMenuItem deleteMethode = new JMenuItem("Delete Methode");
		deleteMethode.addActionListener(this);
		deleteMethode.setToolTipText("Key: Suppr");
		menu.add(deleteMethode);
		//-----------------------------
		JMenuItem renameMethode = new JMenuItem("Rename Methode");
		renameMethode.addActionListener(this);
		renameMethode.setToolTipText("Key: F2");
		menu.add(renameMethode);
		//-----------------------------
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1 && e.getClickCount() == 2) {
			panel.afficheMethode();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3) {
			menu.show(list, list.getMousePosition().x, list.getMousePosition().y);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equalsIgnoreCase("Create Methode")) {
			panel.createMethode();
		} else if (cmd.equalsIgnoreCase("Delete Methode")) {
			panel.deleteMethode();
		} else if (cmd.equalsIgnoreCase("Rename Methode")) {
			panel.renameMethode();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 113: // F2
				panel.renameMethode();
				break;
			case 127: // Sup
				panel.deleteMethode();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
