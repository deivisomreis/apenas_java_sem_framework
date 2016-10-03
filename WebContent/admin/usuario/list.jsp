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
				<li class="dropdown" class="active"><a class="dropdown-toggle"
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
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Lista de Usuários</h3>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Ordem</th>
							<th>Nome</th>
							<th>Email<a href=""><span class="glyphicon glyphicon-chevron-up"></span></a> <a href=""><span class="glyphicon glyphicon-chevron-down"></span></a></th>
							<th>Nivel<a href="id=3"><span class="glyphicon glyphicon-chevron-up"></span></a> <a href="id=4"><span class="glyphicon glyphicon-chevron-down"></span></a></th>
							<th>Status<a href="id=1"><span class="glyphicon glyphicon-chevron-up"></span></a> <a href="id=2"><span class="glyphicon glyphicon-chevron-down"></span></a></th>
							<th colspan="2" align="center">Opções</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty users}">
							<c:forEach items="${users}" var="user" varStatus="id">
							<tr>
								<td>${id.count}</td>
								<td>${user.nome}</td>
								<td><a href="mailto:${user.email}">${user.email}</a></td>
								<td>
									<c:if test="${user.nivel == 1}">Básico</c:if>
									<c:if test="${user.nivel == 2}">Intermediário</c:if>
									<c:if test="${user.nivel == 3}">Avançado</c:if>
								</td>
								<td>
									<c:if test="${user.status ==true}">
										<a href="/admin/user/disable?id=${user.id}" data-toggle="tooltip" title="Clique para Desativar!">Ativo</a>
									</c:if>
									
									<c:if test="${user.status == false }">
										<a href="/admin/user/active?id=${user.id}" data-toggle="tooltip" title="Clique para Ativar!">Desativado</a>
									</c:if>
								</td>
								<td><a href="/admin/user/edit?id=${user.id}"><button type="button" class="btn btn-primary">Editar</button></a></td>
								<td><a href="/admin/user/show?id=${user.id}"><button type="button" class="btn btn-success">Vizualizar</button></a></td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty users}">
							<tr>
								<td align="center" colspan="7">Sem Usuários</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<span class="glyphicon glyphicon-chevron-up"></span>
	</div>
</body>
</html>

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});
</script>