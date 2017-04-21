package hotelclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class ClientMain extends JFrame implements ActionListener {
	public int room_Number = 203;
	public int hotel_user_id = 4;

	// ��Ʈ��ũ�� ���� ��ü��
	public Socket socket;
	public ClientThread clientThread;
	String ip = "localhost";
	int port = 7777;

	// db������ ���� ��ü��
	DBManager manager;
	public Connection con;

	// ��������ȯ�� ���� ��ü��
	CheckAdminPanel checkAdminPanel; // �α����г�
	RegAdminPanel regAdminPanel; // ������ ����г�
	JPanel p_container;
	JPanel[] page = new JPanel[3];

	// p_container�� ���� ui���� ��ü��
	public JPanel p_north, p_west, p_center;
	JPanel p_north_west, p_north_east;//p_north�� ��� �гε�
	JLabel la_hotel; // p_north�� ��� label
	public JLabel la_time;
	JLabel la_admin;
	public JLabel la_user;
	public Font font_north, font_content;
	JButton bt_logout, bt_connect;
	JButton bt_home, bt_attraction, bt_service, bt_resv, bt_chat; // p_west�� ��� button
	JButton[] buttons = new JButton[5];

	String[][] imgName = { 
		{ "home.png", "Ȩ" },
		{ "attraction.png", "�ֺ����" },
		{ "service.png", "����" },
		{ "resv.png", "����" },
		{ "chat.png", "ä��" }
	};// res�������� ����� �̹���
	public MyButton[] myButtons = new MyButton[imgName.length];

	// �޴����ÿ� ���� �г���ȯ�� ���� ��ü��
	public HomePanel homePanel;
	AttractionPanel attractionPanel;
	ServicePanel servicePanel;
	ResvPanel resvPanel;
	ChatPanel chatPanel;
	JPanel[] p_menus = new JPanel[5];

	// �޴����ÿ� ���� �г���ȯ�� ���� ��ü��
	// todo

	// ��Ÿ ��ü��
	ClockThread clock; // �ð�

	public ClientMain() {		
		// db������ ���� ��ü��
		manager = DBManager.getInstance();
		con = manager.getConnection();

		p_container = new JPanel();

		p_north = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		p_north_west=new JPanel();
		p_north_east=new JPanel();

		// p_north�� ���� ����
		la_hotel = new JLabel("4��ȣ��                ");
		la_time = new JLabel();
		la_admin = new JLabel("USER: ");
		la_user = new JLabel("�����");
		font_north = new Font("���� ���", Font.BOLD, 30);
		font_content = new Font("�������", Font.PLAIN, 20);
		bt_logout = new JButton("LOGOUT");
		bt_connect=new JButton("��������");
		
		//���⼭���ʹ� MyButton�� ��� p_west�� ���Ͽ���, �����ϱ� ���� �迭�� ��´�.
		buttons[0]=bt_home=new JButton();
		buttons[1]=bt_attraction=new JButton();
		buttons[2]=bt_service=new JButton();
		buttons[3]=bt_resv=new JButton();
		buttons[4]=bt_chat=new JButton();
		
		//mybuttons����
		for (int i = 0; i < imgName.length; i++) {
			URL url=null;
			try {
				url = new URL("http://pseudoluna.synology.me/experi/images/"+imgName[i][0]);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}	
			myButtons[i]=new MyButton(this, buttons[i], url, imgName[i][1]);
		}		
		myButtons[0].flag=false;//ó���� home��ư ���õǾ��ְ� �ʱ�ȭ
		myButtons[0].setBackground(Color.LIGHT_GRAY);	
		
		
		//�޴����� �г��� ������. ���߿� �����ϱ⽱�� �迭�� ��´�.
		p_menus[0]=homePanel=new HomePanel(this);
		p_menus[1]=attractionPanel=new AttractionPanel(this);
		p_menus[2]=servicePanel=new ServicePanel(this);
		p_menus[3]=resvPanel=new ResvPanel(this);
		p_menus[4]=chatPanel=new ChatPanel(this);
		
		clock=new ClockThread(this); //�ð����
		


		p_container.setLayout(new BorderLayout());
		p_north.setLayout(new GridLayout(1, 2));
		
		//size����
		p_container.setPreferredSize(new Dimension(1280, 960));
		p_north.setPreferredSize(new Dimension(1280, 60));
		p_west.setPreferredSize(new Dimension(180, 900));
		p_center.setPreferredSize(new Dimension(1100, 900));
		
		la_hotel.setFont(font_north);
		la_time.setFont(font_content);
		la_admin.setFont(font_content);
		la_user.setFont(font_content);
		bt_logout.setFont(font_content);
		bt_connect.setFont(font_content);
		
		p_north_west.setAlignmentX(LEFT_ALIGNMENT);
		la_hotel.setAlignmentX(LEFT_ALIGNMENT);
		
		p_north_west.add(la_hotel);
		p_north_west.add(la_time);
		p_north_east.add(la_admin);
		p_north_east.add(la_user);
		p_north_east.add(bt_logout);
		p_north_east.add(bt_connect);
		
		p_north.add(p_north_west);		
		p_north.add(p_north_east);		
		
		for (int i = 0; i < myButtons.length; i++) {
			p_west.add(myButtons[i]);
			p_center.add(p_menus[i]);
		}			
		
		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_west, BorderLayout.WEST);
		p_container.add(p_center);
		
		//add(checkAdminPanel);
		//add(regAdminPanel);
		add(p_container);
		setPage(0);//ó�������������� �α����гκ��̰� �Ѵ�.
		
		//�����ʿ��� ����
		bt_logout.addActionListener(this);
		bt_connect.addActionListener(this);
		bt_home.addActionListener(this);
		bt_attraction.addActionListener(this);
		bt_resv.addActionListener(this);
		bt_service.addActionListener(this);
		bt_chat.addActionListener(this);
		

		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_west, BorderLayout.WEST);
		p_container.add(p_center);

		add(p_container);


		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void setPage(int page) {
		
	}
	
	//�޴����ÿ� ���� �г��� �����ִ� �޼ҵ�
	public void menuVisible(JPanel p){
		for (int i = 0; i < p_menus.length; i++) {			
			if (p==p_menus[i]) {
				p_menus[i].setVisible(true);
			}else {
				p_menus[i].setVisible(false);
			}
		}
		p_center.updateUI();		
	}

	public void connect(){
		try {
			socket = new Socket(ip, port);
			clientThread = new ClientThread(this);
			System.out.println("���Ӽ���");
			bt_connect.setEnabled(false);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "���� ���ӿ� �����Ͽ����ϴ�. ����� �ٽ� �õ����ֽʽÿ�");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=(Object)e.getSource();
		
		//���� ��ư ������ ����
		if (obj==bt_connect) {
			connect();
		}
		
		//�α׾ƿ���ư ������ checkAdminPanel���̰� ����
		if (obj==bt_logout) {
			setPage(0);
		}
		
		if (obj==bt_home) {			
			menuVisible(homePanel);
		}else if (obj==bt_attraction) {
			menuVisible(attractionPanel);
		}else if (obj==bt_service) {
			menuVisible(servicePanel);			
		}else if (obj==bt_resv){
			menuVisible(resvPanel);
		}else if (obj==bt_chat) {
			menuVisible(chatPanel);
		}
	}

	public static void main(String[] args) {
		new ClientMain();
	}

}
