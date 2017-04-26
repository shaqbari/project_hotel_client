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

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;



public class Room_Detail extends JFrame{
	ClientMain main;
	Room_Option room;
	RoomPanel rp;
	JPanel p_north, p_center, p_east;
	JLabel  la_center;
	Canvas can;
	Image img;
	String room_type,room_id,room_name,room_size,room_bed,room_view,room_max,room_img,room_price;
	Connection con;
	
	public Room_Detail(Connection con,String room_type) {
		this.con=con;
		
		this.room_type=room_type;
		p_north=new JPanel();
		p_center=new JPanel();
		p_east=new JPanel();
		la_center=new JLabel("객실 정보");
		this.setTitle("상세정보");
		
		p_east.setLayout(new BorderLayout());
		
		can=new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 530, 400, this);
			}
		};
		can.setPreferredSize(new Dimension(530, 400));
		
		p_north.setBackground(Color.DARK_GRAY);
		p_center.setBackground(Color.WHITE);
		
		view();
		
		la_center.setText("<html>"+"<br> ㆍ이름 : "+room_name+"<br> ㆍ평형 : "+room_size+"평 <br> ㆍ침대 : " +room_bed+"<br> ㆍ객실 뷰 : "+room_view+"<br> ㆍ최대 인원수 : "+room_max+"명 <br> ㆍ가격 : "+room_price+"원 </html>");
		la_center.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		
		p_center.add(la_center);
		p_east.add(can);
	
		add(p_center);
		add(p_east, BorderLayout.EAST);
		
		setSize(750,450);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void view() {	
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sql = new StringBuffer();
		sql.append("select room_option_id,room_option_name,room_option_size,room_option_bed,room_option_view,");
		sql.append(" room_option_max,room_option_img,room_option_price from room_option");
		sql.append(" where room_option_name="+"'"+room_type+"'");
		
		//room=new Room_Option();
		
		//System.out.println(sql.toString());
		//System.out.println(con.toString());
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()){	
				room_id=rs.getString("room_option_id");
				room_name=rs.getString("room_option_name");
				room_size=rs.getString("room_option_size");
				room_bed=rs.getString("room_option_bed");
				room_view=rs.getString("room_option_view");
				room_max=rs.getString("room_option_max");
				room_img=rs.getString("room_option_img");
				room_price=rs.getString("room_option_price");
			}	
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally {
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
		
		try {	
			URL url=new URL("http://pseudoluna.synology.me/experi/images/"+room_img);
			System.out.println(url);
			img = ImageIO.read(url);

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}

