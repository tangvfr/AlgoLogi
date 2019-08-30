package fr.tangv.algologi.util;

import java.util.ArrayList;
import java.util.List;

public class Methode {

	private List<Action> actions;
	private String name;
	private boolean main;
	
	public Methode(String name) {
		this.name = name;
		this.main = false;
		actions = new ArrayList<Action>();
	}
	
	public Methode(String name, boolean main) {
		this.name = name;
		this.main = main;
		actions = new ArrayList<Action>();
	}
	
	public boolean isMain() {
		return main;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Action> getActions() {
		return actions;
	}
	
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	public Methode clone(String newName) {
		Methode methode =  new Methode(newName);
		List<Action> actions = new ArrayList<Action>();
		for (Action ac : this.actions) {
			actions.add(ac.clone(null));
		}
		methode.setActions(actions);
		return methode;
	}
	
}