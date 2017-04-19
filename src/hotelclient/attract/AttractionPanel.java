package hotelclient.attract;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JPanel;

import hotelclient.ClientMain;

public class AttractionPanel extends JPanel{
	ClientMain main;
	Connection con;
	
	
	public AttractionPanel(ClientMain main) {
		this.main=main;
		con=main.con;
		
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);
	}
	
}
