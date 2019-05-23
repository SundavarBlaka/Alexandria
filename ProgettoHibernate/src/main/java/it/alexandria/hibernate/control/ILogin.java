package it.alexandria.hibernate.control;

import java.time.LocalDate;
import java.util.List;

import it.alexandria.hibernate.model.Categoria;

public interface ILogin {
	public boolean verificaCredenziali(String username, String password);
	public void registra(String username, String nome, String cognome,
			List<Categoria> interessi, String numeroTel, String indirizzo,
			String password, String email, LocalDate dataDiNascita);
	public boolean cambiaPassword(String vecchiaPassword, String nuovaPassword);
	public void logout();
}
