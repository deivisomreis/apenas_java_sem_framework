package com.deivison_programmer.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({
	"/SessionFilter", "/admin/*", "/usuario/*"
})
public class SessionFilter implements Filter {
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		
		if(uri.startsWith("/admin/") && !uri.contains(".jsp") && session != null && req.getSession().getAttribute("admin") != null){
			chain.doFilter(request, response);
		}
		
		else if(uri.startsWith("/usuario/") && !uri.contains(".jsp") && session !=  null && req.getSession().getAttribute("usuario") != null){
			chain.doFilter(request, response);			
		}
		
		else{
			if(uri.startsWith("/admin/")) // redireciona para a classe ErroServlet tratar o erro para o Administrador
				resp.sendRedirect("/ErroServlet?erro=2");
			if(uri.startsWith("/usuario/"))
				resp.sendRedirect("/ErroServlet?erro=3"); // redireciona para a classe ErroServlet tratar o erro para o Usuário
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
