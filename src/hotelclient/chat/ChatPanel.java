package hotelclient.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hotelclient.ClientMain;
import hotelclient.ClientThread;

public class ChatPanel extends JPanel {
	ClientMain main;			
	Connection con;	
	
	public JTextArea area;
	JScrollPane scroll;
	JTextField txt_input;
	
	public ChatPanel(ClientMain main) {
		this.main=main;		
		
		this.con=main.con;		
		
		area=new JTextArea(15, 20);
		scroll=new JScrollPane(area);
		txt_input=new JTextField(20);
		
		add(scroll);
		add(txt_input);
		
		
		txt_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					System.out.println("엔터쳤니>");
					//내부익명함수내에서 지역변수는 final만 인식가능하다!!
					//clientThread를 멤버변수로 선언하고 생성자에서 new하거나
					//메인과 연결시키면 이생성자내에서는 지역변수로 여겨지기 때문에
					//내부익명함수내에서 사용할 수 없다.
					
					main.clientThread.send(txt_input.getText());
					txt_input.setText("");
					txt_input.requestFocus();
				}
			}
		});
		
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);	
	}
}
