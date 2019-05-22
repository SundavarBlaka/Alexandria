package model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

public class Risorsa {
	@Id @GeneratedValue
	@Column(name="RES_ID")
	private long id;
	
	private String titolo;
	private Categoria categoria;
	private String autori;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime data;
	
	private float prezzo;
	private String descrizione;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RES_OWNER")
	private Profilo proprietario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="risorsaConnessa")
	private List<Commento> commenti;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAutori() {
		return autori;
	}

	public void setAutori(String autori) {
		this.autori = autori;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
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

	public Profilo getProprietario() {
		return proprietario;
	}

	public void setProprietario(Profilo proprietario) {
		this.proprietario = proprietario;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	public Risorsa() {
		super();
	}
}
