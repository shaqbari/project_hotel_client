package hotelclient.service;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class Serv_view extends JPanel implements ActionListener{
	ServicePanel sp;
	ClientMain main;
	JButton bt;
	ImageIcon icon;
	JLabel la;
	URL url;
	
	public Serv_view(ClientMain main, JButton bt, URL url, String serv_name) {
		this.main = main;
		this.bt = bt;
		icon = new ImageIcon(url);
		la = new JLabel(serv_name);
		
		la.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));

		setPreferredSize(new Dimension(250, 300));
		bt.setPreferredSize(new Dimension(350, 250));
		bt.setIcon(icon);
		
		bt.setBorderPainted(false);
		bt.setContentAreaFilled(false);
		bt.setFocusPainted(false);
		bt.setOpaque(false);

		bt.addActionListener(this);

		add(bt);
		add(la);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(bt.getName());
		/*Object obj=e.getSource();
		if(obj==){
			System.out.println(0+"¿‘¥œ¥Ÿ");
		}*/
	}

}
