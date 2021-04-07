package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("option");
		String url = "inicio.html";
		switch (option) {
		case "doValidar":
			request.getRequestDispatcher("ValidarAction").include(request, response);
			// para recuperar la lista de temas y pasarla a la .jsp
			url = (Boolean) request.getAttribute("resultado") ? "operacion.jsp" : "error.html";
			break;
			
		case "toIngresar":
			url="ingreso.html";
			break;
			
		case "doIngresar":
				request.getRequestDispatcher("IngresarAction").include(request, response);
				url="operacion.jsp";
			break;

		case "toExtraer":
			url="extraccion.html";
			break;
		
		case "doExtraer":
			request.getRequestDispatcher("ExtraerAction").include(request, response);
			url="operacion.jsp";
			break;
			
		case "toTransferir":
			url="transferencia.html";
			break;	
			
		case "doTransferir":
			request.getRequestDispatcher("TransferirAction").include(request, response);
			url="operacion.jsp";
			break;

		case "doMovimientos":
			request.getRequestDispatcher("MovimientosAction").include(request, response);
			url="movimientos.jsp";
			break;
			
		case "toInicio":
			url="inicio.html";
			break;
		}
		request.getRequestDispatcher(url).forward(request, response);

	}
}
