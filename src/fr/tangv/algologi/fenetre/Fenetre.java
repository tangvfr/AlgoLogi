package fr.tangv.algologi.fenetre;

import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResourceAsStream("log_algo_logi.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel = new PanelLogi(this);
		this.setContentPane(panel);
	}
	
	public void resetPanel() {
		panel = new PanelLogi(this);
		this.setContentPane(panel);
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(panel.getFen(), "You are sure quit AlgoLogi ?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
				return;
		}
		super.processWindowEvent(e);
	}
	
	public void saveInFile(File file) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
		for (String name : panel.getListMethodeText().keySet()) {
			boolean main = algo.getMethodes().get(name).isMain();
			out.writeBoolean(main);
			out.writeUTF(name);
			out.writeUTF(panel.getListMethodeText().get(name));
			out.flush();
		}
		out.close();
	}
	
	public Map<?,?>[] readInFile(File file) throws IOException {
		Map<String, String> list = new HashMap<String, String>();
		Map<String, Boolean> listMain = new HashMap<String, Boolean>();
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		while (in.available() > 0) {
			boolean main = in.readBoolean();
			String name = in.readUTF();
			String text = in.readUTF();
			list.put(name, text);
			listMain.put(name, main);
		}
		in.close();
		return new Map<?,?>[]{list, listMain};
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
