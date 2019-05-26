package it.alexandria.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name="RISORSA")
public class Risorsa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 894276491656218468L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RES_ID")
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VENDITA_ID", nullable=true)
	private Vendita vendita;
	
	private String titolo;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	private String autori;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private float prezzo;
	private String descrizione;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RES_OWNER")
	private Profilo proprietario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="risorsaConnessa", fetch=FetchType.EAGER)
	private List<Commento> commenti;
	
	private String url;

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

	public Vendita getVendita() {
		return vendita;
	}

	public void setVendita(Vendita vendita) {
		this.vendita = vendita;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String urlImmagine) {
		this.url = urlImmagine;
	}
}
