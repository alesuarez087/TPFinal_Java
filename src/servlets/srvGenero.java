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
import utils.Validate;

/**
 * Servlet implementation class srvGenero
 */
@WebServlet("/srvGenero")
public class srvGenero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Controlador ctrl = new Controlador();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvGenero() {
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

		Genero genero = new Genero();
		
		
		if(request.getParameter("newGenero")!=null){
			if(request.getSession().getAttribute("FormSession") == null){
				genero.setState(States.Alta);
			} else { 
				genero = ctrl.getOneGenero(Integer.parseInt(request.getParameter("idGenero")));
				genero.setState(States.Modificacion); 
				request.getSession().setAttribute("FormSession", null);
			}
			genero.setDescripcion(request.getParameter("descGenero"));
			if(request.getParameter("habilitado")==null) genero.setHabilitado(false);
			else genero.setHabilitado(true);
			if(genero.getState()==States.Alta){
				if(Validate.Descripcion(request.getParameter("descGenero"))){
					ctrl.save(genero);
				} else{
					request.setAttribute("messageError", "Ya existe ese Género");
				}
			} else ctrl.save(genero);
			
			request.getRequestDispatcher("adminGenero.jsp").forward(request, response);
			
				
		}
		
		if(request.getParameter("eventUpdate")!=null){
			genero = ctrl.getOneGenero(request.getParameter("descSelect"));
			request.setAttribute("idGenero", genero.getId());
			request.setAttribute("descGenero", genero.getDescripcion());
			if(genero.isHabilitado()) request.setAttribute("habilitado", true);
				else request.setAttribute("habilitado", null);
			request.getSession().setAttribute("FormSession", "Modificacion");
			request.getRequestDispatcher("genero.jsp").forward(request, response);
		}
		
		if(request.getParameter("clearForm")!=null){
			request.setAttribute("idGenero", "");
			request.setAttribute("descGenero", "");
			request.setAttribute("habilitado", null);
			request.getSession().setAttribute("FormSession", null);
			request.getRequestDispatcher("genero.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventDelete")!=null){
			Genero gen = ctrl.getOneGenero(request.getParameter("descSelect"));
			gen.setState(States.Baja);
			ctrl.save(gen);
			request.getRequestDispatcher("adminGenero.jsp").forward(request, response);
		}
	}

}
