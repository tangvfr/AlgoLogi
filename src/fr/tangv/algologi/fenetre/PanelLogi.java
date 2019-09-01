package fr.tangv.algologi.fenetre;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import fr.tangv.algologi.util.Action;
import fr.tangv.algologi.util.ActionType;
import fr.tangv.algologi.util.Methode;

public class PanelLogi extends JPanel {
	
	private static final long serialVersionUID = -5813755440650992329L;
	private Fenetre fen;
	private MenuBar menuBar;
	private JTabbedPane tabbedPane;
	private JSplitPane split1;
	private JList<String> list;
	private DefaultListModel<String> listClass;
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public PanelLogi(Fenetre fen) {
		this.fen = fen;
		this.setLayout(new BorderLayout());
		//------------------------------
		menuBar = new MenuBar(this);
		fen.setJMenuBar(menuBar);
		//------------------------------
		split1 = new JSplitPane(1);
		this.add(split1, BorderLayout.CENTER);
		//------------------------------
		listClass = new DefaultListModel();
	    list = new JList(listClass);
	    ListListener listenerList = new ListListener(list, this);
	    list.addMouseListener(listenerList);
	    list.addKeyListener(listenerList);
		split1.add(list);
		//------------------------------
		tabbedPane = new JTabbedPane();
		createMethodeDefault();
		split1.add(tabbedPane);
		//------------------------------
		((Tabbed) tabbedPane.getSelectedComponent()).updateTabbed();
	}
	
	public String getNameList(Methode methode) {
		return getNameList(methode, methode.getName());
	}
	
	public String getNameList(Methode methode, String name) {
		if (methode.isMain()) {
			return "<html><font color=#ffc800>"+name+"</font></html>";
		} else {
			return name;
		}
	}
	
	public String getNameListReal(String name) {
		return name.replace("<html><font color=#ffc800>", "").replace("</font></html>", "");
	}
	
	public void addListMethode(Methode methode) {
		listClass.addElement(getNameList(methode));
	    list.updateUI();
	}
	
	public void loadMethode(Methode methode) {
		tabbedPane.addTab(methode.getName(), new Tabbed(methode, this));
		int index = tabbedPane.getTabCount()-1;
		tabbedPane.setTabComponentAt(index, new TabTop(getNameList(methode), this));
		tabbedPane.setSelectedIndex(index);
	}
	
	public void renameMethode(Methode methode, String name) {
		listClass.setElementAt(getNameList(methode, name), listClass.indexOf(getNameList(methode)));
		//------------------------------------
		int index = tabbedPane.indexOfTab(methode.getName());
		if (index != -1) {
			tabbedPane.setTitleAt(index, name);
			((TabTop) tabbedPane.getTabComponentAt(index)).setNameLabel(getNameList(methode, name));
		}
		//------------------------------------
		fen.getAlgo().getMethodes().remove(methode.getName());
		methode.setName(name);
		fen.getAlgo().getMethodes().put(name, methode);
	}
	
	public void deleteMethode(Methode methode) {
		listClass.remove(listClass.indexOf(getNameList(methode)));
		int index = tabbedPane.indexOfTab(methode.getName());
		if (index != -1)
			tabbedPane.remove(tabbedPane.indexOfTab(methode.getName()));
		fen.getAlgo().getMethodes().remove(methode.getName());
	}
	
	public boolean existMethode(String name) {
		return fen.getAlgo().getMethodes().containsKey(getNameListReal(name));
	}
	
	public void createMethode(String name) {
		Methode methode = new Methode(name);
		loadMethode(methode);
		addListMethode(methode);
		fen.getAlgo().getMethodes().put(name, methode);
	}
	
	public void createMethodeDefault() {
		Methode methode = new Methode("Default", true);
		methode.setAction(new Action("Started", ActionType.start, null));
		Tabbed tab = new Tabbed(methode, this);
		loadMethode(methode);
		addListMethode(methode);
		fen.getAlgo().getMethodes().put(methode.getName(), methode);
		tab.updateTabbed();
	}
	
	public void hideMethode(String name) {
		 tabbedPane.removeTabAt(tabbedPane.indexOfTab(name));
	}
	
	public void afficheMethode() {
		Methode methode = getFen().getAlgo().getMethodes().get(getNameListReal(list.getSelectedValue()));
		int index = tabbedPane.indexOfTab(methode.getName());
		if(index == -1)
			loadMethode(methode);
		else 
			tabbedPane.setSelectedIndex(index);
	}
	
	public void createMethode() {
		while (true) {
			String name = JOptionPane.showInputDialog(getFen(), "What is name of methode create ?", "Methode Create", JOptionPane.QUESTION_MESSAGE);
			if (name == null) {
				break;
			} else if (name.isEmpty() || name == "" || name.contains(" ")) {
				JOptionPane.showMessageDialog(getFen(), "The name of methode is invalid.", "Methode Create", JOptionPane.ERROR_MESSAGE);
			} else if (!existMethode(name)) {
				createMethode(name);
				break;
			} else {
				JOptionPane.showMessageDialog(getFen(), "The name of methode exist already.", "Methode Create", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void deleteMethode() {
		if (!list.isSelectionEmpty()) {
			int result = JOptionPane.showConfirmDialog(getFen(), "Are you sure to delete methode ?", "Methode Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0) {
				for (String nameMethode : list.getSelectedValuesList()) {
					Methode methode = getFen().getAlgo().getMethodes().get(getNameListReal(nameMethode));
					if (!methode.isMain())
						deleteMethode(methode);
					else
						JOptionPane.showMessageDialog(getFen(), "You didn't deleted main methode.", "Methode Delete", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(getFen(), "Nothing selected.", "Methode Delete", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void renameMethode() {
		if (!list.isSelectionEmpty()) {
			String name = JOptionPane.showInputDialog(getFen(), "What is name of methode ?", "Methode Rename", JOptionPane.QUESTION_MESSAGE);
			if (name == null) {
				return;
			} else if (name.isEmpty() || name.contains(" ")) {
				JOptionPane.showMessageDialog(getFen(), "The name of methode is invalid.", "Methode Rename", JOptionPane.ERROR_MESSAGE);
			} else {
				int serial = 0;
				for (String nameMethode : list.getSelectedValuesList()) {
					Methode methode = getFen().getAlgo().getMethodes().get(getNameListReal(nameMethode));
					renameMethode(methode, name+" "+serial);
					serial++;
				}
				int nb = 0;
				if (existMethode(name))
					nb++;
				for (String nameMethode : list.getSelectedValuesList()) {
					Methode methode = getFen().getAlgo().getMethodes().get(getNameListReal(nameMethode));
					if (nb == 0) {
						renameMethode(methode, name);
						nb++;
					} else {
						while (existMethode(name+"_"+nb)) nb++;
						renameMethode(methode, name+"_"+nb);
						nb++;
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(getFen(), "Nothing selected.", "Methode Rename", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	public Fenetre getFen() {
		return fen;
	}
	
}
