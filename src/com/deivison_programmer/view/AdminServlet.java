package com.deivison_programmer.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deivison_programmer.dao.AdminDAO;
import com.deivison_programmer.dao.UsuarioDAO;
import com.deivison_programmer.model.Admin;

@WebServlet({"/AdminServlet", "/admin/show", "/admin/edit", "/admin/home", "/admin/user/list", "/admin/user/active","/admin/user/disable",
	"/admin/user/show"
	})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO  userDAO;
	private AdminDAO adminDAO;
	
	public AdminServlet() {
		userDAO = new UsuarioDAO();
		adminDAO = new AdminDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if(uri.equals("/admin/home"))
			home(request, response);
		
		else if(uri.equals("/admin/show"))
			show(request, response);
		
		else if(uri.equals("/admin/edit"))
			edit(request, response);
		
		else if(uri.equals("/admin/user/list"))
			listUsers(request, response);
		
		else if(uri.equals("/admin/user/active"))
			actveUser(request, response);
		
		else if(uri.equals("/admin/user/disable"))
			disableUser(request, response);
		
		else if(uri.equals("/admin/user/show"))
			userShow(request, response);
		
		else
			pageErro(request, response);			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		request.setAttribute("nome", admin.getNome());
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);		
	}

	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		request.setAttribute("nome", admin.getNome());
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/admin/show.jsp").forward(request, response);
	}

	protected void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = request.getParameter("id") != null && !request.getParameter("id").isEmpty() ? Integer.parseInt(request.getParameter("id")) : null;
		request.setAttribute("users", userDAO.usuarios(id));
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		
		request.setAttribute("nome", admin.getNome());
		request.getRequestDispatcher("/admin/usuario/list.jsp").forward(request, response);
	}

	protected void actveUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));		
		userDAO.ativaUsuario(id);
		listUsers(request, response);
	}

	protected void disableUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		userDAO.desativaUsuario(id);
		listUsers(request, response);
	}
	
	protected void userShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("usuario", userDAO.getUsuario(id));
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		request.setAttribute("nome", admin.getNome());
		request.getRequestDispatcher("/admin/usuario/show.jsp").forward(request, response);
	}
	
	protected void pageErro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String option = request.getParameter("option");
			
			if(option != null && !option.isEmpty() && option.equals("editar")){
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String nome = request.getParameter("nome");
				
				if(nome != null && email != null && senha != null && !nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()){
					Admin admin = (Admin) request.getSession().getAttribute("admin");
					admin.setEmail(email);
					admin.setNome(nome);
					admin.setSenha(senha);
					
					adminDAO.update(admin.getId(), admin);
					
					request.getSession().setAttribute("admin", admin);
					request.setAttribute("status", "Dados atualizados com sucesso!");
					show(request, response);									
				}
				else{
					Admin admin = (Admin) request.getSession().getAttribute("admin");
					
					request.setAttribute("nome", admin.getNome());
					request.setAttribute("admin", admin);
					request.setAttribute("status", "Favor preencher todos os campos! Obrigado.");
					request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
				}
			}
			else{
				Admin admin = (Admin) request.getSession().getAttribute("admin");
				
				request.setAttribute("nome", admin.getNome());
				request.setAttribute("admin", admin);
				request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
			}			
		}
}
