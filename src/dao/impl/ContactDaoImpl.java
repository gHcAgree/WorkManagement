package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoHelper;

import model.Contact;

public class ContactDaoImpl extends UnicastRemoteObject implements dao.ContactDao {

	private DaoHelper daoHelper;
	
	public ContactDaoImpl(DaoHelper dh) throws RemoteException {
		super();
		daoHelper = dh;
	}
	
	@Override
	public void add(Contact contact) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_insert = "insert into Contact(idContact,type,name,sex,email,User_idUser) values(?,?,?,?,?,?)";
		
		try {
			stmt = connection.prepareStatement(sql_insert);
			
			stmt.setString(1,contact.getIdContact());
			stmt.setInt(2,contact.getType());
			stmt.setString(3,contact.getName());
			stmt.setInt(4,contact.getSex());
			stmt.setString(5,contact.getEmail());
			stmt.setString(6,contact.getOwner());
			
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
	public void update(Contact contact) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_update = "update Contact set type=?,name=?,sex=?,email=?,User_idUser=? where idContact=?";
		
		try {
			stmt = connection.prepareStatement(sql_update);
			
			stmt.setInt(1,contact.getType());
			stmt.setString(2,contact.getName());
			stmt.setInt(3,contact.getSex());
			stmt.setString(4,contact.getEmail());
			stmt.setString(5,contact.getOwner());
			stmt.setString(6,contact.getIdContact());
			
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
	public ArrayList find(String idUser) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		
		String sql_select="select * from Contact where (User_idUser=? or type=?)";
		ArrayList list=new ArrayList();
		
		try 
		{
			stmt=connection.prepareStatement(sql_select);
			stmt.setString(1,idUser);
			stmt.setInt(2,0);
			result=stmt.executeQuery();
			while(result.next()) {
				Contact contact = new Contact();
				contact.setIdContact(result.getString(1));
				contact.setType(result.getInt(2));
				contact.setName(result.getString(3));
				contact.setSex(result.getInt(4));
				contact.setEmail(result.getString(5));
				contact.setOwner(result.getString(6));

				list.add(contact);
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
	public void delete(String idContact) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_delete="delete from Contact where idContact=?";
		
		try
		{
			stmt=connection.prepareStatement(sql_delete);
			stmt.setString(1,idContact);
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

	/**
	public static void main(String[] args) {
		ContactDaoImpl c = new ContactDaoImpl();
		ArrayList l = c.find("001");
		System.out.println(((Contact)l.get(0)).getName());
	}
	*/
}
