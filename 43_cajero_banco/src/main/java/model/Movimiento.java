package model;

import java.time.LocalDateTime;

public class Movimiento {
	private int idMovimiento;
	private int cuenta;
	private LocalDateTime fecha;
	private double cantidad;
	private String operacion;
	
	public Movimiento() {
		super();
	}

	public Movimiento(int idMovimiento, int cuenta, LocalDateTime fecha, double cantidad, String operacion) {
		super();
		this.idMovimiento = idMovimiento;
		this.cuenta = cuenta;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.operacion = operacion;
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	

}
