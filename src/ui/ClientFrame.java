package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import main.WMClient;
import model.*;

public class ClientFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JMenuBar menuBar;
	protected JMenu op;
	protected JMenuItem edit;
	protected JMenuItem save;
	protected JMenuItem logout;
	protected JMenuItem exit;
	protected JMenu note;
	protected JMenuItem newNote;
	protected JMenu help;
	protected JMenuItem about;
	
	protected JPanel infoP;
	protected JPanel contactP;
	protected JPanel contactP1;
	protected JPanel contactP2;
	protected JPanel noteP;
	//protected JPanel clientP;
	
	protected JLabel idL;
	protected JLabel passwordL;
	protected JLabel nameL;
	protected JLabel typeL;
	protected JLabel sexL;
	protected JLabel emailL;
	
	protected JTextField idT;
	protected JPasswordField passwordT;
	protected JTextField nameT;
	protected JTextField typeT;
	protected JTextField sexT;
	protected JTextField emailT;
	
	protected JLabel pubContactL;
	protected JLabel priContactL;
	protected JList pubList;
	protected JList priList;
	protected JScrollPane pubScroller;
	protected JScrollPane priScroller;
	
	protected JLabel noteL;
	protected JList noteList;
	protected JScrollPane noteScroller;
	//protected JList clientList;
	
	protected JPanel ps[];
	protected JPanel centerP;
	
	protected WMClient client;
	
	public ClientFrame(WMClient c) {
		client = c;
		
		menuBar = new JMenuBar();
		op = new JMenu("Operation");
		edit = new JMenuItem("Edit");
		save = new JMenuItem("Save");
		logout = new JMenuItem("Logout");
		exit = new JMenuItem("Exit");
		note = new JMenu("Note");
		newNote = new JMenuItem("New note");
		help = new JMenu("Help");
		about = new JMenuItem("About");
		
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ClientFrame.this.dispose();
				client.logout();
			    new WMClient();
			}
			
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientFrame.this.dispose();
				System.exit(0);
			}
			
		});
		
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null, "WMServer 1.0\nAuthor: gHc", "About", JOptionPane.DEFAULT_OPTION);
			}
			
		});
		
		op.add(edit);
		op.add(save);
		op.add(logout);
		op.add(exit);
		note.add(newNote);
		help.add(about);
		menuBar.add(op);
		menuBar.add(note);
		menuBar.add(help);
		
		ps = new JPanel[18];
		for(int i=0;i<18;i++)
			ps[i] = new JPanel();
		
		//left panel
		infoP = new JPanel();
		
		idL = new JLabel("                Id:");
		passwordL = new JLabel("Password:");
		nameL = new JLabel("        Name:");
		typeL = new JLabel("         Type:");
		sexL = new JLabel("          Sex:");
		emailL = new JLabel("       Email:");
		
		idT = new JTextField(15);
		passwordT = new JPasswordField(15);
		nameT = new JTextField(15);
		typeT = new JTextField(15);
		sexT = new JTextField(15);
		emailT = new JTextField(15);
		
		idT.setEditable(false);
		passwordT.setEditable(false);
		nameT.setEditable(false);
		typeT.setEditable(false);
		sexT.setEditable(false);
		emailT.setEditable(false);
		
		infoP.setLayout(new GridLayout(8,2));
		
		ps[0].add(idL);
		ps[1].add(idT);
		ps[2].add(passwordL);
		ps[3].add(passwordT);
		ps[4].add(nameL);
		ps[5].add(nameT);
		ps[6].add(typeL);
		ps[7].add(typeT);
		ps[8].add(sexL);
		ps[9].add(sexT);
		ps[10].add(emailL);
		ps[11].add(emailT);
		
		infoP.add(new JPanel());
		infoP.add(new JPanel());
		for(int i=0;i<12;i++)
			infoP.add(ps[i]);
		
		infoP.setBorder(
				BorderFactory.createTitledBorder( 
						null, "Info", TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri",Font.BOLD, 14),Color.blue));
		
		//right panel
		contactP = new JPanel();
		contactP1 = new JPanel();
		contactP2 = new JPanel();
		
		pubContactL = new JLabel("Public contact:");
		priContactL = new JLabel("Private contact:");
		
		pubList = new JList();
		priList = new JList();
		pubScroller = new JScrollPane(pubList);
		pubScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		priScroller = new JScrollPane(priList);
		priScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pubList.setFixedCellWidth(300);
		pubList.setSelectedIndex(0);
		pubList.setVisibleRowCount(5);
		
		priList.setFixedCellWidth(300);
		priList.setSelectedIndex(0);
		priList.setVisibleRowCount(5);
		
		contactP.setLayout(new BoxLayout(contactP,BoxLayout.Y_AXIS));
		contactP1.setLayout(new BoxLayout(contactP1,BoxLayout.Y_AXIS));
		contactP2.setLayout(new BoxLayout(contactP2,BoxLayout.Y_AXIS));
		
		ps[12].add(pubContactL);
		ps[13].add(pubScroller);
		contactP1.add(ps[12]);
		contactP1.add(ps[13]);
		
		ps[14].add(priContactL);
		ps[15].add(priScroller);
		contactP2.add(ps[14]);
		contactP2.add(ps[15]);
		
		contactP.add(contactP1);
		contactP.add(contactP2);
		
		contactP.setBorder(
				BorderFactory.createTitledBorder( 
						null, "Contact", TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri",Font.BOLD, 14),Color.blue));
		
		//top panel
		noteP = new JPanel();
		noteL = new JLabel("Note:");
		noteList = new JList();
		noteScroller = new JScrollPane(noteList);
		noteScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		noteList.setFixedCellWidth(700);
		noteList.setSelectedIndex(0);
		noteList.setVisibleRowCount(10);
		
		noteP.setLayout(new BoxLayout(noteP,BoxLayout.Y_AXIS));
		ps[16].add(noteL);
		ps[17].add(noteScroller);
		noteP.add(ps[16]);
		noteP.add(ps[17]);
		
		noteP.setBorder(
				BorderFactory.createTitledBorder( 
						null, "Note", TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri",Font.BOLD, 14),Color.blue));
		
		centerP = new JPanel();
		centerP.add(infoP);
		centerP.add(new JPanel());
		centerP.add(new JPanel());
		centerP.add(new JPanel());
		centerP.add(contactP);
		
		this.setJMenuBar(menuBar);
		//this.getContentPane().add(BorderLayout.CENTER,infoP);
		this.getContentPane().add(BorderLayout.CENTER,centerP);
		this.getContentPane().add(BorderLayout.NORTH,noteP);
		this.setResizable(false);
		this.setTitle("WMClient-Client");
		this.setSize(800,700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(new CloseHandler());        //logout on close
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setInfo(User user) {
		idT.setText(user.getIdUser());
		passwordT.setText(user.getPassword());
		nameT.setText(user.getName());
		typeT.setText(user.getType()==0?"Admin":"Client");
		sexT.setText(user.getSex()==0?"Female":"Male");
		emailT.setText(user.getEmail());
	}
	
	public void setNote(ArrayList list) {
		DefaultListModel listModel = new DefaultListModel();
		for(int i=0;i<list.size();i++)
			listModel.addElement((i+1)+": "+"Title: "+((Note)(list.get(i))).getTitle()+" |Author: "+((Note)(list.get(i))).getOwner());
		
		noteList.setModel(listModel);
	}
	
	public void setContact(ArrayList list) {
		DefaultListModel pubModel = new DefaultListModel();
		DefaultListModel priModel = new DefaultListModel();
		
		int pubC = 0;
		int priC = 0;
		for(int i=0;i<list.size();i++) {
			if(((Contact)list.get(i)).getType()==0)
				pubModel.addElement(++pubC+": "+((Contact)list.get(i)).getName());
			else 
				priModel.addElement(++priC+": "+((Contact)list.get(i)).getName());
		}
		
		pubList.setModel(pubModel);
		priList.setModel(priModel);
	}
	
	
	public class CloseHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			client.logout();
		}
	}
}
