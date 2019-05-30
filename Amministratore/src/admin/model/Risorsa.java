package admin.model;

import java.io.Serializable;
import java.util.Date;

public class Risorsa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 894276491656218468L;
	private long id;
	private String titolo;
	private String autori;
	private Date data;
	private float prezzo;
	private String descrizione;

	private String idProprietario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutori() {
		return autori;
	}

	public void setAutori(String autori) {
		this.autori = autori;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(String idProprietario) {
		this.idProprietario = idProprietario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return this.id == ((Risorsa) obj).id;
	}

	@Override
	public String toString() {
		return "Risorsa [id=" + id + ", titolo=" + titolo + ", proprietario=" + idProprietario + "]";
	}
	
	

}
