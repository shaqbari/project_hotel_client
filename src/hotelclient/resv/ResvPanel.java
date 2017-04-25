package hotelclient.resv;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;
import hotelclient.home.Room_Option;
import hotelclient.network.ResvRequest;
import hotelclient.network.RoomSearchRequest;

public class ResvPanel extends JPanel implements ActionListener, ItemListener{
	ClientMain main;
	Connection con;
	int count=0; //달력클릭할때마다 증가할 변수 두번만 선택할수 있게 한다!
	int stay=1; //머무를 날자
	
	public static final int WIDTH=1100;
	public static final int  HEIGHT=900;
	
	
	ArrayList<Room_Option> optionInfo=new ArrayList<Room_Option>();
	
	public JPanel p_center, p_center_north, p_east, p_east_option, p_east_room, p_room_title, p_room_button, p_east_resv;
	
	//p_center_north에 붙을 예정
	JLabel desc;
	JButton bt_refrash;	
	
	MyCalendar myCalendar;//p_center에 붙을 예정
	
	//p_east_option에 붙을 예정
	JLabel la_optionSelect;
	JButton bt_search;
	CheckboxGroup group;
	Checkbox deluxe, business, grand, first, vip, vvip, sweet;
	Checkbox[] boxs=new Checkbox[7];
	
	//p_east_resv에 붙을 예정
	JLabel la_resv;
	JLabel la_start, la_start_input, la_end, la_end_input, la_stay, la_stay_input, la_option, la_option_input,  la_room_number, la_room_number_input, la_price, la_price_input;
	JLabel[] resvInfo =new JLabel[12];
	JButton bt_resv;
	
	//p_east_room에 붙을 예정
	JLabel la_room;
	
	Dimension size=new Dimension(WIDTH/6-10, 40);
	Font font=new Font("맑은 고딕", Font.PLAIN, 15);
	
