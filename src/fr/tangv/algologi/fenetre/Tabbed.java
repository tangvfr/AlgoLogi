package fr.tangv.algologi.fenetre;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import fr.tangv.algologi.util.Action;
import fr.tangv.algologi.util.Methode;

public class Tabbed extends JScrollPane {

	private static final long serialVersionUID = 6299595377022310547L;
	private Methode methode;
	private JViewport jView;
	private PanelLogi panel;
	
	public PanelLogi getPanel() {
		return panel;
	}
	
	public Tabbed(Methode methode, PanelLogi panel) {
		super();
		this.panel = panel;
		this.methode = methode;
		//----------------------------
		jView = new JViewport();
		this.setViewport(jView);
		this.setAutoscrolls(true);
	}
	
	public void updateTabbed() {
		JPanel fond = new JPanel();
		BoxLayout layout = new BoxLayout(fond, 0);
		fond.setLayout(layout);
		for(Action ac : methode.getActions()) {
			
		}
		jView.setView(fond);
	}
	
	
}
