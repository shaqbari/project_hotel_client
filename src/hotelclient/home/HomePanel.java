package hotelclient.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.Socket;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;
import sun.net.www.content.image.jpeg;

public class HomePanel extends JPanel{
	ClientMain main;
	Connection con;
	
	JPanel resvInfo;
	int resvInfoWidth=250;
	int resvInfoheight=300;
	
	JLabel title;
	JLabel resv_id, guest_name, resv_time, stay;
	private JLabel resv_id_input, guest_name_input, resv_time_input, stay_input;
	
	public HomePanel(ClientMain main) {
		this.main=main;
		con=main.con;
		
		resvInfo=new JPanel();
		title=new JLabel("예약정보 ");
		resv_id=new JLabel("예약번호 : ");
		guest_name=new JLabel("이름 : ");
		resv_time=new JLabel("입실가능시간 : ");
		stay=new JLabel("퇴실하실시간 : ");
		
		resv_id_input	=new JLabel();
		guest_name_input=new JLabel();
		resv_time_input=new JLabel();
		stay_input=new JLabel();
			
		resvInfo.setPreferredSize(new Dimension(resvInfoWidth, resvInfoheight));
		title.setPreferredSize(new Dimension(resvInfoWidth-10, resvInfoheight/5-10));
		resv_id.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		guest_name.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		resv_time.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		stay.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		resv_id_input.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		guest_name_input.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		resv_time_input.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		stay_input.setPreferredSize(new Dimension(resvInfoWidth/2-10 , resvInfoheight/5-10));
		
		resvInfo.add(title);
		resvInfo.add(resv_id);
		resvInfo.add(resv_id_input);
		resvInfo.add(guest_name);
		resvInfo.add(guest_name_input);
		resvInfo.add(resv_time);
		resvInfo.add(resv_time_input);
		resvInfo.add(stay);		
		resvInfo.add(stay_input);
		
		add(resvInfo);
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(true);	
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
