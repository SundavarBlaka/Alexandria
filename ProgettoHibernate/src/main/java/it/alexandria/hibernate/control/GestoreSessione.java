package it.alexandria.hibernate.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class GestoreSessione {
	private Map<HttpSession,String> mappaSessioniAttive;
	private static GestoreSessione gestore = null;
	
	private GestoreSessione() {
		mappaSessioniAttive=new HashMap<HttpSession,String>();
	}
	
	public static GestoreSessione getInstance() {
		if(gestore==null)
			gestore=new GestoreSessione();
		return gestore;
	}
	
	public void aggiungiSessione(HttpSession sessione, String username) {
		mappaSessioniAttive.put(sessione, username);
	}
	
	public void rimuoviSessione(HttpSession sessione) {
		mappaSessioniAttive.remove(sessione);
	}
	
	public String verificaSessione(HttpSession sessione) {
		String result = null;
		
		result=mappaSessioniAttive.get(sessione);
		
		return result;
	}
}
