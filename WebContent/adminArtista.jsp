<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.*" %>
<%@ page import="controlador.*" %>
<%@ page import="utils.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){ 
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
<title>Artistas</title>
<script>
function valida(){
	if(formTabla.descArtista.value == ''){alert('Ingrese de Nombre de Artista'); return false;}
	else return true
}
</script>
</head>
<body>
<% Controlador ctrl = new  Controlador(); %>
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
            <li class="active"><a href="adminArtista.jsp">Artistas<span class="sr-only">(current)</span></a></li>
            <li><a href="adminGenero.jsp">Géneros</a></li>
            <li><a href="adminItem.jsp">Discos</a></li>
            <li><a href="adminUsuario.jsp">Usuarios</a></li>
            <% } %>
			<li><a href="adminStockPrecio.jsp">Remarcar</a></li>
        </div>
	</div>
</div>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

<h2 class="page-header">Artistas</h2>

  	  <form role="form" action="srvArtista" method="post" id="formTabla" name="formTabla" onsubmit="return valida()">
  	  <table>
		<tr>
			<td><b>Código:</b></td>
			<td> 
				 <div class = "form-inline">
					<input type="text" readonly class="form-control" id="idArtista" name="idArtista" readonly value="<%if(request.getAttribute("idArtista")!=null){%><%=request.getAttribute("idArtista") %><% }%>"  size="43">
				 </div>
			</td>
		</tr>
		<tr>
			<td><b>Nombre:</b></td>
			<td><input type="text" class="form-control" id="descArtista" name="descArtista" value="<%if(request.getAttribute("descArtista")!=null){%><%=request.getAttribute("descArtista") %><% }%>"></td>
		</tr>
		<tr>
			<td><b>Habilitado:</b></td>
			<td> 
				 <div class = "form-inline">
					<input type="checkbox" class="form-control" id="habilitado" name="habilitado" <%if(request.getAttribute("habilitado")!=null){%> <%if(Boolean.parseBoolean(request.getAttribute("habilitado").toString())==true){ %>checked <% } }%>" size="55">
				 </div>
			</td>
		</tr>
		<tr>
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
	<input class="btn btn-success" type="submit" value="Guardar" id="newArtista" name="newArtista"/>
	<input class="btn btn-default" type="submit" value="Cancelar" id="clearForm" name="clearForm"/>
    </div>
    </form>
    
    <br><br><br>
    
    <table class="table table-hover">
       <thead> 
         <tr> 
           <th>Código</th>
           <th>Nombre</th>
           <th>Habilitado</th>
           <th></th> 
         </tr> 
       </thead>
       <tbody>
       	<% for(Artista artista : ctrl.getAllArtista()){  %>
         <tr>
           <td style="vertical-align:middle">
           		<input type="hidden" name="nro" id="nro" value="<%=artista.getId()%>" /><%=artista.getId()%>
           </td>
           <td style="vertical-align:middle">
           		<input type="hidden" name="desc" id="desc" value="<%=artista.getNombre()%>"/><%=artista.getNombre()%>
           </td>
           <td style="vertical-olign:middle">
           		<input type="checkbox" readonly disabled <%if(artista.isHabilitado()){ %> checked <%} %>><td>
           </td>
           <form role="form" action="srvArtista" method="post" id="botonera" name="botonera">
           		<td style="vertical-align:middle">
           			<input type="hidden" name="nombreSelect" id="nombreSelect" value="<%=artista.getNombre()%>"/>
           			<input class="btn btn-success btn-sm" type="submit" value="Modificar" id="eventUpdate" name="eventUpdate" />
  					<input class="btn btn-danger btn-sm" type="submit" value="Eliminar" id="eventDelete" name="eventDelete" />
           		</td>
           </form>
         </tr>
         <%} %>
       </tbody>
     </table>

</body>
<%
	} else { if(user.getTipoUsuario() == null) {
				response.sendRedirect("login.jsp");
			} else { response.sendRedirect("itemTop.jsp");
				}
	}
%>
</html>