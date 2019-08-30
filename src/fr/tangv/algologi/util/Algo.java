package fr.tangv.algologi.util;

import java.util.HashMap;
import java.util.Map;

public class Algo {

	private Map<String, Methode> methodes;
	private String name;
	
	public Algo(String name) {
		this.name = name;
		methodes = new HashMap<String, Methode>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setText(String name) {
		this.name = name;
	}
	
	public Map<String, Methode> getMethodes() {
		return methodes;
	}
	
}
