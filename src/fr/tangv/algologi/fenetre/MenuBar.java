package fr.tangv.algologi.fenetre;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		//--------------------------------------
		menu = new JMenu("File");	
		this.add(menu);
		//--------------------------------------
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileFiltreAL());
				int result = fileChooser.showOpenDialog(panel.getFen());
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					if (!path.endsWith(".algologi")) path += ".algologi";
					File file = new File(path);
					if (!file.exists() || JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(panel.getFen(), "Remplace File ?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
						try {
							if (!file.exists()) file.createNewFile();
							panel.save();
							panel.getFen().saveInFile(file);
							JOptionPane.showMessageDialog(panel.getFen(), "Saving finish !", "Save", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(panel.getFen(), e1.getMessage(), "Save", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		menu.add(save);
		//--------------------------------------
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileFiltreAL());
				int result = fileChooser.showOpenDialog(panel.getFen());
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					if (path.endsWith(".algologi")) {
						File file = new File(path);
						if (file.exists()) {
							try {
								Fenetre fen = panel.getFen();
								Map<?,?>[] donee = fen.readInFile(file);
								fen
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(panel.getFen(), e1.getMessage(), "Open", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
		menu.add(open);
		//--------------------------------------
		JMenuItem generated = new JMenuItem("Generated");
		generated.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.save();
				panel.generated();
				panel.getFen().getAlgo().generatedImage();
				JOptionPane.showMessageDialog(panel.getFen(), "Generated finish !", "Generated", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu.add(generated);
		//--------------------------------------
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.getFen().processWindowEvent(new WindowEvent(panel.getFen(), WindowEvent.WINDOW_CLOSING));
			}
		});
		menu.addSeparator();
		menu.add(quit);
	}
	
	public PanelLogi getPanel() {
		return panel;
	}
	
}
