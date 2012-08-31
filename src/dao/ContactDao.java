package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Contact;

public interface ContactDao extends Remote {
	
	public void add(Contact contact) throws RemoteException;
	
	public void delete(String idContact) throws RemoteException;
	
	public void update(Contact contact) throws RemoteException;
	
	public ArrayList find(String idUser) throws RemoteException;

}
