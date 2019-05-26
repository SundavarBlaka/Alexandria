package it.alexandria.hibernate.control;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

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
		// TODO Auto-generated method stub
		
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
