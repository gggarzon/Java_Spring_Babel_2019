package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Cuenta;

@Repository("cuentasDao")
public class CuentasDaoImpl implements CuentasDao {

	@Autowired
	JdbcTemplate template;
	@Override
	public Cuenta obtenerCuenta(int numeroCuenta) {
		String sql = "SELECT * FROM cuentas WHERE numeroCuenta=?";
		List <Cuenta> cuentas = template.query(sql, (rs,f)-> rs.isAfterLast()?null:new Cuenta(rs.getInt("numeroCuenta"),rs.getDouble("saldo"),rs.getString("tipocuenta")),numeroCuenta);
		return cuentas.size()>0?cuentas.get(0):null;
	}

	@Override
	public void actualizarCuenta(Cuenta cuenta) {
		String sql = "UPDATE cuentas set saldo=? where numeroCuenta=?";
		template.update(sql,cuenta.getSaldo(),cuenta.getNumeroCuenta());
	}

}
