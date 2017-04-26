package hotelclient.service;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;

public class Serv_resvOrder extends JFrame implements ActionListener{
	ClientMain main;
	Connection con;
	JPanel p_info,p_order;
	String serv_name;
	JLabel la_name,la_price;
	ImageIcon icon;
	JButton bt,bt_confirm;
	ArrayList<Service> service = new ArrayList<Service>();
	
	String service_id;
	String service_name;
	String service_img;
	String service_price;
	
	public Serv_resvOrder(ClientMain main,String serv_name,ImageIcon icon) {
		this.main=main;
		this.setLayout(new BorderLayout());
		this.serv_name=serv_name;
		this.icon=icon;
		con=main.con;
		
		p_info = new JPanel();
		p_order = new JPanel();
		
		//info
		la_name = new JLabel(serv_name);
		la_name.setFont(new Font("Georgia", Font.BOLD, 30));
		bt = new JButton();
		bt.setIcon(icon);
		
		bt.setBorderPainted(false);
		bt.setContentAreaFilled(false);
		bt.setFocusPainted(false);
		bt.setOpaque(false);
		
		p_info.add(la_name);
		p_info.add(bt);
		
		getList();
		
		la_price.setFont(new Font("맑은고딕",Font.BOLD,30));
		p_info.add(la_price);
		
		//여기서부터 예약날짜 시간 택할 수 있게 checkbox 넣자
		
		
		//p_order
		bt_confirm = new JButton("확인/예약");
		//bt_resvOrder = new JButton("예약주문");
		p_order.add(bt_confirm);
		//p_order.add(bt_resvOrder);
		
		add(p_info,BorderLayout.CENTER);
		add(p_order,BorderLayout.SOUTH);
		
		bt_confirm.addActionListener(this);
		//bt_resvOrder.addActionListener(this);
		
		setVisible(true);
		setSize(new Dimension(300,600));
	}
	
	//db연동
	public void getList(){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			StringBuffer sql = new StringBuffer();
			sql.append("select to_char(service_id,'0') as service_id,service_name,service_img,to_char(service_price,'00000') as service_price from service");
			sql.append(" where service_name="+"'"+serv_name+"'");
			//System.out.println(sql.toString());
			try {
				pstmt=con.prepareStatement(sql.toString());
				rs=pstmt.executeQuery();
				while(rs.next()){
					//System.out.println(rs.getString("service_id"));
					//System.out.println(rs.getString("service_name"));
					//System.out.println(rs.getString("service_img"));
					//System.out.println(rs.getString("service_price"));
					service_id=rs.getString("service_id");
					service_name=rs.getString("service_name");
					service_img=rs.getString("service_img");
					service_price=rs.getString("service_price");
					
					la_price = new JLabel(service_price+"원");
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	//주문
	public void order(){
		
		Calendar cal=Calendar.getInstance();
		String yyyy=Integer.toString(cal.get(Calendar.YEAR));
		String mm=Integer.toString(cal.get(Calendar.MONTH));
		String dd=Integer.toString(cal.get(Calendar.DATE));
		String hh24=Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		String mi=Integer.toString(cal.get(Calendar.MINUTE));
		String ss=Integer.toString(cal.get(Calendar.SECOND));
		
		//일단은 이렇게..
		String msg=serv_name;
		System.out.println(msg);
		
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"requestType\":\"service\",");
		json.append("\"room_number\":"+main.room_Number+",");
		json.append("\"requestTime\":\""+yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss+"\",");
		json.append("\"hotel_user_id\":"+main.hotel_user_id+"");			
		json.append("\"content\":\""+msg+"\"");			
		json.append("}");	
		
		//System.out.println(json.toString());
		
		main.clientThread.send(json.toString());
	}
	
	//확인_예약버튼 누르면 예약이 완료된다.
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==bt_confirm){
			order();
		}
	}

}
