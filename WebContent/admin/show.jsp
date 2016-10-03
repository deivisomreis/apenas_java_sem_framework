<%@page import="com.teste_alexandre.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin ${nome}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/admin/home">Menu</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="dropdown" class="active"><a class="dropdown-toggle"	data-toggle="dropdown" href="#">${nome} - Perfil<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/show">Perfil</a></li>
						<li><a href="/admin/edit">Editar</a></li>
					</ul>
				</li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Usuário<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/user/new">Cadastro</a></li>
						<li><a href="/admin/user/list">Lista</a></li>
					</ul>
				</li>
				<li><a href="/logout/admin">Sair</a></li>
			</ul>
		</div>
		</nav>
		<hr />
		
		<c:if test="${not empty status}">
			<div class="alert alert-warning fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>${status}</strong>
			</div>
		</c:if>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Dados Cadastrados</h3>
			</div>
			<div class="panel-body">
			<h4>Nome:</h4>
			<p>${admin.nome}</p>
			<h4>Email:</h4>
			<p>${admin.email}</p>
			<h4>Senha:</h4>
			<p>
				<% Admin admin = (Admin) request.getAttribute("admin");
				String senha = admin.getSenha();
				
				for(int i = 0; i < senha.length(); i++){
					out.print("*");			
				}%>
			</p>
			
			<hr/>
			
			<a href="/admin/edit"><button type="button" class="btn btn-primary">Editar</button></a>
			</div>
		</div>


	</div>
</body>
</html>

<style>
p{font-style: italic; font-weight: bold; font-size: 18px;}
</style>