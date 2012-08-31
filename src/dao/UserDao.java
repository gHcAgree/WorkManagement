package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.User;

public interface UserDao extends Remote  {
	
	public void add(User user) throws RemoteException;
	
	public void delete(String idUser) throws RemoteException;
	
	public void update(User user) throws RemoteException;
	
	public ArrayList find() throws RemoteException;
	
	public User find(String idUser) throws RemoteException;
	
	public void notifyServer(User user) throws RemoteException;
	
	public ArrayList getActiveUsers() throws RemoteException;
}
