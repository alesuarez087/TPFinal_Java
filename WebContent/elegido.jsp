<%@ page import="controlador.Controlador"%>
<%@ page import="entidades.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Disco</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/dashboard.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<% 
	Controlador ctrl = new Controlador();
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	Item item = (Item)request.getSession().getAttribute("item");
	if(user != null){ if(user.getTipoUsuario() == Usuario.TiposUsuario.Usuario) { if(item!=null) {
%>
<body>


	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
				<li class="active"><a href="itemTop.jsp">Discos</a></li>
				<li><a href="listCompras.jsp">Compras</a></li>
			</ul>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<% 	if(user != null){ int nro=0;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           		nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           	} else nro = 0; %>
				<li><a href="carrito.jsp"> <img alt="Brand"
						src="bootstrap/img/carrito25.png"> Carrito de compras <span
						clase="badge">(<%=nro %>)</a></li>

				<% } %>
				<li><a href="valid.jsp">Cerrar Sesión</a></li>
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
					<li><a href="itemTop.jsp">Inicio</a></li>
					<li><a href="itemForGenero.jsp">Géneros</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<form role="form" action="srvCompra" method="post" id="compra"
			name="compra">
			<h2 class="page-header">Disco</h2>
			<div class="row placeholders">
				<div class="col-xs-6 col-sm-3 placeholder">
					<table>
						<tr>
							<td colspan="2"><img src="<%= item.getUrlPortada() %>"
								width="200" height="200" class="img-responsive"
								alt="Generic placeholder thumbnail"></td>
						</tr>
						<tr>
							<td colspan="2">
								<h4><%= item.getTitulo() %></h4>
							</td>
						</tr>
						<tr>
							<td colspan="2"><span class="text-muted"><%= item.GetArtista().getNombre() %></span>
							</td>
						</tr>
						<tr>
							<td colspan=2>
								<h2>
									Promedio:
									<%=(double)Math.rint(ctrl.getPromedio(item.getId())) %></h2>
							</td>
						</tr>
						<tr>
							<td style="vertical-align: middle">
								<h3>Cantidad</h3>
							</td>
							<td style="vertical-align: middle"><select
								class="form-control" id="cmbCantidad" name="cmbCantidad">
									<option></option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
							</select></td>
						</tr>
					</table>
					<input type="hidden" name="idSelect" id="idSelect"
						value="<%=item.getId()%>" /> <input
						class="btn btn-success btn-sm" type="submit" value="Agregar"
						id="addCarrito" name="addCarrito" />
				</div>
			</div>
		</form>

		<table>
			<tr>
				<td style="text-align: center">
					<h3 class="page-subheader">Comentarios</h3>
				</td>
			</tr>
			<tr>
				<% if(ctrl.getAllClasificacion(item.getId()).size() !=0){
		for(Clasificacion cl : ctrl.getAllClasificacion(item.getId())){
			if(cl.getDetalles() != null){ %>
				<div class="panel panel-default">
					<div class="panel-heading">
						Usuario:
						<%=cl.GetUsuario().getNombreUsuario() %></div>
					<div class="panel-body">
						<%= cl.getDetalles() %>
					</div>
				</div>
				<% } } } else{ %>
				<h4>Sin Comentarios</h4>
				<% } %>
			</tr>
		</table>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


	</div>

</body>
<%
	} else response.sendRedirect("itemTop.jsp");
	} else response.sendRedirect("adminInicio.jsp");
	} else response.sendRedirect("login.jsp");
	
%>
</html>