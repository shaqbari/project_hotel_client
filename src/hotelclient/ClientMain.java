package hotelclient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientMain extends JFrame implements ActionListener{
	String room_Number="203";
	
	//네트워크에 사용될 객체들
	Socket socket;
	ClientThread clientThred;
	String ip="localhost";
	int port=7777;
	
	JPanel p_container;
	JPanel p_north, p_west, p_center;
	
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JTextField txt_input;
	
	public ClientMain() {		
		bt_connect=new JButton("서버 접속");
		
		p_container=new JPanel();
		
		p_north=new JPanel();
		p_west=new JPanel();
		p_center=new JPanel();
				
		area=new JTextArea(15, 20);
		scroll=new JScrollPane(area);
		txt_input=new JTextField(20);	
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		p_container.setLayout(new BorderLayout());
		
		p_center.add(bt_connect);
		p_center.add(area);
		p_center.add(scroll);
		p_center.add(txt_input);

		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_west, BorderLayout.WEST);
		p_container.add(p_center);
		
		add(p_container);
		txt_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if (key==KeyEvent.VK_ENTER) {
					clientThred.send(txt_input.getText());
					txt_input.setText("");
					txt_input.requestFocus();
				}
			}
		});
		
		bt_connect.addActionListener(this);
		
		setSize(1280, 960);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}	
	
	public void connect(){
		try {
			socket=new Socket(ip, port);			
			clientThred=new ClientThread(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		connect();
		bt_connect.setEnabled(false);
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}

}
