package hotelclient.service;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JPanel;

import hotelclient.ClientMain;

public class ServicePanel extends JPanel {
	ClientMain main;
	Connection con;
	
	
	public ServicePanel(ClientMain main) {
		this.main=main;
		con=main.con;
		
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);	
	}
}
