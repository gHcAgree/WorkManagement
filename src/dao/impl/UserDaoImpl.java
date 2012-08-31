package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.DaoHelper;

import main.WMServer;
import model.User;

public class UserDaoImpl extends UnicastRemoteObject implements dao.UserDao {

	private DaoHelper daoHelper;
	private WMServer server;

	public UserDaoImpl(WMServer s,DaoHelper dh) throws RemoteException {
		super();
		server = s;
		daoHelper = dh;
	}
	
	@Override
	public void add(User user) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_insert = "insert into User(idUser,type,name,sex,email,password) values(?,?,?,?,?,?)";
		
		try {
			stmt = connection.prepareStatement(sql_insert);
			
			stmt.setString(1,user.getIdUser());
			stmt.setInt(2,user.getType());
			stmt.setString(3,user.getName());
			stmt.setInt(4,user.getSex());
			stmt.setString(5,user.getEmail());
			stmt.setString(6,user.getPassword());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
		}
		
	}

	@Override
	public void update(User user) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_update = "update User set type=?,name=?,sex=?,email=?,password=? where idUser=?";
		
		try {
			stmt = connection.prepareStatement(sql_update);
			
			stmt.setInt(1,user.getType());
			stmt.setString(2,user.getName());
			stmt.setInt(3,user.getSex());
			stmt.setString(4,user.getEmail());
			stmt.setString(5,user.getPassword());
			stmt.setString(6,user.getIdUser());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
		}
		
	}

	@Override
	public ArrayList find() throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		
		String sql_select="select * from User";
		ArrayList list=new ArrayList();
		
		try 
		{
			stmt=connection.prepareStatement(sql_select);
			result=stmt.executeQuery();
			while(result.next()) {
				User user=new User();
				user.setIdUser(result.getString(1));
				user.setType(result.getInt(2));
				user.setName(result.getString(3));
				user.setSex(result.getInt(4));
				user.setEmail(result.getString(5));
				user.setPassword(result.getString(6));

				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResultSet(result);
		}
		return list;
	}

	@Override
	public User find(String idUser) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		
		String sql_select="select * from User where idUser=?";
		
		try
		{
			stmt=connection.prepareStatement(sql_select);
			stmt.setString(1,idUser);
			result=stmt.executeQuery();
			if(result.next())
			{
				User user=new User();
				user.setIdUser(result.getString(1));
				user.setType(result.getInt(2));
				user.setName(result.getString(3));
				user.setSex(result.getInt(4));
				user.setEmail(result.getString(5));
				user.setPassword(result.getString(6));
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResultSet(result);
		}
		return null;
	}

	@Override
	public void delete(String idUser) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_delete="delete from User where idUser=?";
		
		try
		{
			stmt=connection.prepareStatement(sql_delete);
			stmt.setString(1,idUser);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(stmt);
		}
	}

	@Override
	public void notifyServer(User user) throws RemoteException {
		// TODO Auto-generated method stub
		server.updateUsers(user);
	}

	@Override
	public ArrayList getActiveUsers() throws RemoteException {
		// TODO Auto-generated method stub
		return server.getActiveUsers();
	}
	
	
}
