package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import utils.*;

public class DataTipoUsuario {

	public static ArrayList<TipoUsuario> GetAll(){
		ArrayList<TipoUsuario> retorno = new ArrayList<TipoUsuario>();
		TipoUsuario fila = null; ResultSet rs=null ; PreparedStatement stmt = null; 
		String sql = "{ call TiposUsuarioGetAll };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs != null){
				fila = new TipoUsuario();
				fila.setId(rs.getInt("id_tipo_usuario"));
				fila.setDescripcion(rs.getString("desc_tipo_usuario"));
				fila.setHabilitado(rs.getBoolean("habilitado"));
				retorno.add(fila);
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
		return retorno;
	}
}
