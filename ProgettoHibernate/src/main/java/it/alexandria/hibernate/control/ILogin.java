package it.alexandria.hibernate.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILogin {
	public boolean verificaCredenziali(String username, String password);
	public void registra(HttpServletRequest request, HttpServletResponse response);
	public boolean cambiaPassword(HttpServletRequest request);
	public void logout(HttpServletRequest request);
}
