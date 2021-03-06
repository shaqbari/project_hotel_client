package hotelclient.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;
import hotelclient.chat.ChatPanel;

public class HomePanel extends JPanel {
	ClientMain main;
	

	JPanel  p_west, p_south;
	JPanel p_east,p_east_n,p_east_s;

	JPanel p_north, p_center;
	
	public ChatPanel chatPanel;
	
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
	
	
	String[] room_type={"deluxe","business","grand","first","vip","vvip", "suite"};
	//ArrayList<Room_Option> list;

	
	public HomePanel(ClientMain main) {
		this.main=main;
		
		p_west=new JPanel();
		p_east=new JPanel();
		p_east_n=new JPanel();
		p_east_s=new JPanel() ;
		p_south=new JPanel();
		resvInfo=new JPanel();
		roomPanel=new JPanel();
		chatPanel=new ChatPanel(main);
		
		dimension=new Dimension((resvInfoWidth/2)-10, (resvInfoheight/5)-10);
		title=new JLabel("                            최근 예약정보 ");
		resv_id=new JLabel("예약번호 : ");
		guest_name=new JLabel("이름 : ");
		resv_time=new JLabel("입실가능시간 : ");
		stay=new JLabel("퇴실하실시간 : ");
		
		resv_id_input	=new JLabel();
		guest_name_input=new JLabel();
		resv_time_input=new JLabel();
		stay_input=new JLabel();
		
		p_east.setLayout(new BorderLayout());
		
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
		try {
			background=new JLabel(new ImageIcon(new URL("http://pseudoluna.synology.me/experi/images/hilton.jpg")));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		//background.setPreferredSize(new Dimension(1000, 900));
		
		
		p_west.setPreferredSize(new Dimension(800, 600));
		p_west.add(background);
		p_east_n.add(chatPanel);
		p_east_s.add(resvInfo);
		p_east.add(p_east_n,BorderLayout.NORTH);
		p_east.add(p_east_s,BorderLayout.SOUTH);
		//p_south.add(bt); //RoomPanel을 붙일 예정
		
		
		
		for(int i=0; i<room_type.length; i++){
			
			try {
				URL url = new URL("http://pseudoluna.synology.me/experi/images/"+room_type[i]+".jpg");
				RoomPanel roomPanel=new RoomPanel(url, this,room_type[i]);
			//	list=new ArrayList<Room_Option>();
				
				p_south.add(roomPanel);
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
