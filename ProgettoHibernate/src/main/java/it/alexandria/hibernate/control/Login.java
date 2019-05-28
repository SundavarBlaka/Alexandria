package it.alexandria.hibernate.control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Carrello;
import it.alexandria.hibernate.model.Categoria;
import it.alexandria.hibernate.model.Credenziali;
import it.alexandria.hibernate.model.Messaggio;
import it.alexandria.hibernate.model.Profilo;
import it.alexandria.hibernate.model.Risorsa;
import it.alexandria.hibernate.model.UCMapping;
import it.alexandria.hibernate.model.Vendita;

public class Login extends HttpServlet implements ILogin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8009767287726849756L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type.equals("login")) {
			login(request, response);
			return;
		} else if (type.equals("registra")) {
			registra(request, response);
		} else if (type.equals("cambiaPassword")) {
			cambiaPassword(request, response);
		} else if (type.equals("logout")) {
			logout(request);
		} else {
			try {
				response.sendRedirect("/alexandria");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		if (verificaCredenziali(username, password)) {
			GestoreSessione gestore = GestoreSessione.getInstance();
			gestore.aggiungiSessione(request.getSession(), username);
			Profilo profilo = (Profilo) session.get(Profilo.class, username);
			@SuppressWarnings("unchecked")
			List<UCMapping> interessi= session.createQuery("FROM UCMAPPING WHERE USERNAME = :USERNAME").setParameter("USERNAME",username).getResultList();
			
			HttpSession sessione = request.getSession();
			sessione.setAttribute("username", profilo.getUsername().toLowerCase());
			sessione.setAttribute("nome", profilo.getNome());
			sessione.setAttribute("cognome", profilo.getCognome());
			sessione.setAttribute("data", profilo.getData());
			sessione.setAttribute("numeroTel", profilo.getNumeroTel());
			sessione.setAttribute("indirizzo", profilo.getIndirizzo());
			sessione.setAttribute("email", profilo.getEmail());
			sessione.setAttribute("interessi", interessi);
			Carrello carrello=new Carrello();
			carrello.setRisorseSelezionate(new ArrayList<Risorsa>());
			sessione.setAttribute("carrello", carrello);
		} else {
			try {
				response.sendRedirect("/alexandria");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				session.getTransaction().commit();
				session.close();
			}
			return;
		}

		try {
			response.sendRedirect("profile.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	public boolean verificaCredenziali(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Credenziali credenziali = (Credenziali) session.get(Credenziali.class, username);
		session.getTransaction().commit();
		session.close();
		if (credenziali != null) {
			return credenziali.getPassword().equals(password);
		}
		return false;
	}

	public void registra(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String nome = request.getParameter("name");
		String surname = request.getParameter("surname");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date data = java.sql.Date.valueOf(LocalDate.parse(request.getParameter("date"), dtf));

		/*
		 * ANCORA DA IMPLEMENTARE
		 */
		List<Categoria> interessi = new ArrayList<Categoria>();
		for (Categoria c : Categoria.values()) {
			if (request.getParameter(c.toString()) != null) {
				interessi.add(c);
			}
		}

		String email = request.getParameter("email");
		String numeroTel = request.getParameter("telefono");
		String password = request.getParameter("password");
		String indirizzo = request.getParameter("indirizzo");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Profilo profilo = new Profilo();
		profilo.setUsername(username);
		profilo.setNome(nome);
		profilo.setCognome(surname);
		profilo.setData(data);
		profilo.setLibreria(new ArrayList<Risorsa>());
		profilo.setMessaggiInviati(new ArrayList<Messaggio>());
		profilo.setMessaggiRicevuti(new ArrayList<Messaggio>());
		profilo.setRisorseAcquistate(new ArrayList<Vendita>());
		profilo.setRisorseVendute(new ArrayList<Vendita>());
		profilo.setEmail(email);
		profilo.setNumeroTel(numeroTel);
		profilo.setIndirizzo(indirizzo);
		
		for(Categoria c : interessi) {
			UCMapping map=new UCMapping();
			map.setCategoria(c);
			map.setUsername(username);
			session.save(map);
		}

		Credenziali cred = new Credenziali();
		cred.setUsername(username);
		cred.setPassword(password);

		session.save(profilo);
		session.save(cred);
		session.getTransaction().commit();
		session.close();
		try {
			response.sendRedirect("/alexandria");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public void cambiaPassword(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String passwordVecchia = request.getParameter("passwordVecchia");
		String nuovaPassword = request.getParameter("passwordNuova");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Credenziali attuali = (Credenziali) session.get(Credenziali.class, username);
		if (attuali.getPassword().equals(passwordVecchia)) {
			attuali.setPassword(nuovaPassword);
			session.getTransaction().commit();
			session.close();

			try {
				response.sendRedirect("profile.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		session.getTransaction().commit();
		session.close();
		try {
			response.sendRedirect("/alexandria");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public void logout(HttpServletRequest request) {
		GestoreSessione sessione = GestoreSessione.getInstance();
		sessione.rimuoviSessione(request.getSession());
	}

}
