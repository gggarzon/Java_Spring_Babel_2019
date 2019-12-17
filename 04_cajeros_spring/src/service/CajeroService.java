package service;

import java.util.List;

import model.Cuenta;
import model.Movimiento;

public interface CajeroService {
	Cuenta buscarCuenta (int numeroCuenta);
	double obtenerSaldo (int numeroCuenta);
	List <Movimiento> obtenerMovimientos (int numeroCuenta);
	void ingresar (int numeroCuenta, double cantidad);
	void extraer (int numeroCuenta, double cantidad);
}
