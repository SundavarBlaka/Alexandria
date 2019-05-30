package admin.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import admin.controller.db.CommentRepository;
import admin.controller.db.DataSource;
import admin.controller.db.ProfileRepository;
import admin.controller.db.ResourceRepository;
import admin.controller.db.VenditaRepository;
import admin.model.Commento;
import admin.model.Profilo;
import admin.model.Risorsa;
import admin.model.Vendita;

public class Controller {
	private String logFile;
	private ResourceRepository rr;
	private ProfileRepository pr;
	private CommentRepository cr;
	private VenditaRepository vr;

	public Controller(String logFile) {
		this.logFile = logFile;
		DataSource ds = new DataSource();
		rr = new ResourceRepository(ds);
		pr = new ProfileRepository(ds);
		cr = new CommentRepository(ds);
		vr = new VenditaRepository(ds);
	}

	public List<String> getLogs(String key) {
		List<String> res = new ArrayList<String>();

		BufferedReader bf;

		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)));
			String line = null;

			while ((line = bf.readLine()) != null) {
				if (line.contains(key)) {
					res.add(line);
				}
			}

		} catch (FileNotFoundException fnfex) {
			System.err.println("Errore: file non trovato");
		} catch (IOException e) {
			System.err.println("Errore durante la lettura del file di log");
		}

		return res;
	}

	public List<Risorsa> getRisorsaByUsername(String key) {

		return rr.getRisorsaByUser(key);

	}

	public List<Risorsa> getRisorsaByTitolo(String key) {
		return rr.getRisorsaByTitolo(key);
	}

	public List<Risorsa> getRisorsaById(String key) {
		return rr.getRisorsaById(Integer.parseInt(key));
	}

	public boolean removeRisorsaById(String line) {
		return rr.removeRisorsaByID(Integer.parseInt(line));
	}

	public Profilo getProfiloByUsername(String line) {
		return pr.getProfiloByUsername(line);
	}

	public boolean removeProfiloByUsername(String line) {
		return pr.removeProfiloByUsername(line);
	}

	public List<Commento> getCommentoByRisorsa(String line) {
		List<Commento> res = null;
		try {
			int id = Integer.parseInt(line);
			res = cr.getCommentoByRisorsa(id);
		} catch (NumberFormatException e) {
			res = null;
		}

		return res;
	}

	public List<Commento> getCommentoByUsername(String username) {
		return cr.getCommentoByUsername(username);
	}

	public boolean removeCommentoById(String id) {
		boolean res = false;

		try {
			int identificativo = Integer.parseInt(id);
			res = cr.removeCommentoById(identificativo);
		} catch (NumberFormatException e) {
			res = false;
		}

		return res;
	}

	public List<Vendita> getVendita(String acq, String vend, String ris) {

		return vr.getVendite(acq, vend, ris);

	}

}
