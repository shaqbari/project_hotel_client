package hotelclient.home;

import java.awt.Color;
import java.awt.Dimension;
import java.net.Socket;
import java.sql.Connection;

import javax.swing.JPanel;

import hotelclient.ClientMain;

public class HomePanel extends JPanel{
	ClientMain main;
	Connection con;
	
	
	public HomePanel(ClientMain main) {
		this.main=main;
		con=main.con;
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(true);	
	}
}
