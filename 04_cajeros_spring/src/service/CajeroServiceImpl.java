package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CuentasDao;
import dao.MovimientosDao;
import model.Cuenta;
import model.Movimiento;

@Service("cajeroService")
public class CajeroServiceImpl implements CajeroService {
	
	@Autowired
	CuentasDao g ;
	
	@Autowired
	MovimientosDao m ;
	
	@Override
	public Cuenta buscarCuenta(int numeroCuenta) {
		return g.obtenerCuenta(numeroCuenta);
	}

	@Override
	public double obtenerSaldo(int numeroCuenta) {
		/* 
		 * */
		return g.obtenerCuenta(numeroCuenta).getSaldo();
	}

	@Override
	public List<Movimiento> obtenerMovimientos(int numeroCuenta) {
		return m.listarMovimientos(numeroCuenta);
	}

	@Override
	public void ingresar(int numeroCuenta, double cantidad) {
		Cuenta cuenta = buscarCuenta(numeroCuenta);
		cuenta.setSaldo(cuenta.getSaldo()+cantidad);
		g.actualizarCuenta(cuenta);
		Date date = new Date();
		m.crearMovimiento(new Movimiento(1,cantidad,numeroCuenta,date,"ingreso"));
	}

	@Override
	public void extraer(int numeroCuenta, double cantidad) {
		Cuenta cuenta = buscarCuenta(numeroCuenta);
		cuenta.setSaldo(cuenta.getSaldo()-cantidad);
		g.actualizarCuenta(cuenta);
		Date date = new Date();
		m.crearMovimiento(new Movimiento(1,cantidad,numeroCuenta,date,"extraccion"));
	}

}
