/*객실 1건의 디스플레이*/
package hotelclient.home;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RoomPanel extends JPanel{
	HomePanel home;
	Canvas can;
	JLabel la;
//	JButton bt_detail;
	BufferedImage image;
	URL url;
	int width=120; int height=150;
	String[] room_type={"deluxe","business","grand","first","vip","vvip", "sweet"};
	ArrayList<Room_Option> list;	

	
	public RoomPanel(URL url, HomePanel home) {
		this.url=url;
		this.home=home;
		
		try {
			image=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		can=new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, width, height, this);
			}
		};
		

		for(int i=0; i<room_type.length; i++){
			la=new JLabel(room_type[i]);
			
		}
		
		//bt_detail=new JButton("+");
		
		setLayout(new BorderLayout());
		
		add(la, BorderLayout.NORTH);
		add(can);
		//add(bt_detail, BorderLayout.SOUTH);
	
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(width, height+45));
		
		
		
		la.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				
					new Room_Detail();
				}
			
		});
		
	}
	
}
