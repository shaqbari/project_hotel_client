package hotelclient.attract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class Attraction_View extends JPanel{
	JButton bt;
	ClientMain main;
	ImageIcon icon;
	JLabel la;
	URL url;
	
	public Attraction_View(String name, JButton bt, URL url) {
		this.bt = bt;
		icon = new ImageIcon(url);
		la = new JLabel(name);
		setLayout(new BorderLayout());
		
		//��ư�� �� �̹��� ������ ����
		Image orignImg = icon.getImage();
		Image changeImg = orignImg.getScaledInstance(280, 190, Image.SCALE_SMOOTH);
		ImageIcon resizeIcon = new ImageIcon(changeImg);
				
		la.setFont(new Font("���� ����", Font.BOLD, 17));
		la.setBackground(Color.LIGHT_GRAY);
		la.setHorizontalAlignment(JLabel.CENTER);	//�۾� �߾����� ����
		la.setVerticalAlignment(JLabel.CENTER);		//�۾� �߾����� ����
		bt.setPreferredSize(new Dimension(280, 190));
		bt.setIcon(resizeIcon);
				
		add(la);
		add(bt, BorderLayout.SOUTH);
	}
	
}



