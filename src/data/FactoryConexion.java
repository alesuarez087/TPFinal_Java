package data;

import java.sql.*;
import utils.*;

public class FactoryConexion {

		//private String host="localhost";
		//private String user="java";
		//private String pass="java";
		private String port="3306";
		private String db="java_tpfinal";
		private String dbType="mysql";
		private String user="root";
		private String pass="TZThgi64119";
		private String host= "mysql27947-luzbelito.jl.serv.net.mx";
		private Connection conn;
		private int cantConn=0;
		
		private FactoryConexion() throws ApplicationException{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new ApplicationException("Error del Driver JDBC",e);
			}
		}
		
		private static FactoryConexion instancia;
		
		public static FactoryConexion getInstancia() throws ApplicationException{
			if (instancia==null){
				instancia = new FactoryConexion();
			}
			return instancia;
		}
		
		public Connection getConn(){
			try {
				if(conn==null || conn.isClosed()){
					conn = DriverManager.getConnection("jdbc:"+dbType+"://"+host+":"+port+"/"+db, user, pass);
					cantConn++;
				}
			} catch (SQLException e) {
				new ApplicationException("Error al conectar a la DB",e);

			}
			return conn;
		}
		
		public void releaseConn() throws ApplicationException{
			try {
				cantConn--;
				if(cantConn==0){
					conn.close();
				}
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexi�n",e);
			}
			
		}
}
