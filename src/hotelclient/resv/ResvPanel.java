package hotelclient.resv;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hotelclient.ClientMain;
import hotelclient.home.Room_Option;
import hotelclient.network.GuestResvRequest;
import hotelclient.network.MemberResvRequest;
import hotelclient.network.RoomSearchRequest;

public class ResvPanel extends JPanel implements ActionListener, ItemListener{
	ClientMain main;
	Connection con;
	int count=0; //�޷�Ŭ���Ҷ����� ������ ���� �ι��� �����Ҽ� �ְ� �Ѵ�!
	int stay=1; //�ӹ��� ����
	
	public static final int WIDTH=1100;
	public static final int  HEIGHT=900;
	
	
	ArrayList<Room_Option> optionInfo=new ArrayList<Room_Option>();
	
	public JPanel p_center, p_center_north, p_east;
	
	//p_east�� ���� �г�
	public JPanel p_east_option, p_option_select, p_option_info;
	public JPanel p_east_room, p_room_title, p_room_button;
	public JPanel p_east_resv;
	
	//p_center_north�� ���� ����
	JLabel desc;
	JButton bt_refrash;	
	
	MyCalendar myCalendar;//p_center�� ���� ����
	
	//p_east_option�� ���� ����
	//p_option_select�� ���� ����
	JLabel la_optionSelect;
	JButton bt_search;
	CheckboxGroup group;
	Checkbox deluxe, business, grand, first, vip, vvip, suite;
	Checkbox[] boxs=new Checkbox[7];//��ư ���� �迭
	
	/*-------------------------------------------------------------*/
	//p_option_info�� ���� �����ΰ��� �����Ѵ�.
	//JLabel la_option_bed, la_bed_input;
	JPanel p_option_detail, p_option_img;
	JLabel la_option_detail;
	Canvas can;
	
	//p_option_select�� �ٿ��� ���Ʒ� �г�
	JPanel p_option_select_north, p_option_select_south;
	//p_option_select �Ʒ� ���� ���� ���� �г�
	JPanel p_blank;
	
	/*-------------------------------------------------------------*/
	
	//p_east_resv�� ���� ����
	JLabel la_resv;
	public JLabel la_start, la_start_input, la_end, la_end_input, la_stay, la_stay_input, la_option, la_option_input,  la_room_number, la_room_number_input, la_price, la_price_input;
	//��ȸ���ϰ�� �߰��� ���ϰ͵�	
	JLabel[] resvInfo =new JLabel[12];
	JButton bt_resv;
	//��ȸ�� �гο� ���� ��ü��	
	JLabel la_name, la_phone, la_phone_spacer1, la_phone_spacer2;
	public JTextField txt_name, txt_phone1, txt_phone2, txt_phone3;
	public ArrayList<Component> guestInfo=new ArrayList<Component>();//���� ��ü�� ���� cf 
	
	
	//p_east_room�� ���� ����
	JLabel la_room;
	
	Dimension size=new Dimension(WIDTH/6-10, 25);
	Font font=new Font("���� ���", Font.PLAIN, 15);
	
	
	ColorThread colorThread;//�ι�° Ŭ���� ��ĥ�ϴµ� ���� ������
	
