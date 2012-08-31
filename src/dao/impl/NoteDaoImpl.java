package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoHelper;

import model.Note;

public class NoteDaoImpl extends UnicastRemoteObject implements dao.NoteDao {
	
	private DaoHelper daoHelper;
	
	public NoteDaoImpl(DaoHelper dh) throws RemoteException {
		super();
		daoHelper = dh;
	}

	@Override
	public void add(Note note) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_insert = "insert into Note(idNote,title,content,User_idUser) values(?,?,?,?)";
		
		try {
			stmt = connection.prepareStatement(sql_insert);
			
			stmt.setString(1,note.getIdNote());
			stmt.setString(2,note.getTitle());
			stmt.setString(3,note.getContent());
			stmt.setString(4,note.getOwner());
			
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
	public void update(Note note) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_update = "update Note set title=?,content=?,User_idUser=? where idNote=?";
		
		try {
			stmt = connection.prepareStatement(sql_update);
			
			stmt.setString(1,note.getTitle());
			stmt.setString(2,note.getContent());
			stmt.setString(3,note.getOwner());
			stmt.setString(4,note.getIdNote());
			
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
	public ArrayList findAll(String idUser) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		
		String sql_select="select * from Note where User_idUser=?";
		ArrayList list=new ArrayList();
		
		try 
		{
			stmt=connection.prepareStatement(sql_select);
			stmt.setString(1,idUser);
			result=stmt.executeQuery();
			
			while(result.next()) {
				Note note = new Note();
				note.setIdNote(result.getString(1));
				note.setTitle(result.getString(2));
				note.setContent(result.getString(3));
				note.setOwner(result.getString(4));

				list.add(note);
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
	public Note find(String idNote) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String idNote) throws RemoteException {
		// TODO Auto-generated method stub
		Connection connection=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		String sql_delete="delete from Note where idNote=?";
		
		try
		{
			stmt=connection.prepareStatement(sql_delete);
			stmt.setString(1,idNote);
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

}
