package it.alexandria.hibernate.control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Carrello;
import it.alexandria.hibernate.model.Categoria;
import it.alexandria.hibernate.model.Profilo;
import it.alexandria.hibernate.model.Risorsa;
import it.alexandria.hibernate.model.UCMapping;
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
			mostraProfilo(request,response);
		}else if(tipo.equals("aggiungi")) {
			aggiungiAlCarrello(request,response);
		}else if(tipo.equals("rimuovi")) {
			rimuoviDalCarrello(request,response);
		}else if(tipo.equals("ordine")) {
			ordine(request,response);
		}else if(tipo.equals("acquista")) {
			acquistoRisorsa(request,response);
		}else if(tipo.equals("modifica")) {
			modificaProfilo(request,response);
		}else if(tipo.equals("mostra")) {
			mostraProfilo(request,response);
		}
	}
	
	private String getParameter(String content, String string) {
		if(content.equals("")) {
			return null;
		}
		
		String result="";
		StringTokenizer tokenizer=new StringTokenizer(content,"\n");
		while(tokenizer.hasMoreTokens()) {
			String token=tokenizer.nextToken();
			if(token.contains(string)) {
				String[] varTokens=token.replaceAll(string+"=","=&").split("&");
				for(String str: varTokens) {
					if(!str.contains("=")) {
						result=str;
						break;
					}
				}
			}
		}
		return result;
	}
	
	private String[] getParameters(String content, String string) {
		if(content.equals("")) {
			return null;
		}
		
		StringTokenizer tokenizer=new StringTokenizer(content,"\n");
		List<String> res=new ArrayList<String>();
		while(tokenizer.hasMoreTokens()) {
			String token=tokenizer.nextToken();
			if(token.contains(string)) {
				String[] varTokens=token.replaceAll(string+"=","&").split("&");
				for(String str: varTokens) {
					if(!str.contains("=")&&!str.equals("")) {
						res.add(str);
					}
				}
			}
		}
		return res.toArray(new String[0]);
	}
	
	public void ordine(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} 
		}
		
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
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} 
		}
		
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
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} 
		}
		
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
			response.sendRedirect("cart.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rimuoviDalCarrello(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} 
		}
		
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
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return false;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			} 
		}
		
		String email=getParameter((String)request.getSession().getAttribute("content"),"email");
		String tel=getParameter((String)request.getSession().getAttribute("content"),"tel");
		String address=getParameter((String)request.getSession().getAttribute("content"),"address");
		String[] interessi=getParameters((String)request.getSession().getAttribute("content"),"interessi");
		String username=(String)request.getSession().getAttribute("username");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Profilo profilo = (Profilo) session.get(Profilo.class, username);
		
		if(!email.equals("")) {
			profilo.setEmail(email);
			request.getSession().setAttribute("email", email);
		}
		if(!tel.equals("")) {
			profilo.setNumeroTel(tel);
			request.getSession().setAttribute("numeroTel", tel);
		}
		if(!address.equals("")) {
			profilo.setIndirizzo(address);
			request.getSession().setAttribute("indirizzo", address);
		}
		
		if(interessi!=null) {
			@SuppressWarnings("unchecked")
			List<UCMapping> interes = (List<UCMapping>) session.createQuery("FROM UCMAPPING WHERE USERNAME= :USERNAME").setParameter("USERNAME", username).getResultList();
			for(UCMapping interesse:interes) {
				session.delete(interesse);
			}
			List<UCMapping> newList=new ArrayList<UCMapping>();
			for(String interesse:interessi) {
				UCMapping map=new UCMapping();
				map.setUsername(username);
				map.setCategoria(Categoria.valueOf(interesse));
				session.save(map);
				newList.add(map);
			}
			request.getSession().setAttribute("interessi", newList);
		}
		
		session.save(profilo);
		session.getTransaction().commit();
		session.close();
		
		try {
			response.sendRedirect("profile");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void mostraProfilo(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} 
		}
		
		try {
			request.getRequestDispatcher("profile.jsp").forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
