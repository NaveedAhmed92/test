package com.h2db;

import javax.swing.JDialog;

import org.h2.jdbc.JdbcArray;

public class Loginform extends JDialog{
 static	Loginform dialog= new Loginform();
	public Loginform()
	{
		initialize();	
	}
	public void initialize()
	{
		getContentPane().setLayout(null);
		setBounds(100, 100, 450, 300);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}
