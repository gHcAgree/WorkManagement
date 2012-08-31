package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Note;

public interface NoteDao extends Remote {
	
	public void add(Note note)  throws RemoteException;
	
	public void delete(String idNote) throws RemoteException;
	
	public void update(Note note) throws RemoteException;
	
	public ArrayList findAll(String idUser) throws RemoteException;
	
	public Note find(String idNote) throws RemoteException;
}
