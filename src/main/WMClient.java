package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dao.*;
import model.*;
import ui.*;

public class WMClient {

	private UserDao userDao;
	private NoteDao noteDao;
	private ContactDao contactDao;
	private ClientFrame mainFrame;
	private LoginFrame logFrame;
	private User user;
	private int type;
	
	public WMClient() {
		logFrame = new LoginFrame(this);
		try {
			userDao = (UserDao)Naming.lookup("rmi://127.0.0.1:5000/UserDao");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WMClient();
	}
	
	public void go() {
		
		try {
			userDao.notifyServer(user);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(type==0)
			mainFrame = new AdminFrame(this);
		else
			mainFrame = new ClientFrame(this);
		
		mainFrame.setInfo(user);
		
		try {
			noteDao = (NoteDao)Naming.lookup("rmi://127.0.0.1:5001/NoteDao");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(noteDao!=null) {
			try {
				ArrayList notes = noteDao.findAll(user.getIdUser());
				mainFrame.setNote(notes);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			contactDao = (ContactDao)Naming.lookup("rmi://127.0.0.1:5002/ContactDao");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(contactDao!=null) {
			try {
				ArrayList contacts = contactDao.find(user.getIdUser());
				mainFrame.setContact(contacts);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(type==0) {
			try {
				ArrayList users = userDao.find();
				((AdminFrame)mainFrame).setUsers(users);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	public boolean login() {
		try {
			user = userDao.find(logFrame.getIdT().getText());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user==null)
			return false;
		else {
			if(!user.getPassword().equals(String.valueOf(logFrame.getPasswordT().getPassword())))
				return false;
			else {
				if((user.getType()==0 && logFrame.getAdminR().isSelected())) {
					type = 0;
					return true;
				}
				else if((user.getType()==1 && logFrame.getClientR().isSelected())) {
					type = 1;
					return true;
				}
				else 
					return false;
			}
		}
	}
	
	public void logout() {
		try {
			userDao.notifyServer(user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean repeatLogin() {
		try {
			ArrayList activeUsers = userDao.getActiveUsers();
			for(int i=0;i<activeUsers.size();i++)
				if(((User)activeUsers.get(i)).getIdUser().equals(user.getIdUser()))
					return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
