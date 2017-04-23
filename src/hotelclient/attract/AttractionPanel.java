package hotelclient.attract;

import java.awt.Color;
import java.awt.Dimension;
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
	JButton bt1, bt2, bt3, bt4;
	JButton[] buttons = new JButton[4];
	Attraction_View[] attView = new Attraction_View[imgName.length];
	
	JLabel la_center, la_map;
	
	Image img;
	
	public AttractionPanel(ClientMain main) {
		this.main=main;
		/*
		buttons[0]=bt1=new JButton();
		buttons[1]=bt2=new JButton();
		buttons[2]=bt3=new JButton();
		buttons[3]=bt4=new JButton();
		
		for(int i=0;i<imgName.length;i++){
			URL url = null;
			try {
				url = new URL ("http://pseudoluna.synology.me/experi/images/" + imgName[i][0]+".jpg");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			attView[i] = new Attraction_View(main, imgName[0][i], buttons[i], url);
			add(attView[i]);
		}
		*/
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);
	}
	
	
	
}
