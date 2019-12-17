package dao;

import java.util.List;

import model.Movimiento;

public interface MovimientosDao {
	
	public List <Movimiento> listarMovimientos (int idCuenta);
	
	public void crearMovimiento (Movimiento movimiento);

}
