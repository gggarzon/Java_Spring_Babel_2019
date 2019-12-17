package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Contacto;

//indicamos que es clase service y le damos un nombre (opcional)

@Service("agendaService")
public class AgendaServicejdbcImpl implements AgendaService {
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public boolean agregarContacto(Contacto c) {
		String sql = "INSERT INTO contactos (nombre,email,edad) VALUES (?,?,?)";
		int cantidad = template.update(sql,c.getNombre(),c.getEmail(),c.getEdad());
		return cantidad>0?true:false;
	}

	@Override
	public Contacto buscarContacto(String email) {
		String sql = "SELECT * FROM contactos WHERE email=?";
		List <Contacto> contactos = template.query(sql, (rs,f)-> rs.isAfterLast()?null:new Contacto(rs.getString("nombre"),rs.getString("email"),rs.getInt("edad")),email);
		return contactos.size()>0?contactos.get(0):null;
	}

	@Override
	public void eliminarContacto(String email) {
		String sql = "DELETE FROM contactos WHERE email=?";
		template.update(sql);
	}
	@Override
	public List<Contacto> devolverContactos() {
		String sql = "SELECT * FROM contactos";
		return template.query(sql, (rs,f)-> new Contacto(rs.getString("nombre"),rs.getString("email"),rs.getInt("edad")));
		
}
}