package model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vendita {
	@Id @GeneratedValue
	@Column(name="VENDITA_ID")
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="vendita")
	@JoinColumn(name="USERNAME")
	private Profilo acquirente;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="vendita")
	@JoinColumn(name="USERNAME")
	private Profilo venditore;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profilo getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(Profilo acquirente) {
		this.acquirente = acquirente;
	}

	public Profilo getVenditore() {
		return venditore;
	}

	public void setVenditore(Profilo venditore) {
		this.venditore = venditore;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Vendita() {
		super();
	}
}
