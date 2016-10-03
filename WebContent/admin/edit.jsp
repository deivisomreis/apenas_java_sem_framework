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
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Painel :: Editar Cadastro</h3>
			</div>
			<div class="panel-body">
				<form action="/admin/edit" method="post">
					<input type="hidden" name="option" value="editar"/>
					<div class="form-group">
						<label for="nome">Nome:</label> 
						<input type="text" 	class="form-control" name="nome" value="${admin.nome}" required="true">
					</div>
					<div class="form-group">
						<label for="email">Email:</label> 
						<input type="email" 	class="form-control" name="email" value="${admin.email}" required="true">
					</div>
					<div class="form-group">
						<label for="senha">Senha:</label> 
						<input type="password" 	class="form-control" id="senha" name="senha" value="${admin.senha}" required="true">
					</div>
					<hr/>
					<button type="reset" class="btn btn-primary">Cancelar</button> &nbsp;  &nbsp; &nbsp;
					<button type="submit" class="btn btn-success">Editar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

<script>
$("#senha").mouseover(function(){
	$("#senha").attr("type", "text");
});

$("#senha").mouseout(function(){
	$("#senha").attr("type", "password");
})

</script>