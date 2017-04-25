package hotelclient.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.MalformedURLException;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

import hotelclient.service.Serv_view;

public class HomePanel extends JPanel {
	ClientMain main;
	

	JPanel  p_west,p_east, p_south;

	JPanel p_north, p_center;

	JPanel resvInfo;
	JPanel roomPanel;
	int resvInfoWidth=250;
	int resvInfoheight=300;
	Dimension dimension;
	
	JLabel background;
	JLabel title;
	JLabel resv_id, guest_name, resv_time, stay;
	JButton myPage, option;
	private JLabel resv_id_input, guest_name_input, resv_time_input, stay_input;
	
	JButton bt;
	String[] room_type={"deluxe","business","grand","first","vip","vvip", "sweet"};

	JButton bt_del, bt_bui,bt_grand,bt_fir,bt_vip,bt_vvip, bt_sweet;
	JButton[] buttons = new JButton[7]; //버튼을 p_south패널에 붙일예정
	
	public HomePanel(ClientMain main) {
		this.main=main;
		

		p_west=new JPanel();
		p_east=new JPanel();
		p_south=new JPanel();
		resvInfo=new JPanel();
		roomPanel=new JPanel();
		
		dimension=new Dimension((resvInfoWidth/2)-10, (resvInfoheight/5)-10);
		title=new JLabel("예약정보 ");
		resv_id=new JLabel("예약번호 : ");
		guest_name=new JLabel("이름 : ");
		resv_time=new JLabel("입실가능시간 : ");
		stay=new JLabel("퇴실하실시간 : ");
		
		resv_id_input	=new JLabel();
		guest_name_input=new JLabel();
		resv_time_input=new JLabel();
		stay_input=new JLabel();
		bt=new JButton("객실");
			
		resvInfo.setPreferredSize(new Dimension(resvInfoWidth, resvInfoheight));
		resvInfo.setBackground(Color.WHITE);
		
		title.setPreferredSize(new Dimension(resvInfoWidth-10, resvInfoheight/5-10));
		resv_id.setPreferredSize(dimension);
		guest_name.setPreferredSize(dimension);
		resv_time.setPreferredSize(dimension);
		stay.setPreferredSize(dimension);
		resv_id_input.setPreferredSize(dimension);
		guest_name_input.setPreferredSize(dimension);
		resv_time_input.setPreferredSize(dimension);
		stay_input.setPreferredSize(dimension);
		
		resvInfo.add(title);
		resvInfo.add(resv_id);
		resvInfo.add(resv_id_input);
		resvInfo.add(guest_name);
		resvInfo.add(guest_name_input);
		resvInfo.add(resv_time);
		resvInfo.add(resv_time_input);
		resvInfo.add(stay);		
		resvInfo.add(stay_input);
		
		
		setLayout(new BorderLayout());
		background=new JLabel(new ImageIcon("C:/java_workspace2/ClientPractice/res/hotelimg.jpg"));
		//background.setPreferredSize(new Dimension(1000, 900));
		

		p_west.setPreferredSize(new Dimension(900, 600));
		p_west.add(background);
		p_east.add(resvInfo);
		
		p_south.add(bt); //객실 버튼을 붙일 예정
		

		for(int i=0; i<room_type.length; i++){
			URL url = null;
			try {
				url = new URL("http://pseudoluna.synology.me/experi/images/"+room_type[i]+".jpg");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
		}
						
				
		add(p_west,BorderLayout.WEST);
		add(p_east, BorderLayout.EAST);
		add(p_south, BorderLayout.SOUTH);
		//setBackground(Color.CYAN);
		
		setPreferredSize(new Dimension(1200, 900));
		setVisible(true);	
		setLayout(new FlowLayout());
	
	}
	
	public JLabel getResv_id_input() {
		return resv_id_input;
	}
	
	public JLabel getGuest_name_input() {
		return guest_name_input;
	}
		
	public JLabel getResv_time_input() {
		return resv_time_input;
	}
		
	public JLabel getStay_input() {
		return stay_input;
	}


}
