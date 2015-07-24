package com.h2db;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class h2db {

	private JFrame frame;
	private JTable table;
	Connection connection;
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					h2db window = new h2db();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public h2db() throws SQLException {
		makeConnection();
		//createTable();
		//insertRecord();
		gettingRecord();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			   UIManager
			     .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			  // initialize();
			  } catch (Exception ex) {

			  }
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		   int id = 1;
		   String name = "naveed";
		 
		 JScrollPane scr=new JScrollPane();
		 scr.setBounds(10, 11, 400, 180);
		frame.getContentPane().add(scr);
		table = new JTable(model);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		scr.setViewportView(table);
	}
	
	public void gettingRecord()
	{
		model.addColumn("ID");
		model.addColumn("name");
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
	
	public void createTable()
	{
		String categoryTable = "CREATE TABLE IF NOT EXISTS person_detail ( id int(11) NOT NULL AUTO_INCREMENT, name varchar(80) NOT NULL, PRIMARY KEY (id) )";
		Statement stmt = null;
		  try{
		    stmt = connection.createStatement();
		    stmt.executeUpdate(categoryTable);
		    System.out.println("Person table successfully created");
		  }catch(SQLException sqlex){
		   System.err.println("error occured while category table");
		  }finally{
		   if(stmt != null){
		    try{
		     stmt.close();
		     connection.close();
		    }catch(SQLException sqlex){
		     System.err.println("statement error.");
		    }
		   }
		  }
	}
	
	public void insertRecord() throws SQLException
	{
		Statement stmt =null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO person_detail"+ " (name) VALUES('Naveed Ahmed')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("Record inserted successfully..");
			
			stmt.close();
			connection.close();
			
		}
		
	}
}
