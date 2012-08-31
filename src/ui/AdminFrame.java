package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import main.WMClient;
import model.User;

public class AdminFrame extends ClientFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel clientP;
	private JLabel clientL;
	private JScrollPane clientScroller;
	private JList clientList;
	
	private JPanel ps2[];
	
	public AdminFrame(WMClient c) {
		super(c);
		
		clientL = new JLabel("Users:");
		clientList = new JList();
		
		clientList.setFixedCellWidth(700);
		clientList.setSelectedIndex(0);
		clientList.setVisibleRowCount(5);
		
		clientScroller = new JScrollPane(clientList);
		clientScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		clientP = new JPanel();
		clientP.setLayout(new BoxLayout(clientP,BoxLayout.Y_AXIS));
		
		ps2 = new JPanel[2];
		
		for(int i=0;i<2;i++)
			ps2[i] = new JPanel();
		
		ps2[0].add(clientL);
		ps2[1].add(clientScroller);
		
		for(int i=0;i<2;i++)
			clientP.add(ps2[i]);
		
		clientP.setBorder(
				BorderFactory.createTitledBorder( 
						null, "Users", TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri",Font.BOLD, 14),Color.blue));
		
		this.getContentPane().add(BorderLayout.SOUTH,clientP);
		this.setSize(800,800);
	}
	
	public void setUsers(ArrayList list) {
		DefaultListModel listModel = new DefaultListModel();
		for(int i=0;i<list.size();i++)
			listModel.addElement((i+1)+": "+((User)list.get(i)).getName()+"| "+(((User)list.get(i)).getType()==0?"Admin":"Client"));
		
		clientList.setModel(listModel);
	}
	
}
