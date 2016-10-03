<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${title}</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
		<body>
			<div class="container">
				<h3>${title}</h3>
				
		<c:if test="${not empty status}">
			<div class="alert alert-warning fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>${status}</strong>
			</div>
		</c:if>

		<form action="${path}" method="post">
			<input type="hidden" name="option" value="logar"/>
			<div class="form-group">
				<label for="email">Email:</label> 
				<input type="email" class="form-control" name="email" required="true">
			</div>			
			<div class="form-group">
				<label for="senha">Senha:</label> 
				<input type="password" class="form-control" name="senha"  value="" required="true">
			</div>
			
			<button type="reset" class="btn btn-default">Limpar</button> &nbsp; <button type="submit" class="btn btn-default">Entrar</button>
		</form>
	</div>
		</body>
</html>