	public ResvPanel(ClientMain main) {
		this.main=main;
		con=main.con;
		
		p_center=new JPanel();
		p_center_north=new JPanel();
		p_east=new JPanel();
		p_east_option=new JPanel();
		p_east_room=new JPanel();
		p_room_title=new JPanel();
		p_room_button=new JPanel();
		p_east_resv=new JPanel();
		
		desc=new JLabel("  1. 날짜를 선택하세요.(해당날짜선택시 당일14:00부터 익일 12:00까지 예약됩니다.)");
		bt_refrash=new JButton("처음부터 다시 예약");
		
		p_center.setPreferredSize(new Dimension(WIDTH*2/3-10, HEIGHT));
		p_center_north.setPreferredSize(new Dimension(WIDTH*2/3-10, 50));
		p_east.setPreferredSize(new Dimension(WIDTH/3-10, HEIGHT));
		p_east_option.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*3/7-10));
		p_east_room.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*1/7-10));	
		p_east_resv.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*4/7-10));
		
		la_optionSelect=new JLabel("2. 옵션 선택");
		bt_search=new JButton("잔여 객실 검색");
		group=new CheckboxGroup();
		boxs[0]=deluxe=new Checkbox("deluxe", group, false);
		boxs[1]=business=new Checkbox("business", group, false);
		boxs[2]=grand=new Checkbox("grand", group, false);
		boxs[3]=first=new Checkbox("first", group, false);
		boxs[4]=vip=new Checkbox("vip", group, false);
		boxs[5]=vvip=new Checkbox("vvip", group, false);
		boxs[6]=sweet=new Checkbox("sweet", group, false);
		
		la_optionSelect.setPreferredSize(new Dimension(WIDTH/3-150, 40));
		
		la_room=new JLabel("  3. 방 선택");
		
		la_resv=new JLabel("  4. 예약확인");
		resvInfo[0]=la_start=new JLabel("입실예약일 : ");
		resvInfo[1]=la_start_input=new JLabel();
		resvInfo[2]=la_end=new JLabel("퇴실예약일 : ");
		resvInfo[3]=la_end_input=new JLabel();
		resvInfo[4]=la_stay=new JLabel("머무르는기간");
		resvInfo[5]=la_stay_input=new JLabel();
		resvInfo[6]=la_option=new JLabel("방옵션 : ");
		resvInfo[7]=la_option_input=new JLabel();
		resvInfo[8]=la_room_number=new JLabel("호실 : ");
		resvInfo[9]=la_room_number_input=new JLabel();
		resvInfo[10]=la_price=new JLabel("가격 : ");
		resvInfo[11]=la_price_input=new JLabel();		
		bt_resv=new JButton("예약");
		
		p_center_north.add(bt_refrash);
		p_center_north.add(desc);
				
		p_east_option.add(la_optionSelect);
		p_east_option.add(bt_search);
		for (int i = 0; i < boxs.length; i++) {
			p_east_option.add(boxs[i]);
		}
		
		
		la_room.setPreferredSize(new Dimension(WIDTH/3, 40));
		p_room_title.add(la_room);
		
		p_east_room.setLayout(new BorderLayout());		
		p_east_room.add(p_room_title, BorderLayout.NORTH);
		p_east_room.add(p_room_button);
		
		
		
		la_resv.setPreferredSize(new Dimension(WIDTH/3, 40));
		p_east_resv.add(la_resv);		
		for (int i = 0; i < resvInfo.length; i++) {
			resvInfo[i].setPreferredSize(size);
			resvInfo[i].setFont(font);
			p_east_resv.add(resvInfo[i]);			
		}
		p_east_resv.add(bt_resv);
		
		this.setLayout(new BorderLayout());
		p_center.setLayout(new BorderLayout());
		
		myCalendar=new MyCalendar(this);//ResvPanel의 크기가 결정되고나서 캘린더 생성해야 처음사이즈가 myCalendar에 전달된다.
		
		p_center.add(p_center_north, BorderLayout.NORTH);
		p_center.add(myCalendar);
		p_east.add(p_east_option);
		p_east.add(p_east_room);
		p_east.add(p_east_resv);
		
		add(p_center, BorderLayout.WEST);
		add(p_east);
		
		bt_refrash.addActionListener(this);
		bt_search.addActionListener(this);
		bt_resv.addActionListener(this);
		
		for (int i = 0; i < boxs.length; i++) {
			boxs[i].addItemListener(this);
			
		}
		
		p_east_option.setBackground(Color.LIGHT_GRAY);
		p_room_title.setBackground(Color.LIGHT_GRAY);
		p_room_button.setBackground(Color.LIGHT_GRAY);
		p_east_resv.setBackground(Color.LIGHT_GRAY);
		
		getOptionInfo();
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setVisible(false);	
	}

	public void refrash(){
		//안보이게 하고 새로운 패널생성후 이패널을 지운다.
		this.setVisible(false);
		ResvPanel resvPanel=new ResvPanel(main);				
		resvPanel.setVisible(true);
		main.p_center.add(resvPanel);
		main.resvPanel=resvPanel;//메인의 패널은 여기서 새로만든 패널이 된다.
		main.p_center.remove(this);
	}
	
	public void search(){
		System.out.println("방검색 눌렀어?");
		//옵션선택 버튼 다시 선택못하게 막자
		for (int i = 0; i < boxs.length; i++) {
			boxs[i].setEnabled(false);
		}
		//서버에 요청 보낸다.
		RoomSearchRequest searchReqeust=new RoomSearchRequest(main);		
		searchReqeust.requestJSON(la_start_input.getText(), la_end_input.getText(), la_option_input.getText());
	}
	
	public void getOptionInfo(){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from room_option";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				Room_Option dto=new Room_Option();
				dto.setRoom_option_id(rs.getInt("Room_option_id"));
				dto.setRoom_option_name(rs.getString("Room_option_name"));
				dto.setRoom_option_size(rs.getInt("Room_option_size"));
				dto.setRoom_option_bed(rs.getString("Room_option_bed"));
				dto.setRoom_option_view(rs.getString("Room_option_view"));
				dto.setRoom_option_max(rs.getInt("Room_option_max"));
				dto.setRoom_option_img(rs.getString("Room_option_img"));
				dto.setRoom_option_price(rs.getInt("Room_option_price"));
				
				optionInfo.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void resv(){
		ResvRequest resvRequest=new ResvRequest(main);
		resvRequest.requestJSON("", "", "");
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if (obj==bt_refrash) {
			refrash();			
		}else if (obj==bt_search) {
			search();			
		}else if (obj==bt_resv) {
			resv();
		}
	}

	public void itemStateChanged(ItemEvent e) {
		Object obj=e.getSource();
		for (int i = 0; i < boxs.length; i++) {
			if (obj==boxs[i]) {
				la_option_input.setText(boxs[i].getLabel());
			}
		}
	}
}
