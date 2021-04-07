package service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import model.Cuenta;
import model.Movimiento;

// Inyeccion de dependencias Cdi
@Named("bancaService")	// Es instanciable
@RequestScoped			// Ambito de peticion

public class BancaServiceImpl implements BancaService {

	@Override
	public Cuenta validarCuenta(Integer numeroCuenta) {

		try (Connection con = DatosLocator.getConnection()) {
			String sql = "select * from cuentas where numeroCuenta=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, numeroCuenta);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return new Cuenta(rs.getInt("numeroCuenta"), rs.getDouble("saldo"), rs.getString("tipocuenta"));
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Cuenta ingresar(Integer numeroCuenta, Double cantidad) {
		Cuenta cuenta = this.validarCuenta(numeroCuenta);
		if (validarCuenta(cuenta.getNumeroCuenta()) != null) {
			Double saldoFinal = cuenta.getSaldo() + cantidad;
			try (Connection con = DatosLocator.getConnection()) {
				con.setAutoCommit(false);

				// Preparar el incremento de saldo
				String sqlSetSaldo = "UPDATE cuentas SET saldo=? WHERE numeroCuenta=?";
				PreparedStatement ps = con.prepareStatement(sqlSetSaldo);
				ps.setDouble(1, saldoFinal);
				ps.setInt(2, numeroCuenta);

				// Preparar la anotacion de movimiento
				String sqlMovimiento = "INSERT INTO movimientos (idCuenta, fecha, cantidad, operacion) VALUES (?,?,?,?)";
				PreparedStatement ps2 = con.prepareStatement(sqlMovimiento);
				ps2.setInt(1, numeroCuenta);
				ps2.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
				ps2.setDouble(3, cantidad);
				ps2.setString(4, "ingreso");

				// Ejecutar las query
				ps.execute();
				ps2.execute();
				con.commit();
				return validarCuenta(cuenta.getNumeroCuenta());

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public Cuenta extraer(Integer numeroCuenta, Double cantidad) {
		Cuenta cuenta = this.validarCuenta(numeroCuenta);
		if (validarCuenta(cuenta.getNumeroCuenta()) != null && cuenta.getSaldo() > cantidad) {
			Double saldoFinal = cuenta.getSaldo() - cantidad;
			try (Connection con = DatosLocator.getConnection()) {
				con.setAutoCommit(false);

				// Preparar el decremento de saldo
				String sqlSetSaldo = "UPDATE cuentas SET saldo=? WHERE numeroCuenta=?";
				PreparedStatement ps = con.prepareStatement(sqlSetSaldo);
				ps.setDouble(1, saldoFinal);
				ps.setInt(2, numeroCuenta);

				// Preparar la anotacion de movimiento
				String sqlMovimiento = "INSERT INTO movimientos (idCuenta, fecha, cantidad, operacion) VALUES (?,?,?,?)";
				PreparedStatement ps2 = con.prepareStatement(sqlMovimiento);
				ps2.setInt(1, numeroCuenta);
				ps2.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
				ps2.setDouble(3, cantidad);
				ps2.setString(4, "extraccion");

				// Ejecutar las query
				ps.execute();
				ps2.execute();
				con.commit();
				return validarCuenta(cuenta.getNumeroCuenta());

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * Transferencia de operaciones Creamos este metodo para asegurar la operacion
	 * de transferencia en un solo commit
	 */
	@Override
	public Cuenta transferir(Integer numeroCuentaAdeudo, Integer numeroCuentaAbono, Double cantidad) {
		Cuenta cuentaAdeudo = this.validarCuenta(numeroCuentaAdeudo);
		Cuenta cuentaAbono = this.validarCuenta(numeroCuentaAbono);
		if (validarCuenta(cuentaAdeudo.getNumeroCuenta()) != null && (cuentaAdeudo.getSaldo() > cantidad)
				&& validarCuenta(cuentaAbono.getNumeroCuenta()) != null) {
			Double saldoFinalAdeudo = cuentaAdeudo.getSaldo() - cantidad;
			Double saldoFinalAbono = cuentaAbono.getSaldo() + cantidad;

			try (Connection con = DatosLocator.getConnection()) {
				con.setAutoCommit(false);

				// Preparar el decremento de saldo
				String sqlSetSaldo = "UPDATE cuentas SET saldo=? WHERE numeroCuenta=?";
				PreparedStatement psAdeudo = con.prepareStatement(sqlSetSaldo);
				psAdeudo.setDouble(1, saldoFinalAdeudo);
				psAdeudo.setInt(2, cuentaAdeudo.getNumeroCuenta());

				// Preparar el abono
				String sqlSetSaldoAbono = "UPDATE cuentas SET saldo=? WHERE numeroCuenta=?";
				PreparedStatement psAbono = con.prepareStatement(sqlSetSaldoAbono);
				psAbono.setDouble(1, saldoFinalAbono);
				psAbono.setInt(2, numeroCuentaAbono);

				// Preparar la anotacion de movimientos
				LocalDateTime fechaHora = LocalDateTime.now();
				// Anotacion del decremento
				String sqlMovimiento = "INSERT INTO movimientos (idCuenta, fecha, cantidad, operacion) VALUES (?,?,?,?)";
				PreparedStatement psMovAdeudo = con.prepareStatement(sqlMovimiento);
				psMovAdeudo.setInt(1, cuentaAdeudo.getNumeroCuenta());
				psMovAdeudo.setTimestamp(2, java.sql.Timestamp.valueOf(fechaHora));
				psMovAdeudo.setDouble(3, cantidad);
				psMovAdeudo.setString(4, "-transferencia");

				// Anotacion del incremento
				PreparedStatement psMovAbono = con.prepareStatement(sqlMovimiento);
				psMovAbono.setInt(1, cuentaAbono.getNumeroCuenta());
				psMovAbono.setTimestamp(2, java.sql.Timestamp.valueOf(fechaHora));
				psMovAbono.setDouble(3, cantidad);
				psMovAbono.setString(4, "+transferencia");

				// Ejecutar las query
				psAdeudo.execute();
				psAbono.execute();
				psMovAdeudo.execute();
				psMovAbono.execute();
				con.commit();
				// Devolver la cuenta completa de la trasnferencia
				return cuentaAdeudo;

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public List<Movimiento> mostrarMov(Integer numeroCuenta) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		Cuenta cuenta = this.validarCuenta(numeroCuenta);
		if (validarCuenta(cuenta.getNumeroCuenta()) != null) {
			try (Connection con = DatosLocator.getConnection()) {
				String sql = "select * from movimientos WHERE idCuenta=?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, cuenta.getNumeroCuenta());
				 ResultSet res=ps.executeQuery();
				while (res.next()) {
					movimientos.add(new Movimiento(res.getInt(1), res.getInt(2), res.getTimestamp(3).toLocalDateTime(),
							res.getDouble(4), res.getString(5)));
				}
				return movimientos;
			} catch (SQLException ex) {
				ex.printStackTrace();

			}
		}
		return null;
	}

}
