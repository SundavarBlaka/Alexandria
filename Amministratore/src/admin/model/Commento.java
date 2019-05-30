package admin.model;

import java.io.Serializable;
import java.util.Date;

public class Commento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5700257516066054172L;

	private long id;

	private int idRisorsaConnessa;
	private String mittente;
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIdRisorsaConnessa() {
		return idRisorsaConnessa;
	}

	public void setIdRisorsaConnessa(int idRisorsaConnessa) {
		this.idRisorsaConnessa = idRisorsaConnessa;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Commento() {
		super();
	}

	@Override
	public String toString() {
		return "Commento [id=" + id + ", idRisorsaConnessa=" + idRisorsaConnessa + ", mittente=" + mittente + ", data="
				+ data + "]";
	}

}
