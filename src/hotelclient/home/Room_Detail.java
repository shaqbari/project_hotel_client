package hotelclient.home;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Room_Detail extends JFrame{
	Room_Option room;
	RoomPanel rp;
	JPanel p_north, p_center, p_east;
	JLabel  la_center;
	Canvas can;
	Image img;
	
	
	public Room_Detail() {
	
		
		p_north=new JPanel();
		p_center=new JPanel();
		p_east=new JPanel();
		la_center=new JLabel("���� ����");
		this.setTitle("������");
		
		p_east.setLayout(new BorderLayout());
		
		can=new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 530, 400, this);
			}
		};
		p_north.setBackground(Color.DARK_GRAY);
		p_center.setBackground(Color.WHITE);
		
		la_center.setFont(new Font("���� ���", Font.BOLD, 17));
		
		can.setPreferredSize(new Dimension(530, 400));
		
		p_center.add(la_center);
		p_east.add(can);
	
		
		add(p_center);
		add(p_east, BorderLayout.EAST);
		
		setSize(750,450);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	
	
	
	public void view() {	
		room=new Room_Option();
		
		try {
			String number = Integer.toString(room.getRoom_number());
			String name = room.getRoom_option_name();
			String size = Integer.toString(room.getRoom_option_size());
			String bed = room.getRoom_option_bed();
			String view = room.getRoom_option_view();
			String max = Integer.toString(room.getRoom_option_max());
			String price = Integer.toString(room.getRoom_option_price());
			
			URL url=this.getClass().getResource("/"+room.getRoom_option_img());
			img = ImageIO.read(url);
	
					
			la_center.setText("<html>"+"<br> ���̸� : "+name+"<br> ������ : "+size+"�� <br> ��ħ�� : " +bed+"<br> ������ �� : "+view+"<br> ���ִ� �ο��� : "+max+"�� <br> ������ : "+price+"�� </html>");
								
		} catch (IOException e) {
			e.printStackTrace();
		
	}
}

}

