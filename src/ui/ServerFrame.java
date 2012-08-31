package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import main.WMServer;
import model.User;

public class ServerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu op;
	private JMenuItem shutdown;
	private JMenuItem exit;
	private JMenu help;
	private JMenuItem about;
	
	private JLabel userLabel;
	private JList activeUser;
	private JScrollPane userScroll;
	
	private JPanel mainPanel;
	private JPanel labelPanel;
	private JPanel listPanel;
	
	private WMServer server; 
	
	public ServerFrame(WMServer s) {
		server = s;
		
		menuBar = new JMenuBar();
		op = new JMenu("Operation");
		shutdown = new JMenuItem("Shut dwon");
		exit = new JMenuItem("Exit");
		help = new JMenu("Help");
		about = new JMenuItem("About");
		
		shutdown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				server.shutdown();
				JOptionPane.showConfirmDialog(null, "Server is down...", "Confirm", JOptionPane.DEFAULT_OPTION);
			}
			
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ServerFrame.this.dispose();
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
		
		op.add(shutdown);
		op.add(exit);
		help.add(about);
		menuBar.add(op);
		menuBar.add(help);
		
		userLabel = new JLabel("Active Users:");

		activeUser = new JList();
		activeUser.setFixedCellWidth(400);
		activeUser.setSelectedIndex(0);
		activeUser.setVisibleRowCount(10);
		
		userScroll = new JScrollPane(activeUser);
		userScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		userScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		mainPanel = new JPanel();
		labelPanel = new JPanel();
		listPanel = new JPanel();
		
		labelPanel.add(userLabel);
		listPanel.add(userScroll);
		
		mainPanel.add(labelPanel);
		mainPanel.add(listPanel);
		
		this.setJMenuBar(menuBar);
		this.getContentPane().add(BorderLayout.CENTER,mainPanel);
		this.setResizable(false);
		this.setTitle("WMServer");
		this.setSize(500,350);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setUsers(ArrayList list) {
		DefaultListModel listModel = new DefaultListModel();
		for(int i=0;i<list.size();i++)
			listModel.addElement((i+1)+": "+((User)list.get(i)).getName()+"| "+(((User)list.get(i)).getType()==0?"Admin":"Client"));
		activeUser.setModel(listModel);
	}
	
}