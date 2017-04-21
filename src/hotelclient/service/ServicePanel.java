package hotelclient.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class ServicePanel extends JPanel {
	ClientMain main;
	Connection con;
	
	String[] serv_name = {"beer","breakfast","dinner","lunch","snack","wine"};
	
	JButton bt_beer,bt_breakfast,bt_dinner,bt_lunch,bt_snack,bt_wine;
	JButton[] buttons = new JButton[6];
	Serv_view[] serv_view= new Serv_view[serv_name.length];
	
	JLabel label;
	ImageIcon imae;
	
	public ServicePanel(ClientMain main) {
		setLayout(new FlowLayout());
		this.main=main;
		con=main.con;
		
		buttons[0]=bt_beer=new JButton();
		buttons[1]=bt_breakfast=new JButton();
		buttons[2]=bt_dinner=new JButton();
		buttons[3]=bt_lunch=new JButton();
		buttons[4]=bt_snack=new JButton();
		buttons[5]=bt_wine=new JButton();
		
		for(int i=0; i<serv_name.length; i++){
			URL url = null;
			try {
				url = new URL("http://pseudoluna.synology.me/experi/images/"+serv_name[i]+".jpg");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			serv_view[i] = new Serv_view(main, buttons[i], url, serv_name[i]);
			add(serv_view[i]);
		}
	
		
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);	
	}
}
