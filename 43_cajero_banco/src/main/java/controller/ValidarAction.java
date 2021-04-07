package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cuenta;
import service.BancaService;
import service.BancaServiceFactory;

/**
 * Servlet implementation class ValidarAction
 */
@WebServlet("/ValidarAction")
public class ValidarAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BancaService service = BancaServiceFactory.getBancaService();
		Cuenta cuenta = service.validarCuenta(Integer.parseInt(request.getParameter("numeroCuenta")));
		if (cuenta != null) {
			request.getSession().setAttribute("cuenta", cuenta);
			request.getSession().setAttribute("numeroCuenta", cuenta.getNumeroCuenta());
			request.setAttribute("resultado", true);
		} else {
			request.setAttribute("resultado", false);
		}
	}
}
