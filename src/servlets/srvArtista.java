package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.*;
import entidades.Entidad.States;
import utils.Validate;
import controlador.*;

/**
 * Servlet implementation class srvArtista
 */
@WebServlet("/srvArtista")
public class srvArtista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controlador ctrl = new Controlador();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvArtista() {
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

		Artista artista;
		
		
		if(request.getParameter("newArtista")!=null){
			if(request.getParameter("idArtista").equals("")){
				artista = new Artista();
				artista.setState(States.Alta);
			} else { 
				artista = ctrl.getOneArtista(Integer.parseInt(request.getParameter("idArtista")));
				artista.setState(States.Modificacion); 
				request.getSession().setAttribute("FormSession", null);
			}
			artista.setNombre(request.getParameter("descArtista"));
			artista.setHabilitado(true);
			
			if(artista.getState()==States.Alta){
				if(Validate.Artista(request.getParameter("descArtista"))){
					ctrl.save(artista);
				} else{
					request.setAttribute("messageError", "Ya existe ese Artista");
				}
			} else ctrl.save(artista);
			
			request.getRequestDispatcher("adminArtista.jsp").forward(request, response);
			
				
		}
		
		if(request.getParameter("eventUpdate")!=null){
			artista = ctrl.getOneArtista(request.getParameter("nombreSelect"));
			request.setAttribute("idArtista", artista.getId());
			request.setAttribute("descArtista", artista.getNombre());
			
			if(artista.isHabilitado()) request.setAttribute("habilitado", true);
			else request.setAttribute("habilitado", null);
			
			request.getSession().setAttribute("FormSession", "Modificacion");
			request.getRequestDispatcher("adminArtista.jsp").forward(request, response);
		}
		
		if(request.getParameter("clearForm")!=null){
			request.setAttribute("idArtista", "");
			request.setAttribute("descArtista", "");
			request.setAttribute("habilitado", null);
			request.getSession().setAttribute("FormSession", null);
			request.getRequestDispatcher("adminArtista.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventDelete")!=null){
			artista = ctrl.getOneArtista(request.getParameter("nombreSelect"));
			artista.setState(States.Baja);
			ctrl.save(artista);
			request.getRequestDispatcher("adminArtista.jsp").forward(request, response);
		}
	}
}
