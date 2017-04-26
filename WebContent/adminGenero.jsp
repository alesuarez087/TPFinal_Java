<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.*" %>
<%@ page import="controlador.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Géneros</title>

<script type="text/javascript">
	function validar(){
		if(formTabla.descGenero == ""){alert('Ingrese Nombre de Genero');return false;}
		else return true;
	}
</script>

</head>
<%
	Controlador ctrl = new Controlador();
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){  
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
          <li class="active"><a href="adminInicio">Editar<span class="sr-only">(current)</span></a></li>
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
            <li class="active"><a href="adminGenero.jsp">Géneros<span class="sr-only">(current)</span></a></li>
            <li><a href="adminItem.jsp">Discos</a></li>
            <li><a href="adminUsuario.jsp">Usuarios</a></li>
            <% } %>
			<li><a href="adminStockPrecio.jsp">Remarcar</a></li>
        </div>
	</div>
</div>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

<h2 class="page-header">Géneros</h2>

	<form role="form" action="srvGenero" method="post" id="formTabla" name="formTabla">
		<table>
			<tr>
				<td><b>Código:</b></td>
			<td> 
				 <div class = "form-inline">
					<input type="text" class="form-control" id="idGenero" name="idGenero" readonly value="<%if(request.getAttribute("idGenero")!=null){%><%=request.getAttribute("idGenero") %><% }%>"  size="43">
				 </div>
			</td>
		</tr>
		<tr>
			<td><b>Nombre:</b></td>
			<td><input type="text" class="form-control" id="descGenero" name="descGenero" value="<%if(request.getAttribute("descGenero")!=null){%><%=request.getAttribute("descGenero") %><% }%>"></td>
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
			<td colspan="2">
				<%if (request.getAttribute("messageError")!=null){ %> <font color="#FF0000"> <%=request.getAttribute("messageError") %></font><%} %>
      		</td>
      		<td>
      		</td>
		</tr>
	</table>
	<br>
	<div align="center">
		<input class="btn btn-success" type="submit" value="Guardar" id="newGenero" name="newGenero"/>
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
       	<%
       		ArrayList<Genero> generos = new ArrayList<Genero>();
   	   		if(request.getAttribute("generos")==null)
       		generos = ctrl.getAllGenero();
   	   		else
   	   		generos = (ArrayList<Genero>) request.getAttribute("generos"); int i = 0;
       		for(Genero genero : generos){  %>
         <tr>
           <td style="vertical-align:middle">
           		<input type="hidden" name="nro" id="nro" value="<%=genero.getId()%>"/><%=genero.getId()%>
           </td>
           <td style="vertical-align:middle">
           		<input type="hidden" name="desc" id="desc" value="<%=genero.getDescripcion()%>"/><%=genero.getDescripcion()%>
           </td>
           <td style="vertical-olign:middle">
           		<input type="checkbox" readonly disabled <%if(genero.isHabilitado()){ %> checked <%} %>><td>
           </td>	
           <form role="form" action="srvGenero" method="post" id="botonera" name="botonera">
           		<td style="vertical-align:middle">
           			<input type="hidden" name="descSelect" id="descSelect" value="<%=genero.getDescripcion()%>"/>
           			<input class="btn btn-success btn-sm" type="submit" value="Modificar" id="eventUpdate" name="eventUpdate" />
  					<input class="btn btn-danger btn-sm" type="submit" value="Eliminar" id="eventDelete" name="eventDelete" />
           		</td>
           </form>
         </tr>
         <% i++;} %>
       </tbody>
     </table>




</div>


</body>
<%
	} else { response.sendRedirect("itemUser.jsp");}
%>
</html>