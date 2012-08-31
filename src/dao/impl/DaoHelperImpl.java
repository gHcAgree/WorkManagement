package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoHelperImpl implements dao.DaoHelper {

	private Connection connection = null;
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/wms";
		String user = "root";
		String password = "ghc@mysql";
		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(connection!=null)
			System.out.println("Successfully connected to database...");
		
		return connection;
	}

	@Override
	public void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void closePreparedStatement(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		if(stmt!=null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void closeResultSet(ResultSet result) {
		// TODO Auto-generated method stub
		if(result!=null)
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	public static void main(String[] args) {
		new DaoHelperImpl().getConnection();
	}
	*/
	
}
