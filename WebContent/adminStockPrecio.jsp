<%@ page import="java.util.ArrayList" %>
<%@ page import="controlador.Controlador" %>
<%@ page import="entidades.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Disco</title>
<script>
function valida(){
	if(formTabla.stockAgregado.value == ''){alert('Ingrese stock a Agregar'); return false}
	else return true
}
</script>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<%
	Controlador ctrl = new Controlador();
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	if(user != null){ if(user.getTipoUsuario() != Usuario.TiposUsuario.Usuario){   
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
          <li class="active"><a href="adminInicio.jsp">Editar</a></li>
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
			<li class="active"><a href="adminStockPrecio.jsp">Remarcar<span class="sr-only">(current)</span></a></li>
        </div>
	</div>
</div>

<div class="col-sm-9 col-sm-offset-3 col-md-12 col-md-offset-2 main">

<h2 class="page-header">Discos</h2>

<br>
<form role="form" action="srvItem" method="post" id="formTabla" name="formTabla" onsubmit="return valida()">
  	  <table>
		<tr>
			<td><b>Código:</b></td>
			<td colspan=2>
				<input type="text" readonly class="form-control" id="idItem" name="idItem" value="<%if(request.getAttribute("idItem")!=null){%><%=request.getAttribute("idItem") %><% }%>" size="43">
			</td>
		</tr>
		<tr>
			<td><b>Título:</b></td>
			<td colspan=2>
				<input type="text" readonly class="form-control" id="tituloItem" name="tituloItem" value="<%if(request.getAttribute("tituloItem")!=null){%><%=request.getAttribute("tituloItem") %><% }%>" size="55">
			</td>
		</tr>
		<tr>
			<td><b>Autor:</b></td>
			<td colspan=2>
				<input type="text" readonly class="form-control" id="tituloItem" name="tituloItem" value="<%if(request.getAttribute("artistaItem")!=null){%><%=request.getAttribute("artistaItem") %><% }%>" size="55">
			</td>
		</tr>
		<tr>
			<td><b>Tipo de Disco:</b></td>
			<td colspan=2>
				<input type="text" readonly class="form-control" id="tituloItem" name="tituloItem" value="<%if(request.getAttribute("tipoDisco")!=null){%><%=request.getAttribute("tipoDisco") %><% }%>" size="55">
			</td>
		</tr>
		<tr>
			<td><b>Precio:</b></td>
			<td>
				<input type="text" readonly class="form-control" id="precioItem" name="precioItem" value="<%if(request.getAttribute("precioItem")!=null){%><%=request.getAttribute("precioItem") %><% }%>">
			</td>
			<td>
				<input type="text" class="form-control" id="precioNuevo" name="precioNuevo" placeholder="Nuevo Precio">
			</td>
		</tr>
		<tr>
			<td><b>Stock:</b></td>
			<td>
				<input type="text" readonly class="form-control" id="stock" name="stock" value="<%if(request.getAttribute("stock")!=null){%><%=request.getAttribute("stock") %><% }%>">
			</td>
			<td>
				<input type="text" class="form-control" id="stockAgregado" name="stockAgregado" placeholder="Stock a Agregar">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<%if (request.getAttribute("messageError")!=null){ %> <font color="#FF0000"> <%=request.getAttribute("messageError") %></font><%} %>
      		</td>
      		<td>
      		</td>
		</tr>
	</table>
	<br>
	<div align="center">
	<input class="btn btn-success" type="submit" value="Guardar" id="saveRemarcar" name="saveRemarcar"/>
	<input class="btn btn-default" type="submit" value="Cancelar" id="clearForm" name="clearForm"/>
    </div>
    </form>

<br><br><br>

 <table class="table table-hover" style="background-color:#ffffff">
       <thead> 
         <tr> 
         	<th>Código</th>
           	<th>Título</th>
           	<th>Autor</th>
           	<th>Precio</th>
           	<th>Stock</th>
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
           	<td><%= item.GetPrecio().getValor() %></td>
           	<td><%= item.getStock() %></td>
           	<form role="form" action="srvItem" method="post" id="botonera" name="botonera">
           		<td style="vertical-align:middle">
           			<input type="hidden" name="idSelect" id="idSelect" value="<%=item.getId()%>"/>
           			<input class="btn btn-success btn-sm" type="submit" value="Modificar" id="eventRemarcar" name="eventRemarcar" />
           		</td>
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