package controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movimiento;
import service.BancaService;
import service.BancaServiceFactory;

/**
 * Servlet implementation class MovimientosAction
 */
@WebServlet("/MovimientosAction")
public class MovimientosAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	BancaService bancaService;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer numeroCuenta=(Integer)request.getSession().getAttribute("numeroCuenta");
//		BancaService bancaService=BancaServiceFactory.getBancaService();    // No necesario por la inyeccion de dependencias
		List<Movimiento> movimientos = bancaService.mostrarMov(numeroCuenta);
		request.setAttribute("movimientos", movimientos);
		
	}

}
