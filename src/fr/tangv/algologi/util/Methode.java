package fr.tangv.algologi.util;

public class Methode {

	private Action action;
	private String name;
	private boolean main;
	
	public Methode(String name) {
		this.name = name;
		this.main = false;
		action = null;
	}
	
	public Methode(String name, boolean main) {
		this.name = name;
		this.main = main;
		action = null;
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
	
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	public Methode clone(String newName) {
		Methode methode =  new Methode(newName);
		methode.setAction(action.clone(null));
		return methode;
	}
	
}