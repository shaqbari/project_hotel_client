/*°´½Ç 1°ÇÀÇ µð½ºÇÃ·¹ÀÌ*/
package hotelclient.home;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hotelclient.ClientMain;


public class RoomPanel extends JPanel{
	HomePanel home;
	Canvas can;
	JLabel la;
//	JButton bt_detail;
	BufferedImage image;
	URL url;
	int width=150; int height=190;
	//String[] room_type={"deluxe","business","grand","first","vip","vvip", "sweet"};
	ArrayList<Room_Option> list;
	String room_type;	
	ClientMain main;
	Connection con;
	Font font=new Font("¸¼Àº °íµñ", Font.BOLD,16);
	
	public RoomPanel(URL url, HomePanel home,String room_type) {
		this.url=url;
		this.home=home;
		this.room_type=room_type;
		main=home.main;
		con=main.con;
		la=new JLabel(room_type);
		
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
		
		la.setFont(font);
		la.setForeground(Color.BLACK);

		/*for(int i=0; i<room_type.length; i++){
			la=new JLabel(room_type[i]);
			
		}*/
		
		//bt_detail=new JButton("+");
		add(la);
		add(can);
		
		//add(bt_detail, BorderLayout.SOUTH);
	
		//setBackground(Color.WHITE);
		can.setPreferredSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height+30));
		
		can.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				
					new Room_Detail(con,room_type);
				}
			
		});
		
	}
	
}
