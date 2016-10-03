package com.deivison_programmer.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ErroServlet")
public class ErroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String erro = request.getParameter("erro");
		
		if(erro != null && erro.equals("1")){
			request.setAttribute("title", "ERRO :: URL NÂO ENCONTRADA");
			request.setAttribute("mensagem", "Página não encontrada!");
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
		if(erro != null && erro.equals("2")){
			request.setAttribute("title", "ERRO :: URL NÂO ENCONTRADA");
			request.setAttribute("path", "login/admin");
			request.setAttribute("mensagem", "Favor realizar o login!");
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
		
		if(erro != null && erro.equals("3")){
			request.setAttribute("title", "ERRO :: URL NÂO ENCONTRADA");
			request.setAttribute("path", "login/usuario");
			request.setAttribute("mensagem", "Favor realizar o login!");
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
