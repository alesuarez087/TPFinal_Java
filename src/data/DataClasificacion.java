package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import entidades.Entidad.States;
import utils.*;

public class DataClasificacion {

	public static ArrayList<Clasificacion> GetAll(int idItem){
		ArrayList<Clasificacion> list = new ArrayList<Clasificacion>();
		Clasificacion clas = null;
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ClasificacionesGetAll(?) }";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idItem);
			rs = stmt.executeQuery();
			while(rs.next()){
				clas = new Clasificacion();
				clas.setId(rs.getInt("id_clasificacion"));
				clas.setIdItem(rs.getInt("id_item"));
				clas.setIdUsuario(rs.getInt("id_usuario"));
				clas.setValor(rs.getInt("puntos"));
				clas.setDetalles(rs.getString("mensaje_adjunto"));
				
				list.add(clas);
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

	public static Clasificacion GetOne(int idItem, int idUsuario){
		Clasificacion clas = null;
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ClasificacionesGetOne(?, ?) }";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idItem);
			stmt.setInt(2, idUsuario);
			rs = stmt.executeQuery();
			while(rs.next()){
				clas = new Clasificacion();
				clas.setId(rs.getInt("id_clasificacion"));
				clas.setIdItem(rs.getInt("id_item"));
				clas.setIdUsuario(rs.getInt("id_usuario"));
				clas.setValor(rs.getInt("puntos"));
				clas.setDetalles(rs.getString("mensaje_adjunto"));
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
		return clas;
	}
	
	public static double GetPromedio(int idItem){
		double ret = 0;
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ClasificacionesPromedio(?) }";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idItem);
			rs = stmt.executeQuery();
			while(rs.next()){
				ret = rs.getFloat(1);
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
		return ret;
	}
	
	public static void Save(Clasificacion clas){
		if(clas.getState() == States.Alta) Insert(clas);
		else if (clas.getState() == States.Modificacion) Update(clas);
		else clas.setState(States.Consulta);
	}

	private static void Update(Clasificacion clas){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ClasificacionesUpdate(?, ?, ?, ?, ?) }";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, clas.getIdItem());
			stmt.setInt(2, clas.getIdUsuario());
			stmt.setInt(3, clas.getValor());
			stmt.setString(4, clas.getDetalles());
			stmt.setInt(5, clas.getId());
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
	
	private static void Insert(Clasificacion clas){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ClasificacionesInsert(?, ?, ?, ?) }";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, clas.getIdItem());
			stmt.setInt(2, clas.getIdUsuario());
			stmt.setInt(3, clas.getValor());
			stmt.setString(4, clas.getDetalles());
			
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
