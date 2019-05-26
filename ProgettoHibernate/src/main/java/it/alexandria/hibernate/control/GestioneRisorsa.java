package it.alexandria.hibernate.control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Commento;
import it.alexandria.hibernate.model.Profilo;
import it.alexandria.hibernate.model.Risorsa;

public class GestioneRisorsa extends HttpServlet implements IGestioneRisorsa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5062957451523459725L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String tipo=request.getParameter("type");
		if(tipo==null) {
			
		}else if(tipo.equals("modifica")) {
			modificaRisorsa(request,response);
		}else if(tipo.contentEquals("mostra")) {
			mostraRisorsa(request,response);
		}else if(tipo.equals("inserisciCommento")) {
			inserisciCommento(request,response);
		}
	}

	public boolean modificaRisorsa(HttpServletRequest request, HttpServletResponse response) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, Long.parseLong(request.getParameter("id")));
		risorsa.setPrezzo(Float.parseFloat(request.getParameter("price")));
		risorsa.setDescrizione(request.getParameter("description"));
		session.save(risorsa);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}

	public void inserisciCommento(HttpServletRequest request, HttpServletResponse response) {
		Date data=java.sql.Timestamp.valueOf(LocalDateTime.now());
		String username=request.getParameter("username");
		String testo=request.getParameter("testo");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Profilo profilo = (Profilo) session.get(Profilo.class, username);
		Risorsa risorsa=(Risorsa)request.getSession().getAttribute("risorsa");
		Commento commento = new Commento();
		commento.setData(data);
		commento.setMittente(profilo);
		commento.setRisorsaConnessa(risorsa);
		commento.setTesto(testo);
		
		session.save(commento);
		session.getTransaction().commit();
		session.close();
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		risorsa = (Risorsa) session.get(Risorsa.class, risorsa.getId());
		request.getSession().setAttribute("risorsa", risorsa);
		session.getTransaction().commit();
		session.close();
		
		try {
			
			response.sendRedirect("single.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rimuoviCommento(HttpServletRequest request, HttpServletResponse reponse) {
		// TODO Auto-generated method stub
		
	}

	public void mostraRisorsa(HttpServletRequest request, HttpServletResponse reponse) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, Long.parseLong(request.getParameter("id")));
		request.getSession().setAttribute("risorsa", risorsa);
		session.getTransaction().commit();
		session.close();
		try {
			reponse.sendRedirect("single.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
