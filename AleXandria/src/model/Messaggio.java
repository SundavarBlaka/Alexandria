package model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Messaggio {
	@Id @GeneratedValue
	@Column(name="MEX_ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MITTENTE")
	private Profilo mittente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DESTINATARIO")
	private Profilo destinatario;
	
	private String testo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profilo getMittente() {
		return mittente;
	}

	public void setMittente(Profilo mittente) {
		this.mittente = mittente;
	}

	public Profilo getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Profilo destinatario) {
		this.destinatario = destinatario;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Messaggio() {
		super();
	}
}
