package admin.model;

import java.io.Serializable;
import java.util.Date;

public class Vendita implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6867289775775430326L;

	private long id;
	private String acquirente;
	private String venditore;
	private int idRisorsaVenduta;
	private Date data;

	public Vendita() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(String acquirente) {
		this.acquirente = acquirente;
	}

	public String getVenditore() {
		return venditore;
	}

	public void setVenditore(String venditore) {
		this.venditore = venditore;
	}

	public int getIdRisorsaVenduta() {
		return idRisorsaVenduta;
	}

	public void setIdRisorsaVenduta(int idRisorsaVenduta) {
		this.idRisorsaVenduta = idRisorsaVenduta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Vendita [id=" + id + ", acquirente=" + acquirente + ", venditore=" + venditore + ", idRisorsaVenduta="
				+ idRisorsaVenduta + ", data=" + data + "]";
	}

}
