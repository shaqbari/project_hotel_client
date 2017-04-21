package hotelclient.attract;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class Attraction_View extends JPanel implements ActionListener{
	JButton bt;
	ClientMain main;
	ImageIcon icon;
	JLabel la;
	URL url;

	
	public Attraction_View(ClientMain main, String name, JButton bt, URL url) {
		this.main = main;
		this.bt = bt;
		icon = new ImageIcon(url);
		la = new JLabel(name);
		
		la.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		
		setPreferredSize(new Dimension(200, 100));
		bt.setIcon(icon);
		
		bt.addActionListener(this);
		
		add(la);
		add(bt);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
	}
}
