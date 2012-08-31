package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import ui.ServerFrame;

import dao.*;
import dao.impl.*;

import model.User;

public class WMServer {
	
	private ArrayList activeUsers;
	private UserDao userDao;
	private NoteDao noteDao;
	private ContactDao contactDao;
	private DaoHelper daoHelper;
	
	private ServerFrame serverFrame;;
	
	public static void main(String[] args) {
		new WMServer().go();
	}
	
	public void go() {	
		serverFrame = new ServerFrame(this);
		
		activeUsers = new ArrayList();
		
		daoHelper = new DaoHelperImpl();
		try {
			userDao = new UserDaoImpl(this,daoHelper);
			noteDao = new NoteDaoImpl(daoHelper);
			contactDao = new ContactDaoImpl(daoHelper);
			
			LocateRegistry.createRegistry(5000);
            Naming.rebind("rmi://127.0.0.1:5000/UserDao", userDao);
			LocateRegistry.createRegistry(5001);
            Naming.rebind("rmi://127.0.0.1:5001/NoteDao", noteDao);
			LocateRegistry.createRegistry(5002);
            Naming.rebind("rmi://127.0.0.1:5002/ContactDao", contactDao);
            
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("server started...");
	}
	
	public void shutdown() {
		userDao = null;
		noteDao = null;
		contactDao = null;
		
		serverFrame.dispose();
		System.out.println("server down...");
		System.exit(0);
	}
	
	public void updateUsers(User user) {
		int i;
		int size = activeUsers.size();
		for(i=0;i<size;i++) {
			if(((User)activeUsers.get(i)).getIdUser().equals(user.getIdUser())) {
				activeUsers.remove(i);
				break;
			}
		}
		
		if(size==activeUsers.size())
			activeUsers.add(user);
		
		serverFrame.setUsers(activeUsers);
	}

	public ArrayList getActiveUsers() {
		return activeUsers;
	}

	
	
	public void setActiveUsers(ArrayList activeUsers) {
		this.activeUsers = activeUsers;
	}
	
	

}
