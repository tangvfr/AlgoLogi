package fr.tangv.algologi.fenetre;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;

import fr.tangv.algologi.util.Methode;

public class Tabbed extends JScrollPane {

	private static final long serialVersionUID = 6299595377022310547L;
	private Methode methode;
	private JViewport jView;
	private PanelLogi panel;
	private JTextPane text;
	
	public PanelLogi getPanel() {
		return panel;
	}
	
	public Tabbed(Methode methode, PanelLogi panel) {
		super();
		this.panel = panel;
		this.methode = methode;
		//----------------------------
		text = new JTextPane();
		text.setEditable(true);
		text.setAutoscrolls(true);
		
		//---------------------------
		jView = new JViewport();
		jView.setView(text);
		//---------------------------
		this.setViewport(jView);
		this.setAutoscrolls(true);
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getText() {
		return this.text.getText();
	}
	
	public void updateTabbed() {
		char[] code = text.getText().toCharArray();
		System.out.println("--------------------------------");
		System.out.println(code);
	}
	
}
