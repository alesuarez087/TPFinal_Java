package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import entidades.Entidad.States;
import entidades.Usuario.TiposUsuario;
import utils.*;


public class DataUsuario {

	public static ArrayList<Usuario> GetAll(){
		ArrayList<Usuario> list = new ArrayList<Usuario>();
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosGetAll };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setClave(rs.getString("clave"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setHabilitado(rs.getBoolean("habilitado"));
				usuario.setDni(rs.getString("dni"));
				
				switch(rs.getInt("id_tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Empleado); break;
					case 3: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
				
				list.add(usuario);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return list;
	}
	
	public static Usuario GetOne(String userName){
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosGetOne(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setClave(rs.getString("clave"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setHabilitado(rs.getBoolean("habilitado"));
				usuario.setDni(rs.getString("dni"));
				
				switch(rs.getInt("id_tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Empleado); break;
					case 3: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	public static Usuario GetOne(int idUsuario){
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosGetOneForId(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setClave(rs.getString("clave"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setHabilitado(rs.getBoolean("habilitado"));
				usuario.setDni(rs.getString("dni"));
				
				switch(rs.getInt("id_tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Empleado); break;
					case 3: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}
	
	public static Usuario Login(String user, String pass){
		Usuario usuario = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosLogin(?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setClave(rs.getString("clave"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setHabilitado(rs.getBoolean("habilitado"));
				usuario.setDni(rs.getString("dni"));
				
				switch(rs.getInt("id_tipo_usuario")){
					case 1: usuario.setTipoUsuario(TiposUsuario.Administrador); break;
					case 2: usuario.setTipoUsuario(TiposUsuario.Empleado); break;
					case 3: usuario.setTipoUsuario(TiposUsuario.Usuario); break;
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return usuario;
	}

	public static void Save(Usuario user){
		if(user.getState()==States.Alta) Insert(user);
		else if(user.getState()==States.Baja) Delete(user);
		else if(user.getState()==States.Modificacion) Update(user);
		else user.setState(States.Consulta);
	}

	private static void Insert(Usuario user){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosInsert(?, ?, ?, ?, ?, ?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getApellido());
			stmt.setString(2, user.getClave());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getNombre());
			stmt.setString(5, user.getNombreUsuario());
			
			switch(user.getTipoUsuario()){
				case Administrador: stmt.setInt(6, 1); break;
				case Empleado: stmt.setInt(6, 2); break;
				case Usuario: stmt.setInt(6, 3); break;
			}
			
			stmt.setBoolean(7, user.isHabilitado());
			stmt.setString(8, user.getDni());
			rs = stmt.executeQuery();
			
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}

	private static void Update(Usuario user){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosUpdate(?, ?, ?, ?, ?, ?, ?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getApellido());
			stmt.setString(2, user.getClave());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getNombre());
			stmt.setString(5, user.getNombreUsuario());
			
			switch(user.getTipoUsuario()){
				case Administrador: stmt.setInt(6, 1); break;
				case Empleado: stmt.setInt(6, 2); break;
				case Usuario: stmt.setInt(6, 3); break;
			}
			
			stmt.setBoolean(7, user.isHabilitado());
			stmt.setString(8, user.getDni());
			stmt.setInt(9, user.getId());
			rs = stmt.executeQuery();
			
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}
	
	private static void Delete(Usuario user){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosDelete(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			rs = stmt.executeQuery();
			
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}

}