	//������ ����
	public ResvPanel(ClientMain main) {
		this.main=main;
		con=main.con;
		
		p_center=new JPanel();
		p_center_north=new JPanel();
		p_east=new JPanel();
		p_option_select=new JPanel();
		p_east_option=new JPanel();
		p_option_info=new JPanel();
		p_east_room=new JPanel();
		p_room_title=new JPanel();
		p_room_button=new JPanel();
		p_east_resv=new JPanel();
		
		desc=new JLabel("  1. ��¥�� �����ϼ���.(�ش糯¥���ý� ����14:00���� ���� 12:00���� ����˴ϴ�.)");
		bt_refrash=new JButton("ó������ �ٽ� ����");
		
		p_center.setPreferredSize(new Dimension(WIDTH*2/3-10, HEIGHT));
		p_center_north.setPreferredSize(new Dimension(WIDTH*2/3-10, 50));
		p_east.setPreferredSize(new Dimension(WIDTH/3-10, HEIGHT));
		p_east_option.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*3/7-10));
		p_option_select.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*1/7-10));
		p_option_info.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*2/7-10));		
		p_east_room.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*1/7-10));	
		p_east_resv.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*4/7-10));
		
		
		
		la_optionSelect=new JLabel("2. �ɼ� ����");
		bt_search=new JButton("3. �ܿ� ���� �˻�");
		group=new CheckboxGroup();
		boxs[0]=deluxe=new Checkbox("deluxe", group, false);
		boxs[1]=business=new Checkbox("business", group, false);
		boxs[2]=grand=new Checkbox("grand", group, false);
		boxs[3]=first=new Checkbox("first", group, false);
		boxs[4]=vip=new Checkbox("vip", group, false);
		boxs[5]=vvip=new Checkbox("vvip", group, false);
		boxs[6]=suite=new Checkbox("suite", group, false);
		
		la_optionSelect.setPreferredSize(new Dimension(WIDTH/3-150, 40));
		
		/*-------------------------------------------------------------*/
		p_option_select_north = new JPanel();
		p_option_select_south = new JPanel();
		p_blank = new JPanel();
		//p_option_info�� ���ϰ��� �ʱ�ȭ�Ѵ�.
		p_option_detail = new JPanel();
		la_option_detail = new JLabel();
		p_option_img = new JPanel();
		
		//p_option_select_north.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*1/7-7));
		//p_option_select_south.setPreferredSize(new Dimension(WIDTH/3, HEIGHT*1/7-5));
		
		/*-------------------------------------------------------------*/
				
		la_room=new JLabel("  4. �� ����");
		
		if (main.checkAdminPanel.isGuest) {
			la_resv=new JLabel("  5. ��ȸ������Ȯ��");			
			
		}else {
			la_resv=new JLabel("  5. ȸ������Ȯ��");			
			
		}
		resvInfo[0]=la_start=new JLabel("�Խǿ����� : ");
		resvInfo[1]=la_start_input=new JLabel();
		resvInfo[2]=la_end=new JLabel("��ǿ����� : ");
		resvInfo[3]=la_end_input=new JLabel();
		resvInfo[4]=la_stay=new JLabel("�ӹ����±Ⱓ");
		resvInfo[5]=la_stay_input=new JLabel();
		resvInfo[6]=la_option=new JLabel("��ɼ� : ");
		resvInfo[7]=la_option_input=new JLabel("deluxe");
		resvInfo[8]=la_room_number=new JLabel("ȣ�� : ");
		resvInfo[9]=la_room_number_input=new JLabel();
		resvInfo[10]=la_price=new JLabel("���� : ");
		resvInfo[11]=la_price_input=new JLabel();		
		bt_resv=new JButton("����");
		
		guestInfo.add(la_name=new JLabel("�̸� "));
		guestInfo.add(txt_name=new JTextField());
		guestInfo.add(la_phone=new JLabel("��ȭ��ȣ "));
		guestInfo.add(txt_phone1=new JTextField());
		guestInfo.add(la_phone_spacer1=new JLabel(" -"));
		guestInfo.add(txt_phone2=new JTextField());
		guestInfo.add(la_phone_spacer2=new JLabel(" -"));		
		guestInfo.add(txt_phone3=new JTextField());
		
		int width1=280;
		int width2=70;
		int height=20;		
		la_name.setPreferredSize(new Dimension(width2, height));
		txt_name.setPreferredSize(new Dimension(width1, height));
		la_phone.setPreferredSize(new Dimension(width2, height));
		txt_phone1.setPreferredSize(new Dimension(width2, height));
		la_phone_spacer1.setPreferredSize(new Dimension(25, height));
		txt_phone2.setPreferredSize(new Dimension(width2, height));
		la_phone_spacer2.setPreferredSize(new Dimension(25, height));
		txt_phone3.setPreferredSize(new Dimension(width2, height));
		
		
		p_center_north.add(bt_refrash);
		p_center_north.add(desc);
				
		p_option_select.add(la_optionSelect);
		p_option_select.add(bt_search);
		/*
		for (int i = 0; i < boxs.length; i++) {
			p_option_select.add(boxs[i]);
			boxs[i].setFont(font);
		}
		*/
		/*--------------------------------------------------------------------*/
		//select ��ư ���� ���̱�
		for (int i = 0; i < boxs.length-3; i++) {
			p_option_select_north.add(boxs[i]);
			boxs[i].setFont(font);
		}
		for (int i = boxs.length-3; i < boxs.length; i++) {
			p_option_select_south.add(boxs[i]);
			boxs[i].setFont(font);
		}
		p_option_select.add(p_option_select_north);
		p_option_select.add(p_option_select_south);
		p_option_select_north.setBackground(Color.LIGHT_GRAY);
		p_option_select_south.setBackground(Color.LIGHT_GRAY);
		p_blank.setBackground(Color.LIGHT_GRAY);
		/*--------------------------------------------------------------------*/
		
		/*-------------------------------------------------------------*/
		//p_option_info�� ���δ�.

		p_option_info.setLayout(new BorderLayout());
		p_option_detail.setLayout(new BorderLayout());
		p_option_img.setLayout(new BorderLayout());

		p_option_info.add(p_option_img);
		p_option_detail.add(la_option_detail);
		p_option_info.add(p_option_detail, BorderLayout.EAST);
		p_option_detail.setPreferredSize(new Dimension(150, 300));
		
		p_option_img.setBackground(Color.LIGHT_GRAY);
		p_option_detail.setBackground(Color.LIGHT_GRAY);
		
		/*-------------------------------------------------------------*/
		
		la_room.setPreferredSize(new Dimension(WIDTH/3, 40));
		p_room_title.add(la_room);
		
		p_east_room.setLayout(new BorderLayout());		
		p_east_room.add(p_room_title, BorderLayout.NORTH);
		p_east_room.add(p_room_button);
		
		p_east_option.setLayout(new BorderLayout());		
		p_east_option.add(p_option_select, BorderLayout.NORTH);
		p_east_option.add(p_blank);
		p_east_option.add(p_option_info,BorderLayout.SOUTH);
		
		
		la_resv.setPreferredSize(new Dimension(WIDTH/3, 40));
		p_east_resv.add(la_resv);		
		for (int i = 0; i < resvInfo.length; i++) {
			resvInfo[i].setPreferredSize(size);
			resvInfo[i].setFont(font);
			p_east_resv.add(resvInfo[i]);			
		}
		
		//��ȸ�� �߰� �������� �Է¶� ���δ�.
		for (int i = 0; i < guestInfo.size(); i++) {
			p_east_resv.add(guestInfo.get(i));
			//ȸ���̶�� �Ⱥ��̰� �Ѵ�.//�̷��� �ϸ� �ʱ�ȭ�Ǿ��ִ´�� ���͹�����.
			//System.out.println(main.checkAdminPanel.isGuest);
			/*if (!main.checkAdminPanel.isGuest) {
				guestInfo.get(i).setVisible(false);
			}*/
			//ȸ���α��ν� �Ⱥ��̰�����. �α׾ƿ��� ����غ�ȸ���α��ν� ���̰��ؾ��Ѵ�. reflash�Ҷ��� ����
			
		}		
				
		p_east_resv.add(bt_resv);
		
		this.setLayout(new BorderLayout());
		p_center.setLayout(new BorderLayout());
		
		myCalendar=new MyCalendar(this);//ResvPanel�� ũ�Ⱑ �����ǰ��� Ķ���� �����ؾ� ó������� myCalendar�� ���޵ȴ�.
		
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
		
		p_option_select.setBackground(Color.LIGHT_GRAY);
		p_option_info.setBackground(Color.LIGHT_GRAY);		
		p_room_title.setBackground(Color.LIGHT_GRAY);
		p_room_button.setBackground(Color.LIGHT_GRAY);
		p_east_resv.setBackground(Color.LIGHT_GRAY);
		
		//colorThread=new ColorThread(this); 
		
		
		getOptionInfo();
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setVisible(false);	
	}

	public void refrash(){
		//�Ⱥ��̰� �ϰ� ���ο� �гλ����� ���г��� �����.
		this.setVisible(false);
		ResvPanel resvPanel=new ResvPanel(main);				
		resvPanel.setVisible(true);		
		main.p_center.add(resvPanel);
		
		//ȸ���̶�� ��ȸ�� �߰����� �Ⱥ��̰� �Ѵ�. 
		for (int i = 0; i < resvPanel.guestInfo.size(); i++) {
			if (!main.checkAdminPanel.isGuest) {
				resvPanel.guestInfo.get(i).setVisible(false);
			} 
		}
		main.resvPanel=resvPanel;//������ �г��� ���⼭ ���θ��� �г��� �ȴ�.
		main.p_center.remove(this);
	}
	
	//������� �ǽð� �÷��� �����غ���.
	
	public void search(){
		System.out.println("��˻� ������?");
		//�ɼǼ��� ��ư �ٽ� ���ø��ϰ� ����
		for (int i = 0; i < boxs.length; i++) {
			boxs[i].setEnabled(false);
		}
		
		//�˻���ư�� ���´�.
		bt_search.setEnabled(false);
		
		//������ ��û ������.
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
		if (main.checkAdminPanel.isGuest) {//ȸ���̶�� �߰� ������������ Ȯ��
			if(check()&&plusCheck()){
				guestResv();
				bt_resv.setEnabled(false);
			}				
		}else{
			if (check()) {			
				memberResv();				
				bt_resv.setEnabled(false);
			}
		}		
	}
	
	
	public void memberResv(){
		MemberResvRequest resvRequest=new MemberResvRequest(main);
		resvRequest.requestJSON(main.hotel_user_id, Integer.parseInt(la_room_number_input.getText()), la_start_input.getText(), la_end_input.getText(), stay);
		
	}
	
	public void guestResv(){
		GuestResvRequest guestRequest=new GuestResvRequest(main);
		guestRequest.requestJSON(main.hotel_user_id, Integer.parseInt(la_room_number_input.getText()), la_start_input.getText(), la_end_input.getText(), stay);
		
	}
	
	//��������Ȯ��
	public boolean check(){
		if (la_start_input.getText().length()==0) {
			JOptionPane.showMessageDialog(this, "��¥�� �������� �ʾҽ��ϴ�.");
			return false;
		}
		if (la_room_number_input.getText().length()==0) {
			JOptionPane.showMessageDialog(this, "���� �������� �ʾҽ��ϴ�.");
			return false;//���� ������ false�� ��ȯ����
		}
		
		return true;
	}
	
	//��ȸ�� �߰� �������� Ȯ��
	public boolean plusCheck(){
		if (txt_name.getText().length()==0) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է����� �ʾҽ��ϴ�.");
		}
		if (txt_phone1.getText().length()==0||txt_phone2.getText().length()==0||txt_phone3.getText().length()==0) {
			JOptionPane.showMessageDialog(this, "�޴���ȭ ��ȣ�� �Է����� �ʾҽ��ϴ�.");			
		}
		
		return true;
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
				
				/*-------------------------------------------------------------*/
				//������ ��ư�� ���� dto�� ���� ArrayList�� optionInfo�� �̿��Ͽ� p_option_info�� �ٲ��ش�.
				
				for (int j = 0; j < optionInfo.size(); j++) {
					if (boxs[i].getLabel().equalsIgnoreCase(optionInfo.get(j).getRoom_option_name())) {
						try {
							String size = Integer.toString(optionInfo.get(j).getRoom_option_size());
							String bed = optionInfo.get(j).getRoom_option_bed();
							String view = optionInfo.get(j).getRoom_option_view();
							String max = Integer.toString(optionInfo.get(j).getRoom_option_max());
							String price = Integer.toString(optionInfo.get(j).getRoom_option_price());
							URL url = new URL("http://pseudoluna.synology.me/experi/images/"+optionInfo.get(j).getRoom_option_img());			
							Image img = ImageIO.read(url);
							
							//������ ����� �ٽ� �׸���. �׸��� ���̴� ������ �ָ��� item�̺�Ʈ �߻� �� ������ �׸��� ������ ����
							p_option_img.removeAll();
							can = new Canvas(){
								public void paint(Graphics g) {
									g.drawImage(img, 0, 0, 200, 200, this);
								}
							};
							can.setPreferredSize(new Dimension(200, 200));
							p_option_img.add(can);	
							la_option_detail.setText("<html>������ : "+size+"�� <br> ��ħ�� : "+bed+"<br> ������ �� : "+view+"<br>���ִ��ο� : "+max+"�� <br>������ :"+price+"�� </html>");		
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					}			
				}				
				
				/*-------------------------------------------------------------*/			
			}	
		}	
	}
}
