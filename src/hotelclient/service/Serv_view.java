package hotelclient.service;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class Serv_view extends JPanel{
	Serv_Detail sd;
	Connection con;
	ClientMain main;
	JButton bt;
	ImageIcon icon;
	JLabel la;
	URL url;
	ArrayList<Service> service = new ArrayList<Service>();
	
	public Serv_view(ClientMain main, JButton bt, URL url, String serv_name) {
		this.main = main;
		this.bt = bt;
		con=main.con;
		
		icon = new ImageIcon(url);
		la = new JLabel(serv_name);
		
		la.setFont(new Font("Georgia", Font.BOLD, 20));

		setPreferredSize(new Dimension(350,320));
		bt.setPreferredSize(new Dimension(400,280));
		bt.setIcon(icon);
		
		bt.setBorderPainted(false);
		bt.setContentAreaFilled(false);
		bt.setFocusPainted(false);
		bt.setOpaque(false);

		add(bt);
		add(la);
		
		setVisible(true);
		
		bt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//System.out.println("새로운 창 등장해야함");
				sd = new Serv_Detail(main,serv_name,icon);
			}
		});	
	}	
}
