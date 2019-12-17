package model;

import java.util.Date;

public class Movimiento {
	private int idMovimiento;
	double cantidad;
	private int idCuenta;
	private Date fecha;
	private String operacion;
	public Movimiento(int idMovimiento, double cantidad, int idCuenta, Date fecha, String operacion) {
		super();
		this.idMovimiento = idMovimiento;
		this.cantidad = cantidad;
		this.idCuenta = idCuenta;
		this.fecha = fecha;
		this.operacion = operacion;
	}
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
}
