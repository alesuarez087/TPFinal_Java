<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="controlador.Controlador"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discos</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/dashboard.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<%
	Controlador ctrl = new Controlador(); 
	Usuario user = (Usuario)request.getSession().getAttribute("userSession"); 
%>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
				<li class="active"><a href="itemTop.jsp">Discos</a></li>
				<%  if(user!=null) if(user.getTipoUsuario() == entidades.Usuario.TiposUsuario.Administrador){ %>
				<li><a href="adminInicio.jsp">Editar</a></li>
				<% } else { %>
				<li><a href="listCompras.jsp">Compras</a></li>
				<% } %>
			</ul>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<% 	if(user != null && user.getTipoUsuario()==Usuario.TiposUsuario.Usuario){ int nro=0;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           		nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           	} else nro = 0; %>
				<li><a href="carrito.jsp"> <img alt="Brand"
						src="bootstrap/img/carrito25.png"> Carrito de compras <span
						clase="badge">(<%=nro %>)</a></li>

				<% } %>
				<%if(user != null){	request.getSession().setAttribute("logout", "logout");%>
				<li><a href="valid.jsp">Cerrar Sesión</a></li>
				<% } else { %>
				<li><a href="login.jsp">Iniciar Sesión</a></li>
				<% } %>
			</ul>
			<form action="srvItem" method="post" class="navbar-form navbar-right">
				<input type="text" class="form-control" id="buscar" name="buscar"
					placeholder="Que estás buscando?">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a>Seleccione Búsqueda<span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="itemTop.jsp">Inicio<span
							class="sr-only">(current)</span></a></li>
					<li><a href="itemForGenero.jsp">Géneros</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h2 class="page-header">Discos</h2>
		<div class="row placeholders">
			<h3>Top de Ventas</h3>
			<% for(Item item : ctrl.getTop8()){ %>
			<div class="col-xs-6 col-sm-3 placeholder">
				<img src="<%= item.getUrlPortada() %>" width="200" height="200"
					class="img-responsive" alt="Generic placeholder thumbnail">
				<h4><%= item.getTitulo() %></h4>
				<span class="text-muted"><%= item.GetArtista().getNombre() %></span>
				<h4>
					$<%=ctrl.getOnePrecioToday(item.getId()).getValor() %></h4>
				<form action="srvCompra" method="post" id="compra" name="compra">
					<input type="hidden" name="idSelect" id="idSelect"
						value="<%=item.getId()%>" /> <input
						class="btn btn-success btn-sm" type="submit" value="Agregar"
						id="eventSale" name="eventSale" />
				</form>
			</div>
			<% } %>
		</div>
		<br>
		<div class="row placeholders">
			<h3 class="sub-header">Top Semanal</h3>
			<% for(Item item : ctrl.getTopSemana()){ %>
			<div class="col-xs-6 col-sm-3 placeholder">
				<img src="<%= item.getUrlPortada() %>" width="200" height="200"
					class="img-responsive" alt="Generic placeholder thumbnail">
				<h4><%= item.getTitulo() %></h4>
				<span class="text-muted"><%= item.GetArtista().getNombre() %></span>
				<h4>
					$<%=ctrl.getOnePrecioToday(item.getId()).getValor() %></h4>
				<form action="srvCompra" method="post" id="compra" name="compra">
					<input type="hidden" name="idSelect" id="idSelect"
						value="<%=item.getId()%>" /> <input
						class="btn btn-success btn-sm" type="submit" value="Agregar"
						id="eventSale" name="eventSale" />
				</form>
			</div>
			<% } %>
		</div>
		<br>
		<div class="row placeholders">
			<h3 class="sub-header">Top Mensual</h3>
			<% for(Item item : ctrl.getTopMes()){ %>
			<div class="col-xs-6 col-sm-3 placeholder">
				<img src="<%= item.getUrlPortada() %>" width="200" height="200"
					class="img-responsive" alt="Generic placeholder thumbnail">
				<h4><%= item.getTitulo() %></h4>
				<span class="text-muted"><%= item.GetArtista().getNombre() %></span>
				<h4>
					$<%=ctrl.getOnePrecioToday(item.getId()).getValor() %></h4>
				<form action="srvCompra" method="post" id="compra" name="compra">
					<input type="hidden" name="idSelect" id="idSelect"
						value="<%=item.getId()%>" /> <input
						class="btn btn-success btn-sm" type="submit" value="Agregar"
						id="eventSale" name="eventSale" />
				</form>
			</div>
			<% } %>
		</div>
		<br>
		<div class="row placeholders">
			<h3 class="sub-header">Mejores Calificados</h3>
			<% for(Item item : ctrl.getMejorPromedio()){ %>
			<div class="col-xs-6 col-sm-3 placeholder">
				<img src="<%= item.getUrlPortada() %>" width="200" height="200"
					class="img-responsive" alt="Generic placeholder thumbnail">
				<h4><%= item.getTitulo() %></h4>
				<span class="text-muted"><%= item.GetArtista().getNombre() %></span>
				<h4>
					$<%=ctrl.getOnePrecioToday(item.getId()).getValor() %></h4>
				<form action="srvCompra" method="post" id="compra" name="compra">
					<input type="hidden" name="idSelect" id="idSelect"
						value="<%=item.getId()%>" /> <input
						class="btn btn-success btn-sm" type="submit" value="Agregar"
						id="eventSale" name="eventSale" />
				</form>
			</div>
			<% } %>
		</div>
	</div>

</body>
</html>