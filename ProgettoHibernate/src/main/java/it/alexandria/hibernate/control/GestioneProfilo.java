package it.alexandria.hibernate.control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Carrello;
import it.alexandria.hibernate.model.Profilo;
import it.alexandria.hibernate.model.Risorsa;
import it.alexandria.hibernate.model.Vendita;

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
		}else if(tipo.equals("acquista")) {
			acquistoRisorsa(request,response);
		}
	}
	
	public void ordine(HttpServletRequest request, HttpServletResponse response) {
		Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
		request.getSession().setAttribute("carrello",carrello);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for(Risorsa r:carrello.getRisorseSelezionate()) {
			Profilo acquirente=(Profilo) session.get(Profilo.class, (String)request.getSession().getAttribute("username"));
			Profilo venditore=r.getProprietario();
			Vendita vendita=new Vendita();
			vendita.setAcquirente(acquirente);
			vendita.setVenditore(venditore);
			vendita.setData(java.sql.Timestamp.valueOf(LocalDateTime.now()));
			vendita.setRisorsaVenduta(r);	
			session.save(vendita);
		}
		
		carrello.setRisorseSelezionate(new ArrayList<Risorsa>());;
		
		session.getTransaction().commit();
		session.close();
		
		try {
			response.sendRedirect("cart.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void acquistoRisorsa(HttpServletRequest request, HttpServletResponse response) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Vendita vendita=new Vendita();
		Profilo acquirente=(Profilo) session.get(Profilo.class, (String)request.getSession().getAttribute("username"));
		Risorsa risorsaAcquistata=(Risorsa) session.get(Risorsa.class, Long.parseLong(request.getParameter("id")));
		Profilo venditore=risorsaAcquistata.getProprietario();
		vendita.setAcquirente(acquirente);
		vendita.setVenditore(venditore);
		vendita.setData(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		vendita.setRisorsaVenduta(risorsaAcquistata);
		
		session.save(vendita);
		session.getTransaction().commit();
		session.close();
		
		try {
			response.sendRedirect("search");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void aggiungiAlCarrello(HttpServletRequest request, HttpServletResponse response) {
		//int quantity=Integer.parseInt(request.getParameter("quantity"));
		long idRisorsa=Long.parseLong(request.getParameter("id"));
		Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, idRisorsa);
		request.getSession().setAttribute("risorsa",risorsa);
		session.getTransaction().commit();
		session.close();
		/*
		for(int i=0;i<quantity;i++) {
			carrello.getRisorseSelezionate().add(risorsa);
		}
		*/
		
		carrello.getRisorseSelezionate().remove(risorsa);
		carrello.getRisorseSelezionate().add(risorsa);
		
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
