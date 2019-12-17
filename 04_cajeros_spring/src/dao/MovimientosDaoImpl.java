package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Movimiento;

@Repository("movimientosDao")
public class MovimientosDaoImpl implements MovimientosDao {
	@Autowired
	JdbcTemplate template;
	@Override
	public List<Movimiento> listarMovimientos(int idCuenta) {
		String sql = "SELECT * FROM movimientos where idCuenta=?";
		return template.query(sql, (rs,f)-> new Movimiento(
				rs.getInt("idMovimiento"),
				rs.getDouble("cantidad"),
				rs.getInt("idCuenta")
				,rs.getDate("fecha"),
				rs.getString("operacion")),idCuenta);	
	}

	@Override
	public void crearMovimiento(Movimiento movimiento) {
		String sql = "insert into movimientos (idCuenta,cantidad,fecha,operacion) values (?,?,?,?)";
		template.update(sql,movimiento.getIdCuenta(),movimiento.getCantidad(),movimiento.getFecha(),movimiento.getOperacion());
	}

}
