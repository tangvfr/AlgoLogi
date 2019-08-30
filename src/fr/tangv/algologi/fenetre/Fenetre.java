package fr.tangv.algologi.fenetre;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import fr.tangv.algologi.util.Algo;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1838098528017894267L;
	private PanelLogi panel;
	private Algo algo;
	
	public Fenetre() {
		super("AlgoLogi");
		algo = new Algo("Default");
		this.setSize(720, 480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new PanelLogi(this);
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("log_algo_logi.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setContentPane(panel);
	}
	
	public PanelLogi getPanel() {
		return panel;
	}
	
	public Algo getAlgo() {
		return algo;
	}
	
	public void setAlgo(Algo algo) {
		this.algo = algo;
	}
	
}
