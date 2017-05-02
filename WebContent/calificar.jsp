<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.*"%>
<%@ page import="controlador.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/dashboard.css" rel="stylesheet">
<link href="bootstrap/css/propio.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<% Controlador ctrl = new  Controlador(); 
	   Usuario user = (Usuario)request.getSession().getAttribute("userSession"); 
	   Clasificacion clas = (Clasificacion)request.getSession().getAttribute("clas");
	   if(user != null){ if(user.getTipoUsuario() == Usuario.TiposUsuario.Usuario){ if(clas!=null){
	%>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
				<li><a href="itemTop.jsp">Discos</a></li>
				<li class="active"><a href="listCompras.jsp">Compras</a></li>
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
					<li><a href="listCompras.jsp">Compras</a></li>
					<li class="active"><a href="itemsComprados.jsp">Clasficaciones<span
							class="sr-only">(current)</span></a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h3 class="page-header">Clasificar</h3>
		<div class="row placeholders">
			<div class="col-xs-7 col-sm-10 placeholder">
				<form action="srvCalificar" method="post" id="califica"
					name="califica">
					<table class="center-block">
						<tr>
							<td align=center><img
								src="<%= clas.GetItem().getUrlPortada() %>" width="200"
								height="200" class="img-responsive"
								alt="Generic placeholder thumbnail"></td>
							<td></td>
							<td style="text-align: center">
								<table>
									<tr>
										<h3><%=clas.GetItem().getTitulo() %></h3>
									</tr>
									<tr>
										<h4><%=clas.GetItem().GetArtista().getNombre() %></h4>
									</tr>
									<tr>
										<h4>
											Lanzamiento:
											<%=clas.GetItem().getAnioLanzamiento() %></h4>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td style="text-align: center" class="form-inline" colspan=3>
								<p class="clasificacion">
									<input id="radio1" name="estrellas" value="5" type="radio" <%if(clas.getValor()==5){ %> checked <% } %>><label for="radio1">&#9733;</label> 
									<input id="radio2" name="estrellas" value="4" type="radio" <%if(clas.getValor()==4){ %> checked <% } %>><label for="radio2">&#9733;</label> 
									<input id="radio3" name="estrellas" value="3" type="radio" <%if(clas.getValor()==3){ %> checked <% } %>><label for="radio3">&#9733;</label>
									<input id="radio4" name="estrellas" value="2" type="radio" <%if(clas.getValor()==2){ %> checked <% } %>><label for="radio4">&#9733;</label> 
									<input id="radio5" name="estrellas" value="1" type="radio" <%if(clas.getValor()==1){ %> checked <% } %>><label for="radio5">&#9733;</label>
								</p>
							</td>
						</tr>
						<tr style="height: 30px;">
							<td colspan=3 style="text-align: center"><input type="text"
								class="form-control" id="messageAdd" name="messageAdd"
								value="<%if(clas.getDetalles()!=null){%><%=clas.getDetalles()%><% }%>"
								placeholder="Comentario"></td>
						</tr>
						<tr>
							<td colspan=3><br></td>
						</tr>
						<tr align=center>
							<td colspan=3><input class="btn btn-success" type="submit"
								value="Calificar" id="eventCalif" name="eventCalif" /> <input
								class="btn btn-default" type="submit" value="Cancelar"
								id="eventCancel" name="eventCancel" /></td>
						</tr>
						<tr>
							<td colspan=3 style="text-align: center">
								<h2>
									Promedio:
									<%=(double)Math.rint(ctrl.getPromedio(clas.getIdItem())) %></h2>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h3 class="page-subheader">Comentarios</h3>
		<%  if(ctrl.getAllClasificacion(clas.getIdItem()).size()!=0){
		for(Clasificacion cl : ctrl.getAllClasificacion(clas.getIdItem())){ 
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
	</div>

</body>

<%
		} else response.sendRedirect("itemsComprados.jsp");
	   	} else response.sendRedirect("adminInicio.jsp");
		} else response.sendRedirect("login.jsp");
%>
</html>