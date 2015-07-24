package com.h2db;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class h2 extends JDialog {
 private JTable table;
 private JScrollPane scrollPane;
 @SuppressWarnings("serial")
DefaultTableModel model = new DefaultTableModel(){
	 public boolean isCellEditable(int row, int column) {
		 return false;
	 };
	 
 };
 Connection connection;
static h2 dialog = new h2();

 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  try {
   
   dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
   dialog.setVisible(true);
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 /**
  * Create the dialog.
  */
 @SuppressWarnings("unused")
public h2() {
	 try {
		   UIManager
		     .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		  //initialize();
		  } catch (Exception ex) {

		  }
  setBounds(100, 100, 450, 300);
  getContentPane().setLayout(null);
  {
	JMenuBar menubar = new JMenuBar();
	JMenu filemenu = new JMenu("File");
	JMenuItem items = filemenu.add("Get Record");
	items.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		System.out.println("menu item clicked..");
		new Loginform();
		}
	});
	menubar.setBounds(0,0,434,20);
	menubar.add(filemenu);
	getContentPane().add(menubar);
	  
   JPanel buttonPane = new JPanel();
   buttonPane.setBounds(0, 228, 434, 33);
   buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
   getContentPane().add(buttonPane);
   {
    JButton okButton = new JButton("Delete");
    okButton.setActionCommand("Delete");
    okButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(table.getSelectedRow()!= -1)
			{
				model.removeRow(table.getSelectedRow());
			}
		}
	});
    buttonPane.add(okButton);
    getRootPane().setDefaultButton(okButton);
   }
   {
    JButton cancelButton = new JButton("Cancel");
    cancelButton.setActionCommand("Cancel");
    cancelButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("you clicked the cancel button");
		    dialog.dispose();
		}
	});
    buttonPane.add(cancelButton);
   }
  }
  getContentPane().add(getScrollPane());
 // model.addRow(new Object[]{1, "Naveed"});
 }
 private JTable getTable() {
  if (table == null) {
	  
   table = new JTable(model);
   model.addColumn("ID");
   model.addColumn("NAME");
   try {
	makeConnection();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   gettingRecord();
  }
  table.getTableHeader().setResizingAllowed(false);
  return table;
 }
 private JScrollPane getScrollPane() {
  if (scrollPane == null) {
   scrollPane = new JScrollPane();
   scrollPane.setBounds(25, 20, 385, 182);
   scrollPane.setViewportView(getTable());
  }
  return scrollPane;
 }
 public void makeConnection() throws SQLException
	{
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:./db/index", "admin", "admin124");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 public void gettingRecord()
	{
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person_detail");
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("name");
			
				model.addRow(new Object[]{id, name});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}