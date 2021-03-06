package controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cuenta;
import service.BancaService;
import service.BancaServiceFactory;

/**
 * Servlet implementation class IngresarAction
 */
@WebServlet("/IngresarAction")
public class IngresarAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	BancaService bancaService;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BancaService bancaService = BancaServiceFactory.getBancaService();
//		HttpSession s=request.getSession();
		Double cantidad=Double.parseDouble(request.getParameter("cantidad"));
		Integer numeroCuenta=(Integer)request.getSession().getAttribute("numeroCuenta");
		Cuenta cuenta = bancaService.ingresar(numeroCuenta, cantidad); 
		request.setAttribute("cuenta", cuenta  );
	}
}
// request.getSession().setAttribute("cliente", cliente);