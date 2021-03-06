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

/**
 * Servlet implementation class ValidarAction
 */
@WebServlet("/ValidarAction")
public class ValidarAction extends HttpServlet {
	@Inject
	BancaService bancaService;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cuenta cuenta = bancaService.validarCuenta(Integer.parseInt(request.getParameter("numeroCuenta")));
		if (cuenta != null) {
			request.getSession().setAttribute("cuenta", cuenta);
			request.getSession().setAttribute("numeroCuenta", cuenta.getNumeroCuenta());
			request.setAttribute("resultado", true);
		} else {
			request.setAttribute("resultado", false);
		}
	}
}
