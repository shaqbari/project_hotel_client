package hotelclient.attract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class AttractionPanel extends JPanel{
	ClientMain main;
	
	String[][] imgName = {
		{"jeju_samsung.jpg", "삼성혈"},	
		{"jeju_dragonhead.jpg", "용두암"},	
		{"jeju_mabang.jpg", "마방목지"},	
		{"jeju_hanla.jpg", "한라수목원"},	
	};
	JButton bt_samsung, bt_dragon, bt_mabang, bt_hanla;
	JButton[] buttons = new JButton[4];
	Attraction_View[] attView = new Attraction_View[imgName.length];
	
	JPanel p_center, p_map;	
	Image img;
	
	public AttractionPanel(ClientMain main) {
		//setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		this.main=main;
		
		p_center = new JPanel();
		p_map = new JPanel();
		
		buttons[0]=bt_samsung=new JButton();
		buttons[1]=bt_dragon=new JButton();
		buttons[2]=bt_mabang=new JButton();
		buttons[3]=bt_hanla=new JButton();
		
		p_center.setBackground(Color.CYAN);
		p_map.setBackground(Color.PINK);
		
		p_map.setPreferredSize(new Dimension(500, 300));
		
		p_center.setLayout(new FlowLayout());
		
		add(p_center);
		add(p_map, BorderLayout.EAST);
		
		view();
		
		//setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);
	}
	
	public void view(){
		for(int i=0;i<imgName.length;i++){
			URL url = null;
			try {
				url = new URL ("http://pseudoluna.synology.me/experi/images/" + imgName[i][0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			attView[i] = new Attraction_View(imgName[i][1], buttons[i], url);
			p_center.add(attView[i]);
		}
	}
	
}
