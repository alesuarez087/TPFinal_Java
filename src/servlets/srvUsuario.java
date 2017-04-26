package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.*;
import entidades.*;
import entidades.Entidad.States;
import entidades.Usuario.TiposUsuario;

/**
 * Servlet implementation class srvUsuario
 */
@WebServlet("/srvUsuario")
public class srvUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controlador ctrl = new Controlador();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usr;
		
		if(request.getParameter("saveUser")!=null){
			if(request.getSession().getAttribute("FormSession")==null){
				usr = new Usuario();
				usr.setState(States.Alta);
			} else{
				request.getSession().setAttribute("FormSession", null);
				usr = ctrl.getOneUsuario(Integer.parseInt(request.getParameter("idUsuario")));
				usr.setState(States.Modificacion);
			}		
					
			usr.setApellido(request.getParameter("apellido"));
			usr.setNombre(request.getParameter("nombre"));
			usr.setClave(request.getParameter("clave"));
			usr.setDni(request.getParameter("dni"));
			usr.setEmail(request.getParameter("email"));
			usr.setNombreUsuario(request.getParameter("nombreUsuario"));
			usr.setClave(request.getParameter("clave"));
		
			if(request.getParameter("habilitado")==null) usr.setHabilitado(false);
				else usr.setHabilitado(true);
		
			switch(request.getParameter("cmbTipo")){
				case "Administrador": usr.setTipoUsuario(TiposUsuario.Administrador); break;
				case "Empleado": usr.setTipoUsuario(TiposUsuario.Empleado); break;
				case "Usuario": usr.setTipoUsuario(TiposUsuario.Usuario); break;
			}
		
			ctrl.save(usr);
			request.getRequestDispatcher("adminUsuario.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventUpdate")!=null){
			usr = ctrl.getOneUsuario(Integer.parseInt(request.getParameter("idSelect")));
			
			request.setAttribute("idUsuario", usr.getId());
			request.setAttribute("nombre", usr.getNombre());
			request.setAttribute("apellido", usr.getApellido());
			request.setAttribute("dni", usr.getDni());
			request.setAttribute("email", usr.getEmail());
			request.setAttribute("nombreUsuario", usr.getNombreUsuario());
			request.setAttribute("clave", usr.getClave());
			request.setAttribute("tipo", usr.getTipoUsuario());
			
			if(usr.isHabilitado()) request.setAttribute("habilitado", true);
			else request.setAttribute("habilitado", null);
			
			request.getSession().setAttribute("FormSession", "Modificacion");
			request.getRequestDispatcher("adminUsuario.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventDelete")!=null){
			usr = ctrl.getOneUsuario(Integer.parseInt(request.getParameter("idSelect")));
			if(usr!=null){
				usr.setState(States.Baja);
				ctrl.save(usr);
			}
			request.getSession().setAttribute("FormSession", null);
			request.getRequestDispatcher("adminUsuario.jsp").forward(request, response);
		}
		
		if(request.getParameter("clearForm")!=null){
			request.setAttribute("idUsuario", null);
			request.setAttribute("nombre", null);
			request.setAttribute("apellido", null);
			request.setAttribute("dni", null);
			request.setAttribute("email", null);
			request.setAttribute("nombreUsuario", null);
			request.setAttribute("clave", null);
			request.setAttribute("habilitado", false);
			request.setAttribute("tipo", null);
			
			request.getSession().setAttribute("FormSession", null);
			request.getRequestDispatcher("adminUsuario.jsp").forward(request, response);
		}
	}

}
