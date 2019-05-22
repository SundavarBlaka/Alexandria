package model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Commento {
	@Id @GeneratedValue
	@Column(name="COMMENT_ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RISORSA_ASSOCIATA")
	private Risorsa risorsaConnessa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MITTENTE_COMMENTO")
	private Profilo mittente;
	
	private String testo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Risorsa getRisorsaConnessa() {
		return risorsaConnessa;
	}

	public void setRisorsaConnessa(Risorsa risorsaConnessa) {
		this.risorsaConnessa = risorsaConnessa;
	}

	public Profilo getMittente() {
		return mittente;
	}

	public void setMittente(Profilo mittente) {
		this.mittente = mittente;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Commento() {
		super();
	}
}
