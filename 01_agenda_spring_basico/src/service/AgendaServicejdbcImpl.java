package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Contacto;
//indicamos que es clase @Service y le damos un nombre (opcional)
@Service("agendaService")
public class AgendaServicejdbcImpl implements AgendaService {
	private static final String driver = "com.mysql.jdbc.Driver";
	//jdbc:mysql//servidor:puerto/basedatos
	private static final String url = "jdbc:mysql://localhost:3306/agenda2";
	private static final String user = "root";
	private static final String pwd = "root";
	static { //esto solo se ejecuta una vez en la vida de la clase
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public boolean agregarContacto(Contacto c) {
		String sql = "INSERT INTO contactos (nombre,email,edad) VALUES (?,?,?)";
		boolean exito = false;
		try (Connection con = DriverManager.getConnection(url, user,pwd)){
			PreparedStatement pst = con.prepareStatement(sql);
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
		try (Connection con = DriverManager.getConnection(url, user,pwd)){
			String sql = "SELECT * FROM contactos WHERE email=?";
			PreparedStatement pst = con.prepareStatement(sql);
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
		try (Connection con = DriverManager.getConnection(url, user,pwd)){
			String sql = "DELETE FROM contactos WHERE email=?";
			PreparedStatement pst = con.prepareStatement(sql);
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
		try (Connection con = DriverManager.getConnection(url, user,pwd)){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
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