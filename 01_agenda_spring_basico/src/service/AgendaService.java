package service;

import java.util.List;

import model.Contacto;

public interface AgendaService {

	boolean agregarContacto(Contacto c);

	Contacto buscarContacto(String email);

	void eliminarContacto(String email);

	List<Contacto> devolverContactos();

}