package hotelclient.attract;

import java.awt.BorderLayout;
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

	
	public Attraction_View(String name, JButton bt, URL url) {
		this.bt = bt;
		icon = new ImageIcon(url);
		la = new JLabel(name);
		setLayout(new BorderLayout());
		
		//버튼에 들어갈 이미지 사이즈 조절
		Image orignImg = icon.getImage();
		Image changeImg = orignImg.getScaledInstance(280, 220, Image.SCALE_SMOOTH);
		ImageIcon resizeIcon = new ImageIcon(changeImg);
		
		
		la.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		//la.setHorizontalTextPosition(la.CENTER);
		//la.setVerticalTextPosition(la.TOP);
		bt.setPreferredSize(new Dimension(280, 220));
		bt.setIcon(resizeIcon);
		
		bt.addActionListener(this);
		
		add(la);
	
		add(bt, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
	}	
	
}



