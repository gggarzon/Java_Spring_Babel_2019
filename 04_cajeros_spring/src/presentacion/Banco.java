package presentacion;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Cuenta;
import model.Movimiento;
import service.CajeroService;
public class Banco {
	public static void main(String[] args) {
		mostrarMenu();
	}
	public static void mostrarMenu() {
		@SuppressWarnings("resource")
		BeanFactory factory = new ClassPathXmlApplicationContext("springConfig.xml");
		CajeroService gestor = factory.getBean(CajeroService.class);
		int r = 0;
		Scanner sc = new Scanner (System.in);
		int cuenta = pedirCuenta();
		if(gestor.buscarCuenta(cuenta)!=null) {
		do {
		System.out.println("1.- Obtener saldo");
		System.out.println("2.- Obtener movimientos");
		System.out.println("3.- Ingreso");
		System.out.println("4.- Extraccion");
		System.out.println("5.- Salir del menu");
		r = Integer.parseInt(sc.nextLine());
		switch(r) {
		case 1: 
			System.out.println("El saldo de esta cuenta es: "+gestor.obtenerSaldo(cuenta));
			break;
		case 2:
			mostrar(gestor.obtenerMovimientos(cuenta));
			break;
		case 3:
			gestor.ingresar(cuenta,pedirCantidad());
			break;
		case 4:
			gestor.extraer(cuenta, pedirCantidad());
			break;
		case 5:
			System.out.println("Saliendo del menu..");
			break;
		default:
			System.out.println("Ingresa una opcion valida");
			break;
		}
		} while (r!=5);
		}
		else {
			System.out.println("No existe dicha cuenta");
		}
	}
	public static int pedirCuenta() {
		Scanner sc = new Scanner (System.in);
		System.out.println("Introduce numero de cuenta:");
		int name = sc.nextInt();
		return name;
	}
	public static void mostrar(List <Movimiento> m) {
		m.stream().forEach(t->System.out.println("ID cuenta:"+t.getIdCuenta()+"FECHA: "+t.getFecha()+"| CANTIDAD: "+t.getCantidad()+"| OPERACION: "+t.getOperacion()));
	}
	public static double pedirCantidad() {
		Scanner sc = new Scanner (System.in);
		System.out.println("Introduce la cantidad:");
		return sc.nextDouble();
	}

}
