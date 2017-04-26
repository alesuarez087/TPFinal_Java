package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import utils.*;

public class DataVenta {

	public static ArrayList<Venta> GetAll(){
		ArrayList<Venta> list = new ArrayList<Venta>();
		Venta venta = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentasGetAll };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				venta = new Venta();
				venta.setId(rs.getInt("id_venta"));
				venta.setHabilitado(rs.getBoolean("habilitado"));
				venta.setIdUsuario(rs.getInt("id_usuario"));
				venta.setNroTarjeta(rs.getString("nro_tarjeta"));
				venta.setTitularTarjeta(rs.getString("titular_tarjeta"));
				venta.setFecha(rs.getDate("fecha"));
				
				list.add(venta);
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

	public static ArrayList<Venta> VentasGetAllForUser(int idUsuario){
		ArrayList<Venta> list = new ArrayList<Venta>();
		Venta venta = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentasGetAllForUser(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				venta = new Venta();
				venta.setId(rs.getInt("id_venta"));
				venta.setHabilitado(rs.getBoolean("habilitado"));
				venta.setIdUsuario(rs.getInt("id_usuario"));
				venta.setNroTarjeta(rs.getString("nro_tarjeta"));
				venta.setTitularTarjeta(rs.getString("titular_tarjeta"));
				venta.setFecha(rs.getDate("fecha"));
				
				list.add(venta);
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
	
	public static Venta GetOne(int idVenta){
		Venta venta = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentasGetOne(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idVenta);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				venta = new Venta();
				venta.setId(rs.getInt("id_venta"));
				venta.setHabilitado(rs.getBoolean("habilitado"));
				venta.setIdUsuario(rs.getInt("id_usuario"));
				venta.setNroTarjeta(rs.getString("nro_tarjeta"));
				venta.setTitularTarjeta(rs.getString("titular_tarjeta"));
				venta.setFecha(rs.getDate("fecha"));
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
		return venta;
	}
	
	public static void Insert(Venta venta){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentasInsert(?, ?, ?, ?, ?, ?, ?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, venta.getIdUsuario());
			stmt.setString(2, venta.getNroTarjeta());
			stmt.setString(3, venta.getTitularTarjeta());
			stmt.setInt(4, venta.getIdProvincia());
			stmt.setString(5, venta.getLocalidad());
			stmt.setString(6, venta.getCalle());
			stmt.setString(7, venta.getNroCalle());
			stmt.setString(8, venta.getPiso());
			stmt.setString(9, venta.getNroDpto());
			
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
	
	public static int ultimaVenta(){
		ResultSet rs = null; PreparedStatement stmt = null; int ret = 0;
		String sql="SELECT MAX(id_venta) FROM ventas;";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null) ret = rs.getInt(1);
			
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
}
