package admin.model;

import java.io.Serializable;
import java.util.Date;

public class Profilo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1162709043630252617L;

	private String username;
	private String nome;
	private String cognome;
	private Date data;
	private int numeroRisorse;

	public Profilo() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getNumeroRisorse() {
		return numeroRisorse;
	}

	public void setNumeroRisorse(int numeroRisorse) {
		this.numeroRisorse = numeroRisorse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Profilo [username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", data=" + data
				+ ", numeroRisorse=" + numeroRisorse + "]";
	}

}
