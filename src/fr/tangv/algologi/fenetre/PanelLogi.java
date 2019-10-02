package fr.tangv.algologi.fenetre;

import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
	private Map<String, String> listMethodeText;
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public PanelLogi(Fenetre fen) {
		this.fen = fen;
		this.setLayout(new BorderLayout());
		//------------------------------
		listMethodeText = new HashMap<String, String>();
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
	}
	
	public Map<String, String> getListMethodeText() {
		return listMethodeText;
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
		String donnee = listMethodeText.get(methode.getName());
		listMethodeText.remove(methode.getName());
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
		listMethodeText.put(methode.getName(), donnee);
	}
	
	public void deleteMethode(Methode methode) {
		listMethodeText.remove(methode.getName());
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
		new Tabbed(methode, this);
		loadMethode(methode);
		addListMethode(methode);
		fen.getAlgo().getMethodes().put(methode.getName(), methode);
	}
	
	public void hideMethode(String name) {
		int index = tabbedPane.indexOfTab(name);
		((Tabbed) tabbedPane.getComponentAt(index)).save();
		tabbedPane.removeTabAt(index);
	}
	
	public void saveInFile(File file) {
		for (int index = 0; index < tabbedPane.getTabCount(); index++) {
			((Tabbed) tabbedPane.getComponentAt(index)).save();
		}
	}
	
	public void save() {
		for (int index = 0; index < tabbedPane.getTabCount(); index++) {
			((Tabbed) tabbedPane.getComponentAt(index)).save();
		}
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
	
	private Action generatedAction(String[] code, Action prev) {
		String errorSys = "systaxe";
		String errorNoStart = "no start";
		Action acLast = prev;
		for (int index = 0; index < code.length; index++) {
			String[] line = code[index].split(" ", 2);
			switch(line[0]) {
				case "start":
					if (acLast == null) {
						acLast = new Action("", ActionType.start, null);
					} else {
						errorGenerated("already start");
					}
					break;
				case "end":
					if (acLast != null) {
						acLast = new Action("", ActionType.end, acLast);
					} else {
						errorGenerated(errorNoStart);
					}
					break;
				case "startfunction":
					if (acLast == null) {
						acLast = new Action("", ActionType.startFunction, null);
					} else {
						errorGenerated("already start");
					}
					break;
				case "endfunction":
					if (acLast != null) {
						acLast = new Action("", ActionType.endFunction, acLast);
					} else {
						errorGenerated(errorNoStart);
					}
					break;
				case "function"://name
					if (acLast != null) {
						if (line.length >= 2 && !line[1].isEmpty()) {
							acLast = new Action(line[1], ActionType.excuteFunction, acLast);
						} else {
							errorGenerated(errorSys);
						}
					} else {
						errorGenerated(errorNoStart);
					}
					break;
				case "if": //text endif and tab
					if (acLast != null) {
						if (line.length >= 2 && !line[1].isEmpty()) {
							acLast = new Action(line[1], ActionType.condiction, acLast);
							Map<String, Action> listNext = new HashMap<String, Action>();
							System.out.println("if: "+line[1]);
							if (index+1 < code.length) {
								while (index < code.length) {
									index++;
									if (code[index].length() != 0 && code[index].charAt(0) != '\t') {
										if (code[index].equals("endif")) {
											System.out.println("end: ");
											index++;
											break;
										}
										//différente condition
										String name = code[index];
										if (listNext.containsKey(name)) {
											errorGenerated("already condiction");
										} else if (index+1 < code.length) {
											int start = index+1;
											int end = index+1;
											while (index < code.length) {
												index++;
												if (code[index].length() != 0 && code[index].charAt(0) != '\t') {
													end = index;
													index--;
													break;
												}
											}
											int size = end-start;
											String[] codeExp = new String[size];
											System.out.println("Name: "+name);
											for (int get = 0; get < size; get++) {
												codeExp[get] = code[start+get].replaceFirst("\t", "");
												System.out.println(codeExp[get]);
											}
											Action action = generatedAction(codeExp, acLast);
											listNext.put(name, action);
										} else {
											errorGenerated(errorSys);
										}
									}
								}
							} else {
								errorGenerated(errorSys);
							}
						} else {
							errorGenerated(errorSys);
						}
					} else {
						errorGenerated(errorNoStart);
					}
					break;
				case "action": //text
					if (acLast != null) {
						if (line.length >= 2 && !line[1].isEmpty()) {
							acLast = new Action(line[1], ActionType.action, acLast);
						} else {
							errorGenerated(errorSys);
						}
					} else {
						errorGenerated(errorNoStart);
					}
					break;
				case "goto": //nameP
					if (acLast != null) {
						if (line.length >= 2 && !line[1].isEmpty()) {
							acLast = new Action(line[1], ActionType.gotoPoint, acLast);
						} else {
							errorGenerated(errorSys);
						}
					} else {
						errorGenerated(errorNoStart);
					}
					break;
				case ":": //nameP
					if (acLast != null) {
						if (line.length >= 2 && !line[1].isEmpty()) {
							acLast = new Action(line[1], ActionType.point, acLast);
						} else {
							errorGenerated(errorSys);
						}
					} else {
						errorGenerated(errorNoStart);
					}
					break;
				case "endif":
					errorGenerated("not is in if");
					break;
				case "":
					break;
				default:
					errorGenerated("not exist");
					break;
			}
		}
		return acLast;
	}
	
	public void generated() {
		for (Methode methode : fen.getAlgo().getMethodes().values()) {
			String[] code = listMethodeText.get(methode.getName()).replace("\r", "").split("\n");
			methode.setAction(generatedAction(code, null));
		}
	}
	
	private void errorGenerated(String reason) {
		System.out.println("Error Genrated: "+reason);
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	public Fenetre getFen() {
		return fen;
	}
	
}
