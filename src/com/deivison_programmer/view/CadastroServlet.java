package com.deivison_programmer.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deivison_programmer.dao.UsuarioDAO;
import com.deivison_programmer.model.Usuario;

@WebServlet("/cadastro/usuario")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CadastroServlet() {
		usuarioDAO = new UsuarioDAO();
	}
	
	private static UsuarioDAO usuarioDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		
		if(option != null && !option.isEmpty() && option.equals("logar")){
			
			int validaEmail = usuarioDAO.cadastradoOuStatus(!request.getParameter("email").isEmpty() ? request.getParameter("email") : null);
			
			if(validaEmail ==0){
				
				Usuario  usuario  =  new Usuario();
				
				usuario.setEmail(!request.getParameter("email").isEmpty() ? request.getParameter("email") : null);
				usuario.setNivel(!request.getParameter("nivel").isEmpty() ? Integer.parseInt(request.getParameter("nivel")) : 0);
				usuario.setNome(!request.getParameter("nome").isEmpty() ? request.getParameter("nome") : null);
				usuario.setSenha(!request.getParameter("senha").isEmpty()  && !request.getParameter("senha").equals("nome")? request.getParameter("senha") : null);
				
				if(usuario.getNome() != null && usuario.getEmail() != null && usuario.getSenha() != null){
					usuarioDAO.insert(usuario);
					
					request.setAttribute("status", "Cadastro Efetuado!");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				else{
					request.setAttribute("status", "Erro: preencher todos os campos");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				
				
			}
			
			else if(validaEmail ==1){
				request.setAttribute("status", "Atenção: email já cadastrado e acesso liberado!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			
			else{
				request.setAttribute("status", "Atenção: email já cadastrado e aguardando aprovação!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			
			
		}
		else{
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
