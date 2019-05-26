package it.alexandria.hibernate.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Carrello;
import it.alexandria.hibernate.model.Risorsa;

public class GestioneProfilo extends HttpServlet implements IGestioneProfilo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3427709074023189454L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String tipo=request.getParameter("type");
		if(tipo==null) {
			
		}else if(tipo.equals("aggiungi")) {
			aggiungiAlCarrello(request,response);
		}else if(tipo.equals("rimuovi")) {
			rimuoviDalCarrello(request,response);
		}else if(tipo.equals("ordine")) {
			ordine(request,response);
		}
	}
	
	public void ordine(HttpServletRequest request, HttpServletResponse response) {
		Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
		carrello.setRisorseSelezionate(new ArrayList<Risorsa>());
		request.getSession().setAttribute("carrello",carrello);
		
		try {
			response.sendRedirect("cart.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void acquistoRisorsa(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void aggiungiAlCarrello(HttpServletRequest request, HttpServletResponse response) {
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		long idRisorsa=Long.parseLong(request.getParameter("id"));
		Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, idRisorsa);
		session.getTransaction().commit();
		session.close();
		for(int i=0;i<quantity;i++) {
			carrello.getRisorseSelezionate().add(risorsa);
		}
		
		try {
			response.sendRedirect("single.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rimuoviDalCarrello(HttpServletRequest request, HttpServletResponse response) {
		long idRisorsa=Long.parseLong(request.getParameter("id"));
		Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, idRisorsa);
		carrello.getRisorseSelezionate().remove(risorsa);
		request.getSession().setAttribute("carrello",carrello);
		session.getTransaction().commit();
		session.close();
		
		try {
			response.sendRedirect("cart.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean modificaProfilo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}

	public void mostraProfilo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
}
