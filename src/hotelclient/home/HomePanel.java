package hotelclient.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class HomePanel extends JPanel implements ActionListener{
	ClientMain main;
	
	JPanel p_north, p_center;
	JPanel resvInfo;
	int resvInfoWidth=250;
	int resvInfoheight=300;
	Dimension dimension;
	
	JLabel background;
	JLabel title;
	JLabel resv_id, guest_name, resv_time, stay;
	JButton myPage, option;
	private JLabel resv_id_input, guest_name_input, resv_time_input, stay_input;
	
	public HomePanel(ClientMain main) {
		this.main=main;
		
		p_north=new JPanel();
		p_center=new JPanel();
		myPage=new JButton("MyPage");
		option=new JButton("option");
		resvInfo=new JPanel();
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
			
		resvInfo.setPreferredSize(new Dimension(resvInfoWidth, resvInfoheight));
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
		
		p_north.add(myPage);
		p_north.add(option);
		p_center.add(background);
		
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		//setBackground(Color.CYAN);
		
		myPage.addActionListener(this);
		option.addActionListener(this);
		
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

	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==myPage){
			
			p_center.updateUI();
			background.setVisible(false);
			p_north.setVisible(false);
			p_center.add(resvInfo,BorderLayout.CENTER);
			
		}else if(obj==option){
			
		}
		
	}	
}
