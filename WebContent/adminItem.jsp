<%@ page import="java.util.ArrayList"%>
<%@ page import="controlador.Controlador"%>
<%@ page import="entidades.*"%>
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
	if(user != null){ if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){  
%>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="itemTop.jsp">Discos</a></li>
				<li class="active"><a href="adminInicio">Editar</a></li>
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
					<li class="active"><a href="adminItem.jsp">Discos<span
							class="sr-only">(current)</span></a></li>
					<li><a href="adminUsuario.jsp">Usuarios</a></li>
					<% } %>
					<li><a href="adminStockPrecio.jsp">Remarcar</a></li>
			</div>
		</div>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-12 col-md-offset-2 main">

		<h2 class="page-header">Discos</h2>

		<br>
		<form role="form" action="srvItem" method="post" id="formTabla"
			name="formTabla">
			<table>
				<tr>
					<td><b>Código:</b></td>
					<td><input type="text" readonly class="form-control"
						id="idItem" name="idItem"
						value="<%if(request.getAttribute("idItem")!=null){%><%=request.getAttribute("idItem") %><% }%>"
						size="43"></td>
				</tr>
				<tr>
					<td><b>Título:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="tituloItem"
								name="tituloItem"
								value="<%if(request.getAttribute("tituloItem")!=null){%><%=request.getAttribute("tituloItem") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Autor:</b></td>
					<td><select class="form-control" id="cmbArtista"
						name="cmbArtista">
							<option>
								<%if(request.getAttribute("artistaItem")!=null){%>
								<%= request.getAttribute("artistaItem") %>
								<%}%>
							</option>
							<%
          		 		for(Artista artista : ctrl.getAllArtistaHab()){
           			%>
							<option>
								<%= artista.getNombre() %>
							</option>
							<% } %>
					</select></td>
				</tr>
				<tr>
					<td><b>Género:</b></td>
					<td><select class="form-control" id="cmbGenero"
						name="cmbGenero">
							<option>
								<%if(request.getAttribute("generoItem")!=null){%>
								<%= request.getAttribute("generoItem") %>
								<%}%>
							</option>
							<%
          	 		for(Genero genero : ctrl.getAllGeneroHab()){
           		%>
							<option>
								<%=genero.getDescripcion()%>
							</option>
							<%} %>
					</select></td>
				</tr>
				<tr>
					<td><b>Tipo de Disco:</b></td>
					<td><select class="form-control" id="cmbTipoDisco"
						name="cmbTipoDisco">
							<option>
								<%if(request.getAttribute("tipoDisco")!=null){%>
								<%= request.getAttribute("tipoDisco") %>
								<%}%>
							</option>
							<%
          	 		for(TipoItem ti : ctrl.getAllTipoItemHab()){
           		%>
							<option>
								<%=ti.getDescripcion()%>
							</option>
							<%} %>
					</select></td>
				</tr>
				<tr>
					<td><b>Año Lanzamiento: </b></td>
					<td><input type="text" class="form-control"
						id="anioLanzamiento" name="anioLanzamiento"
						value="<%if(request.getAttribute("anioLanzamiento")!=null){%><%=request.getAttribute("anioLanzamiento") %><% }%>"></td>
				</tr>
				<tr>
					<td><b>Precio:</b></td>
					<td><input type="text" class="form-control" id="precioItem"
						name="precioItem"
						value="<%if(request.getAttribute("precioItem")!=null){%><%=request.getAttribute("precioItem") %><% }%>"></td>
				</tr>
				<tr>
					<td><b>Stock:</b></td>
					<td><input type="text" class="form-control" id="stock"
						name="stock"
						value="<%if(request.getAttribute("stock")!=null){%><%=request.getAttribute("stock") %><% }%>"></td>
				</tr>
				<tr>
					<td><b>URL Portada:</b></td>
					<td><input type="text" class="form-control" id="urlPortada"
						name="urlPortada"
						value="<%if(request.getAttribute("urlPortada")!=null){%><%=request.getAttribute("urlPortada") %><% }%>"></td>
				</tr>
				<tr>
					<td colspan="2">
						<%if (request.getAttribute("messageError")!=null){ %> <font
						color="#FF0000"> <%=request.getAttribute("messageError") %></font>
						<%} %>
					</td>
					<td></td>
				</tr>
			</table>
			<br>
			<div align="center">
				<input class="btn btn-success" type="submit" value="Guardar"
					id="saveItem" name="saveItem" /> <input class="btn btn-default"
					type="submit" value="Cancelar" id="clearForm" name="clearForm" />
			</div>
		</form>

		<br> <br> <br>

		<table class="table table-hover" style="background-color: #ffffff">
			<thead>
				<tr>
					<th>Código</th>
					<th>Título</th>
					<th>Autor</th>
					<th>Año Lanzamiento</th>
					<th>Género</th>
					<th>Precio</th>
					<th>Stock</th>
					<th>Habilitado</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% 
        	for(Item item : ctrl.getAllItem()){  
        %>
				<tr>
					<td><%= item.getId() %></td>
					<td><%= item.getTitulo() %></td>
					<td><%= item.GetArtista().getNombre() %></td>
					<td><%= item.getAnioLanzamiento() %></td>
					<td><%= item.GetGenero().getDescripcion() %></td>
					<td><%= item.GetPrecio().getValor() %></td>
					<td><%= item.getStock() %></td>
					<td style="vertical-olign: middle"><input type="checkbox"
						readonly disabled <%if(item.isHabilitado()){ %> checked <%} %>>
					<td></td>
					<form role="form" action="srvItem" method="post" id="botonera"
						name="botonera">
						<td style="vertical-align: middle"><input type="hidden"
							name="idSelect" id="idSelect" value="<%=item.getId()%>" /> <input
							class="btn btn-success btn-sm" type="submit" value="Modificar"
							id="eventUpdate" name="eventUpdate" /> <input
							class="btn btn-danger btn-sm" type="submit" value="Eliminar"
							id="eventDelete" name="eventDelete" /></td>
					</form>
				</tr>
				<% 	} %>
			</tbody>
		</table>


	</div>


</body>
<%
	} else response.sendRedirect("itemTop.jsp");
	} else response.sendRedirect("login.jsp");
%>
</html>