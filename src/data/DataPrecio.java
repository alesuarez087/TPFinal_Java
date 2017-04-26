package data;

import java.sql.*;
import entidades.*;
import utils.*;

public class DataPrecio {

	public static Precio GetPrecioToday(int idItem){
		Precio precio = null;
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call PrecioGetToday(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idItem);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				precio = new Precio();
				precio.setIdItem(rs.getInt("id_item"));
				precio.setValor(rs.getDouble("monto"));
				precio.setVigenciaDesde(rs.getDate("vigencia_desde"));
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
		return precio;
	}
	
	public static Precio GetPrecioVenta(int idItem, int idVenta){
		Precio precio = null;
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call PrecioGetVenta(?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idItem);
			stmt.setInt(2, idVenta);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				precio = new Precio();
				precio.setIdItem(rs.getInt("id_item"));
				precio.setValor(rs.getDouble("monto"));
				precio.setVigenciaDesde(rs.getDate("vigencia_desde"));
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
		return precio;
	} 
	
	public static void Save(Precio precio){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call PrecioInsert(?, ?) }";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, precio.getIdItem());
			stmt.setDouble(2, precio.getValor());
			
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
