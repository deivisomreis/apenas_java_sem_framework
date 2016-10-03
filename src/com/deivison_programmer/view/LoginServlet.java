package com.deivison_programmer.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deivison_programmer.dao.AdminDAO;
import com.deivison_programmer.dao.UsuarioDAO;
import com.deivison_programmer.model.Admin;
import com.deivison_programmer.model.Usuario;

@WebServlet({"/login/admin", "/login/usuario"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String loginUsuario = "/login/usuario";
	private final String loginAdmin = "/login/admin";
	private UsuarioDAO usuarioDAO;
	private AdminDAO adminDAO;


	public LoginServlet() {
		usuarioDAO = new UsuarioDAO();
		adminDAO = new AdminDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		if(uri.equals(loginUsuario)){
			usuario(request, response);
		}

		if(uri.equals(loginAdmin)){
			admin(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");

		if(option != null && !option.isEmpty() && option.equals("logar")){
			Usuario usuario = usuarioDAO.login(request.getParameter("email"), request.getParameter("senha"));
			
			if(usuario != null && usuario.isStatus()){
				PrintWriter out = response.getWriter();
				
				out.println("Logado!");				
			}
			else if(usuario != null && !usuario.isStatus()){
				PrintWriter out = response.getWriter();
				out.println("<title>Usuário :: Acesso Bloqueado!</title>");
				out.println("<center>");
				out.println("<h3>Acesso Bloqueado</h3>");
				out.println("<p>Aguarde a liberação do seu acesso! Obrigado.</p>");
				out.println("<a href='/'>Home</a>");
				out.println("</center>");
			}
			else{
				request.setAttribute("title", "Login :: Usuario");
				request.setAttribute("path", "/login/usuario");
				request.setAttribute("status", "Erro: favor validar os dados!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		else{
			request.setAttribute("title", "Login :: Usuario");
			request.setAttribute("path", "/login/usuario");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	protected void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		
		if(request.getSession() != null  && request.getSession().getAttribute("admin") !=  null){
			Admin a = (Admin) request.getSession().getAttribute("admin");			
			request.setAttribute("nome", a.getNome());
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);	
		}

		else if(option != null && !option.isEmpty() && option.equals("logar")){
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			Admin admin = adminDAO.login(email, senha);
			
			if(admin != null){
				request.getSession().setAttribute("admin", admin);
				request.setAttribute("nome", admin.getNome());
				request.getRequestDispatcher("/admin/home.jsp").forward(request, response);				
			}
			else{
				request.setAttribute("status", "Favor validar os dados! Obrigado!");
				request.setAttribute("title", "Login :: Administrador");
				request.setAttribute("path", "/login/admin");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		else{
			request.setAttribute("title", "Login :: Administrador");
			request.setAttribute("path", "/login/admin");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
