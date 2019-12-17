package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Contacto;
//indicamos que es clase @Service y le damos un nombre (opcional)

@Service("agendaService")
public class AgendaServicejdbcImpl implements AgendaService {

	@Autowired
	private DataSource dataSource;
	@Override
	public boolean agregarContacto(Contacto c) {
		String sql = "INSERT INTO contactos (nombre,email,edad) VALUES (?,?,?)";
		boolean exito = false;
		try (Connection con = dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			pst.setString(1,c.getNombre());
			pst.setString(2,c.getEmail());
			pst.setInt(3,c.getEdad());
			pst.execute();
			exito = true;
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		return exito;
	}

	@Override
	public Contacto buscarContacto(String email) {
		Contacto c= new Contacto(" ", " ", 0);
		Boolean exito=false;
		String sql = "SELECT * FROM contactos WHERE email=?";
		try (Connection con = dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)){
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				c.setNombre(rs.getString("nombre"));
				c.setEmail(rs.getString("email"));
				c.setEdad(rs.getInt("edad"));
				exito = true;
			}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		if (exito) {
			return c;
		}
		else {
			return null;
		}
		

	}

	@Override
	public void eliminarContacto(String email) {
		String sql = "DELETE FROM contactos WHERE email=?";
		try (Connection con = dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)){
			pst.setString(1, email);
			pst.execute();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
	}
	@Override
	public List<Contacto> devolverContactos() {
		List <Contacto> contactos = new ArrayList<>();
		String sql = "SELECT * FROM contactos";
		Boolean exito=false;
		try (Connection con = dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)){
			ResultSet rs = pst.executeQuery(sql);
			while (rs.next()) {
				Contacto c= new Contacto(rs.getString("nombre"),rs.getString("email"), rs.getInt("edad"));
				contactos.add(c);
			}
			exito = true;
			}
			
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		if (exito) {
			return contactos;
		}
		else {
			return null;
		}
}
}