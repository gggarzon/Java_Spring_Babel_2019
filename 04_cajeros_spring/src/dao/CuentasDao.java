package dao;

import model.Cuenta;

public interface CuentasDao {
	
	public Cuenta obtenerCuenta (int numeroCuenta);
	public void actualizarCuenta (Cuenta cuenta);
	
}
