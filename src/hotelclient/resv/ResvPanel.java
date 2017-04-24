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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class ResvPanel extends JPanel implements ActionListener, ItemListener{
	ClientMain main;
	
	int count=0; //달력클릭할때마다 증가할 변수 두번만 선택할수 있게 한다!
	
	public static final int WIDTH=1100;
	public static final int  HEIGHT=900;
	JPanel p_center, p_center_north, p_east, p_east_option, p_east_room, p_east_resv;
	
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
	JLabel la_start, la_start_input, la_end, la_end_input, la_option, la_option_input,  la_room_number, la_room_number_input, la_price, la_price_input;
	JLabel[] resvInfo =new JLabel[10];
	JButton bt_resv;
	
	Dimension size=new Dimension(WIDTH/6-10, 40);
	Font font=new Font("맑은 고딕", Font.PLAIN, 15);
	
	public ResvPanel(ClientMain main) {
		this.main=main;
		
		p_center=new JPanel();
		p_center_north=new JPanel();
		p_east=new JPanel();
		p_east_option=new JPanel();
		p_east_room=new JPanel();
		p_east_resv=new JPanel();
		
		desc=new JLabel("  1. 날짜를 선택하세요.(해당날짜선택시 당일14:00부터 익일 12:00까지 예약됩니다.)");
		bt_refrash=new JButton("처음부터 다시 예약");
		
		p_center.setPreferredSize(new Dimension(WIDTH*2/3-10, HEIGHT));
		p_center_north.setPreferredSize(new Dimension(WIDTH*2/3-10, 50));
		p_east.setPreferredSize(new Dimension(WIDTH/3-10, HEIGHT));
		p_east_option.setPreferredSize(new Dimension(WIDTH/3, HEIGHT/6-10));
		p_east_room.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*2/6-10));		
		p_east_resv.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*3/6-10));
		
		la_optionSelect=new JLabel("2. 옵션 선택");
		bt_search=new JButton("잔여 객실 검색");
		group=new CheckboxGroup();
		boxs[0]=deluxe=new Checkbox("deluxe", group, true);
		boxs[1]=business=new Checkbox("business", group, false);
		boxs[2]=grand=new Checkbox("grand", group, false);
		boxs[3]=first=new Checkbox("first", group, false);
		boxs[4]=vip=new Checkbox("vip", group, false);
		boxs[5]=vvip=new Checkbox("vvip", group, false);
		boxs[6]=sweet=new Checkbox("sweet", group, false);
		
		la_optionSelect.setPreferredSize(new Dimension(WIDTH/3-150, 40));
		
		la_resv=new JLabel("예약정보");
		resvInfo[0]=la_start=new JLabel("입실예약일 : ");
		resvInfo[1]=la_start_input=new JLabel();
		resvInfo[2]=la_end=new JLabel("퇴실예약일 : ");
		resvInfo[3]=la_end_input=new JLabel();
		resvInfo[4]=la_option=new JLabel("방옵션 : ");
		resvInfo[5]=la_option_input=new JLabel();
		resvInfo[6]=la_room_number=new JLabel("호실 : ");
		resvInfo[7]=la_room_number_input=new JLabel();
		resvInfo[8]=la_price=new JLabel("가격 : ");
		resvInfo[9]=la_price_input=new JLabel();		
		bt_resv=new JButton("예약");
		
		p_center_north.add(bt_refrash);
		p_center_north.add(desc);
				
		p_east_option.add(la_optionSelect);
		p_east_option.add(bt_search);
		for (int i = 0; i < boxs.length; i++) {
			p_east_option.add(boxs[i]);
		}
		
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
		p_east_room.setBackground(Color.LIGHT_GRAY);		
		p_east_resv.setBackground(Color.LIGHT_GRAY);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setVisible(false);	
	}

	public void actionPerformed(ActionEvent e) {
		
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
