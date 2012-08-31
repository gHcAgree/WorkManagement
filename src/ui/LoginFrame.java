package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.WMClient;

public class LoginFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu op;
	private JMenuItem clear;
	private JMenuItem exit;
	private JMenu help;
	private JMenuItem about;
	
	private JLabel idL;
	private JLabel passwordL;
	private JTextField idT;
	private JPasswordField passwordT;
	private JLabel typeL;
	private JRadioButton adminR;
	private JRadioButton clientR;
	private ButtonGroup group;
	
	private JPanel labelP;
	private JPanel textP;
	private JPanel mainP;
	private JPanel buttonP;
	
	private JButton confirmB;
	private JButton exitB;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	
	private WMClient client;
	
	public LoginFrame(WMClient c) {
		client = c;
		
		menuBar = new JMenuBar();
		op = new JMenu("Operation");
		clear = new JMenuItem("clear");
		exit = new JMenuItem("Exit");
		help = new JMenu("Help");
		about = new JMenuItem("About");
		
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				idT.setText("");
				passwordT.setText("");
			}
			
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginFrame.this.dispose();
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
		
		op.add(clear);
		op.add(exit);
		help.add(about);
		menuBar.add(op);
		menuBar.add(help);
		
		idL = new JLabel("                Id:");
		passwordL = new JLabel("Password:");
		typeL = new JLabel("          Type:");
		
		idT = new JTextField(15);
		passwordT = new JPasswordField(15);
		adminR = new JRadioButton("admin");
		clientR = new JRadioButton("client");
		group = new ButtonGroup();
		
		clientR.setSelected(true);
		
		labelP = new JPanel();
		textP = new JPanel();
		mainP = new JPanel();
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		
		labelP.setLayout(new BoxLayout(labelP,BoxLayout.Y_AXIS));
		textP.setLayout(new BoxLayout(textP,BoxLayout.Y_AXIS));
		mainP.setLayout(new BoxLayout(mainP,BoxLayout.X_AXIS));
		
		p1.add(idL);
		p2.add(passwordL);
		p3.add(typeL);
		labelP.add(p1);
		labelP.add(p2);
		labelP.add(p3);
		
		p4.add(idT);
		p5.add(passwordT);
		p6.add(adminR);
		p6.add(clientR);
		textP.add(p4);
		textP.add(p5);
		textP.add(p6);
		
		group.add(adminR);
		group.add(clientR);
		
		mainP.add(labelP);
		mainP.add(textP);
		
		confirmB = new JButton("Confirm");
		exitB = new JButton("Exit");
		
		confirmB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(client.login()&&(!client.repeatLogin())) {
					LoginFrame.this.dispose();
					client.go();
				} else {
					JOptionPane.showConfirmDialog(null, "Login failed!\nMaybe wrong password or userid or already logged in!", "Failure", JOptionPane.DEFAULT_OPTION);
				}
			}
			
		});
		
		exitB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginFrame.this.dispose();
				System.exit(0);
			}
			
		});
		
		buttonP = new JPanel();
		
		buttonP.add(confirmB);
		buttonP.add(exitB);
		
		this.setJMenuBar(menuBar);
		//this.getContentPane().add(BorderLayout.NORTH,new JLabel(""));
		//this.getContentPane().add(BorderLayout.SOUTH,new JLabel(""));
		this.getContentPane().add(BorderLayout.CENTER,mainP);
		this.getContentPane().add(BorderLayout.SOUTH,buttonP);
		//this.getContentPane().add(BorderLayout.WEST,new JLabel(""));
		//this.getContentPane().add(BorderLayout.EAST,new JLabel(""));
		this.setResizable(false);
		this.setTitle("Login");
		this.setSize(300,200);           //should be at the front of the next statement;
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public JTextField getIdT() {
		return idT;
	}
	
	public JPasswordField getPasswordT() {
		return passwordT;
	}
	
	public JRadioButton getAdminR() {
		return adminR;
	}
	
	public JRadioButton getClientR() {
		return clientR;
	}
	
}
