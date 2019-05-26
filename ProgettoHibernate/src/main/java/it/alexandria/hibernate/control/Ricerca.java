package it.alexandria.hibernate.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;


import it.alexandria.hibernate.model.Risorsa;

public class Ricerca extends HttpServlet implements IRicerca{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590834714280558759L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		List<Risorsa> risorse=ottieniRisorse(request,response);
		request.getSession().setAttribute("risorseRicercate", risorse);
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Risorsa> ottieniRisorse(HttpServletRequest request, HttpServletResponse response) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Risorsa> risorse=(List<Risorsa>)session.createQuery("FROM RISORSA").getResultList();
		session.getTransaction().commit();
		session.close();
		return risorse;
	}

}
