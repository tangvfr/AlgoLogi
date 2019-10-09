package fr.tangv.algologi.util;

import java.util.HashMap;
import java.util.Map;

public class Algo {

	private Map<String, Methode> methodes;
	
	public Algo() {
		methodes = new HashMap<String, Methode>();
	}
	
	public Map<String, Methode> getMethodes() {
		return methodes;
	}
	
}
