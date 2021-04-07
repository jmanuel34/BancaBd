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
 * Servlet implementation class TransferirAction
 */
@WebServlet("/TransferirAction")
public class TransferirAction extends HttpServlet {
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
		var cuentaAbono = Integer.valueOf(request.getParameter("cuentaAbono"));
		Cuenta cuenta = bancaService.transferir(numeroCuenta, cuentaAbono, cantidad) ; 
		request.setAttribute("cuenta", cuenta  );
	}

}
