package fr.tangv.algologi.fenetre;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import fr.tangv.algologi.util.Methode;

public class Tabbed extends JScrollPane {

	private static final long serialVersionUID = 6299595377022310547L;
	private Methode methode;
	private JViewport jView;
	private PanelLogi panel;
	private JTextArea text;
	
	public PanelLogi getPanel() {
		return panel;
	}
	
	public Tabbed(Methode methode, PanelLogi panel) {
		super();
		this.panel = panel;
		this.methode = methode;
		//----------------------------
		text = new JTextArea();
		text.setEditable(true);
		text.setAutoscrolls(true);
		text.setTabSize(2);
		//---------------------------
		jView = new JViewport();
		jView.setView(text);
		//---------------------------
		if (panel.getListMethodeText().containsKey(methode.getName())) {
			setText(panel.getListMethodeText().get(methode.getName()));
		} else {
			panel.getListMethodeText().put(methode.getName(), "");
		}
		this.setViewport(jView);
		this.setAutoscrolls(true);
	}
	
	
	public void save() {
		this.panel.getListMethodeText().replace(this.methode.getName(), getText());
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getText() {
		return this.text.getText();
	}
	
}
