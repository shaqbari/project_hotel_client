package hotelclient;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hotelclient.attract.AttractionPanel;
import hotelclient.chat.ChatPanel;
import hotelclient.home.HomePanel;
import hotelclient.main.CheckAdminPanel;
import hotelclient.main.ClockThread;
import hotelclient.main.DBManager;
import hotelclient.main.MyButton;
import hotelclient.main.RegAdminPanel;
import hotelclient.resv.ResvPanel;
import hotelclient.service.ServicePanel;

public class ClientMain extends JFrame implements ActionListener{
	String room_Number="203";
	
	//��Ʈ��ũ�� ���� ��ü��
	Socket socket;
	ClientThread clientThred;
	String ip="localhost";
	int port=7777;
	
	//db������ ���� ��ü��
	DBManager manager;
	public Connection con;	
	
	//��������ȯ�� ���� ��ü��
	CheckAdminPanel checkAdminPanel; //�α����г�
	RegAdminPanel regAdminPanel;	//������ ����г�	
	JPanel p_container;
	JPanel[] page=new JPanel[3];
	
	//p_container�� ���� ui���� ��ü��
	public JPanel p_north, p_west, p_center;
	
	JLabel la_hotel; //p_north�� ��� label
	public JLabel la_time;
	JLabel la_admin;
	public JLabel la_user;
	public Font font_north, font_content;
	JButton bt_logout;	
	JButton bt_home, bt_now, bt_resv, bt_member, bt_chat; //p_west�� ��� button
	JButton[] buttons=new JButton[5];
	
	String[][] imgName={
			{"home.png", "Ȩ"},
			{"room.png", "���ǰ���"},
			{"resv.png", "�������"},
			{"membership.png", "������"},
			{"chat.png", "ä��"}
	};//res�������� ����� �̹���		
	public MyButton[] myButtons=new MyButton[imgName.length];
	
	//�޴����ÿ� ���� �г���ȯ�� ���� ��ü��
	public HomePanel homePanel;
	AttractionPanel attractionPanel;
	ServicePanel servicePanel;	
	ResvPanel resvPanel;
	ChatPanel chatPanel;	
	JPanel[] p_menus=new JPanel[5];	
	
	
	//��Ʈ��ũ Ȯ�� �г�
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JTextField txt_input;
	
	//�޴����ÿ� ���� �г���ȯ�� ���� ��ü��
	//todo
	
	//��Ÿ ��ü��
	ClockThread clock; //�ð�
	
	public ClientMain() {
		
		manager=DBManager.getInstance();
		con=manager.getConnection();
		
		bt_connect=new JButton("���� ����");
		
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
	
	public void setPage(int page){
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		connect();
		bt_connect.setEnabled(false);
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}

}
