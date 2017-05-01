<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.*"%>
<%@ page import="controlador.*"%>
<%@ page import="utils.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	if(user != null) { if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){
		Controlador ctrl = new Controlador();
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artistas</title>
</head>
<body>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Inicio</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="itemTop.jsp">Discos</a></li>
				<li class="active"><a href="adminInicio">Editar<span
						class="sr-only">(current)</span></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="valid.jsp">Cerrar Sesión</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<% if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){ %>
					<li><a href="adminArtista.jsp">Artistas</a></li>
					<li><a href="adminGenero.jsp">Géneros</a></li>
					<li><a href="adminItem.jsp">Discos</a></li>
					<li><a href="adminUsuario.jsp">Usuarios</a></li>
					<% } %>
					<li><a href="adminStockPrecio.jsp">Remarcar</a></li>
			</div>
		</div>
	</div>

</body>
<%
	} else response.sendRedirect("itemTop.jsp");
	} else response.sendRedirect("login.jsp");
%>
</html>