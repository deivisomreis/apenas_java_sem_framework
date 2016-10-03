package com.deivison_programmer.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/LogoutServlet", "/logout/admin", "/logout/usuario"})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if(uri.equals("/logout/admin")){			
			response.sendRedirect("/login/admin");
			request.getSession().invalidate();
		}
		
		if(uri.equals("/logout/usuario")){
			request.getSession().invalidate();
			response.sendRedirect("/login/usuario");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
