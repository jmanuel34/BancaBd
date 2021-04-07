package service;

import java.util.List;

import model.Cuenta;
import model.Movimiento;

public interface BancaService {
	Cuenta validarCuenta(Integer numeroCuenta);
	Cuenta ingresar(Integer numeroCuenta, Double cantidad);
	Cuenta extraer (Integer numeroCuenta, Double cantidad);
	Cuenta transferir(Integer numeroCuenta, Integer cuentaAbono, Double cantidad);
	List<Movimiento> mostrarMov(Integer numeroCuenta);

}
