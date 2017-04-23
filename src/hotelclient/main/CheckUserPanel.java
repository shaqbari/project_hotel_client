package hotelclient.main;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hotelclient.ClientMain;
import hotelclient.network.GuestLoginRequest;
import hotelclient.network.MemberLoginRequest;

public class CheckUserPanel extends JPanel implements ActionListener, ItemListener{
	ClientMain main;
	
	JPanel p_north, p_input, p_input_guest, p_input_member, p_south;
	boolean isGuest=true;//비회원과 회원패널 구분하는데 쓰일 예정 true이면 guest, false이면 member
	Font font;
		
	JLabel la_title;
	CheckboxGroup group;
	Checkbox ch_guest, ch_member;		

	//비회원 패널에 붙을 객체들	
	JLabel la_resv_id, la_phone, la_phone_spacer1, la_phone_spacer2;
	public JTextField txt_resv_id, txt_phone1, txt_phone2, txt_phone3;	
	
	//회원 패널에 붙을 객체들
	JLabel la_id, la_pw;
	public JTextField txt_id;
	public JPasswordField txt_pw;	
	
	JButton bt_check, bt_regist;
	
	ArrayList<Hotel_admin>hotel_admins=new ArrayList<Hotel_admin>();
	
	String adminId="admin";
	String adminPw="admin";
	
	public CheckUserPanel(ClientMain main) {		
		this.main=main;
		
		p_north=new JPanel();
		p_input=new JPanel(); //회원패널과 비회원패널을 그냥 borderlayout에 붙이면 가장 나중에 붙인거만 나온다. 그러므로 그것을 감쌀수 있는 패널을 만들자
		p_input_guest=new JPanel();
		p_input_member=new JPanel();
		p_south=new JPanel();
		la_title=new JLabel("호텔서비스시스템 로그인");
		font=new Font("맑은 고딕", font.PLAIN, 24);
		group=new CheckboxGroup();
		ch_guest=new Checkbox("비회원", group, true);
		ch_member=new Checkbox("회원", group, false);
		
		//p_input_guest에 붙을 객체
		la_resv_id=new JLabel("예약번호 ");
		txt_resv_id=new JTextField("1");
		la_phone=new JLabel("전화번호 ");
		txt_phone1=new JTextField("010");
		la_phone_spacer1=new JLabel(" -");
		txt_phone2=new JTextField("2222");
		la_phone_spacer2=new JLabel(" -");		
		txt_phone3=new JTextField("2222");
		
		//p_input_member에 붙을 객체
		la_id=new JLabel("ID   ");
		la_pw=new JLabel("PW   ");
		txt_id=new JTextField("admin", 13);
		txt_pw=new JPasswordField("admin", 13);
		
		//p_south에 붙을 버튼
		bt_check=new JButton(" 로그인 ");
		bt_regist=new JButton("회원가입");
		
		this.setLayout(new BorderLayout());
		
		p_input_guest.setPreferredSize(new Dimension(400,100));		
		p_input_member.setPreferredSize(new Dimension(400, 100));		
		
		la_resv_id.setPreferredSize(new Dimension(120, 40));
		txt_resv_id.setPreferredSize(new Dimension(250, 40));
		la_phone.setPreferredSize(new Dimension(120, 40));
		txt_phone1.setPreferredSize(new Dimension(60, 40));
		la_phone_spacer1.setPreferredSize(new Dimension(25, 40));
		txt_phone2.setPreferredSize(new Dimension(60, 40));
		la_phone_spacer2.setPreferredSize(new Dimension(25, 40));
		txt_phone3.setPreferredSize(new Dimension(60, 40));
		
		la_id.setPreferredSize(new Dimension(70, 40));
		la_pw.setPreferredSize(new Dimension(70, 40));
				
		la_title.setFont(new Font("맑은고딕", font.BOLD, 25));
				
		la_id.setFont(font);
		la_pw.setFont(font);
		txt_id.setFont(font);
		txt_pw.setFont(font);
		
		la_resv_id.setFont(font);
		txt_resv_id.setFont(font);
		la_phone.setFont(font);
		txt_phone1.setFont(font);
		la_phone_spacer1.setFont(font);
		txt_phone2.setFont(font);
		la_phone_spacer2.setFont(font);
		txt_phone3.setFont(font);
					
		p_north.add(la_title);
		p_north.add(ch_guest);
		p_north.add(ch_member);
				
		p_input_guest.add(la_resv_id);
		p_input_guest.add(txt_resv_id);
		p_input_guest.add(la_phone);
		p_input_guest.add(txt_phone1);
		p_input_guest.add(la_phone_spacer1);
		p_input_guest.add(txt_phone2);
		p_input_guest.add(la_phone_spacer2);
		p_input_guest.add(txt_phone3);
				
		p_input_member.add(la_id);
		p_input_member.add(txt_id);
		p_input_member.add(la_pw);
		p_input_member.add(txt_pw);
		
		p_input.add(p_input_guest);
		p_input.add(p_input_member);
		
		p_south.add(bt_check);
		p_south.add(bt_regist);
		
		add(p_north, BorderLayout.NORTH);
		add(p_input);		
		add(p_south, BorderLayout.SOUTH);
		
		
		txt_pw.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if (key==KeyEvent.VK_ENTER) {
					check();
				}
			}
		});
		bt_check.addActionListener(this);
		bt_regist.addActionListener(this);
		
		ch_guest.addItemListener(this);
		ch_member.addItemListener(this);
				
		p_input_member.setVisible(false);//처음엔 안보이게
		
		setSize(400, 200);
		setVisible(true);		
	}
	
	//비회원과 회원에 따라 정보가 일치하면 hotelMain만 보이게한다.
	public void check(){
		if (isGuest) {
			GuestLoginRequest guestLogin=new GuestLoginRequest(main, this);
			guestLogin.requestJSON();
		}else {
			MemberLoginRequest memberLogin=new MemberLoginRequest(main, this);
			memberLogin.requestJSON();
		}		
	}
	
	public void actionPerformed(ActionEvent e) {		
		Object obj=(Object)e.getSource();
		if (obj==bt_check) {
			main.connect();			
			
			check();
		}else if(obj==bt_regist){
			main.setPage(1); //RegAdminPanel보이게 한다.
		}
	}

	public void itemStateChanged(ItemEvent e) {
		Object obj=e.getSource();
		if (obj==ch_guest) {
			p_input_guest.setVisible(true);;
			p_input_member.setVisible(false);
			isGuest=true;
		}else if (obj==ch_member) {
			p_input_guest.setVisible(false);;
			p_input_member.setVisible(true);
			isGuest=false;
		}
		
	}	
	
}
