package it.alexandria.hibernate.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Categoria;
import it.alexandria.hibernate.model.Profilo;

public class Login extends HttpServlet implements ILogin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8009767287726849756L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		String username=request.getQueryString().split("&")[0].split("=")[1];
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Profilo profilo=(Profilo)session.get(Profilo.class,username);
		if(true) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\lucas\\OneDrive\\Desktop\\Interfacce\\profile.html")));
				String line;
				while((line=reader.readLine())!=null) {
					if(line.contains("<!-- Cambia dati -->")) {
						out.println("<h5 class=\"username\">"+profilo.getUsername()+"</h5>");
						out.println("<h6 class=\" name_surname\">"+profilo.getNome()+" "+profilo.getCognome()+"</h6>");
						out.println("Data di Nascit&agrave;: <h6 class=\"birthday\">"+profilo.getData().toString()+"</h6>");
						out.println("Tel: <h6 class=\"tel\">"+profilo.getNumeroTel()+"</h6>");
						out.println("Indirizzo: <h6 class=\"address\">"+profilo.getIndirizzo()+"</h6>");
					}else
						out.println(line);
				}
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	public boolean verificaCredenziali(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public void registra(String username, String nome, String cognome, List<Categoria> interessi, String numeroTel,
			String indirizzo, String password, String email, LocalDate dataDiNascita) {
		// TODO Auto-generated method stub
		
	}

	public boolean cambiaPassword(String vecchiaPassword, String nuovaPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	public void logout() {
		// TODO Auto-generated method stub
		
	}

}
