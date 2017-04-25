package hotelclient.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;

import hotelclient.service.Serv_view;

public class RoomPanel extends JPanel{
	HomePanel home;
	String[] room_type={"deluxe","business","grand","first","vip","vvip", "sweet"};
	
	JButton bt_del, bt_bui,bt_grand,bt_fir,bt_vip,bt_vvip, bt_sweet;
	JButton[] buttons = new JButton[7];
	Serv_view[] serv_view= new Serv_view[buttons.length];
	
	
	public RoomPanel(HomePanel home) {
		this.home=home;
		setLayout(new FlowLayout());
		
		for(int i=0; i<room_type.length; i++){
			URL url = null;
			try {
				url = new URL("http://pseudoluna.synology.me/experi/images/"+room_type[i]+".jpg");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		
		}
		
		//add(txt);
		
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);	
	}

}
