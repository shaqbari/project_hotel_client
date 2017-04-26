package hotelclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import hotelclient.main.CheckUserPanel;
import hotelclient.main.ClockThread;
import hotelclient.main.DBManager;
import hotelclient.main.MyButton;
import hotelclient.main.RegMemberPanel;
import hotelclient.main.XMLParser;
import hotelclient.resv.ResvPanel;
import hotelclient.service.ServicePanel;

public class ClientMain extends JFrame implements ActionListener {
	public int room_Number = 203;
	public int hotel_user_id =1;

	//설정xml파일 읽는데 쓰일 parser
	XMLParser parser;
	
	// 네트워크에 사용될 객체들 로그인버튼 누를때 사용됨
	public Socket socket;
	public ClientThread clientThread;
	String ip = "localhost";
	int port = 7777;

	// db연동에 사용될 객체들
	DBManager manager;
	public Connection con;

	// 윈도우전환에 사용될 객체들
	public CheckUserPanel checkAdminPanel; // 로그인패널
	public RegMemberPanel regMemberPanel; // 관리자 등록패널
	JPanel p_container;
	public JPanel[] page = new JPanel[3];

	// p_container에 붙을 ui관련 객체들
	public JPanel p_north, p_west, p_center;
	JPanel p_north_west, p_north_east;//p_north에 담길 패널들
	JLabel la_hotel; // p_north에 담길 label
	JLabel la_room_number;
	public JLabel la_time;
	JLabel la_admin;
	public JLabel la_user;
	public Font font_north, font_content;
	JButton bt_logout;
	JButton bt_home, bt_attraction, bt_service, bt_resv, bt_chat; // p_west에 담길 button
	JButton[] buttons = new JButton[5];

	String[][] imgName = { 
		{ "home.png", "홈" },
		{ "attraction.png", "주변명소" },
		{ "service.png", "서비스" },
		{ "resv.png", "예약" },
		{ "chat.png", "채팅" }
	};// res폴더에서 사용할 이미지
	public MyButton[] myButtons = new MyButton[imgName.length];

	// 메뉴선택에 따라 패널전환에 사용될 객체들
	public HomePanel homePanel;
	AttractionPanel attractionPanel;
	public ServicePanel servicePanel;
	public ResvPanel resvPanel;
	public ChatPanel chatPanel;
	JPanel[] p_menus = new JPanel[5];

	// 메뉴선택에 따라 패널전환에 사용될 객체들
	// todo

	// 기타 객체들
	ClockThread clock; // 시계

	public ClientMain() {
		super("호텔 서비스 시스템");
		
		// db연동에 사용될 객체들
		manager = DBManager.getInstance();
		con=manager.getConnection();	
		
		parser=new XMLParser();
		room_Number=Integer.parseInt(parser.handler.getMyRoom());
		ip=parser.handler.getMyIp();
		port=Integer.parseInt(parser.handler.getMyPort());
		
		page[0]=checkAdminPanel=new CheckUserPanel(this);//로그인정보 확인 패널
		page[1]=regMemberPanel=new RegMemberPanel(this);//로그인정보 확인 패널
		page[2]=p_container = new JPanel();//아래패널을 담을 패널
		p_north = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		p_north_west=new JPanel();
		p_north_east=new JPanel();

		// p_north에 붙일 예정
		la_hotel = new JLabel("4조호텔      ");
		la_room_number=new JLabel(Integer.toString(room_Number)+"호  ");
		la_time = new JLabel();
		la_admin = new JLabel("USER: ");
		la_user = new JLabel("사용자");
		font_north = new Font("맑은 고딕", Font.BOLD, 30);
		font_content = new Font("맑은고딕", Font.PLAIN, 20);
		bt_logout = new JButton("LOGOUT");
		
		//여기서부터는 MyButton에 담겨 p_west에 붙일예정, 관리하기 쉽게 배열에 담는다.
		buttons[0]=bt_home=new JButton();
		buttons[1]=bt_attraction=new JButton();
		buttons[2]=bt_service=new JButton();
		buttons[3]=bt_resv=new JButton();
		buttons[4]=bt_chat=new JButton();
		
		//mybuttons생성
		for (int i = 0; i < imgName.length; i++) {
			URL url=null;
			try {
				url = new URL("http://pseudoluna.synology.me/experi/images/"+imgName[i][0]);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}	
			myButtons[i]=new MyButton(this, buttons[i], url, imgName[i][1]);
		}		
		myButtons[0].flag=false;//처음에 home버튼 선택되어있게 초기화
		myButtons[0].setBackground(Color.LIGHT_GRAY);	
		
		
		//메뉴별로 패널을 나눈다. 나중에 관리하기쉽게 배열에 담는다.
		p_menus[0]=homePanel=new HomePanel(this);
		p_menus[1]=attractionPanel=new AttractionPanel(this);
		p_menus[2]=servicePanel=new ServicePanel(this);
		p_menus[3]=resvPanel=new ResvPanel(this);
		p_menus[4]=chatPanel=new ChatPanel(this);
		
		clock=new ClockThread(this); //시계생성
		
		//레이아웃조정
		this.setLayout(new FlowLayout()); //pack이용하게 flowlayout으로
		p_container.setLayout(new BorderLayout());
		p_north.setLayout(new GridLayout(1, 2));
				
		//size조정
		p_container.setPreferredSize(new Dimension(1280, 960));
		p_north.setPreferredSize(new Dimension(1280, 60));
		p_west.setPreferredSize(new Dimension(180, 900));
		p_center.setPreferredSize(new Dimension(1100, 900));
		
		la_hotel.setFont(font_north);
		la_room_number.setFont(font_north);
		la_time.setFont(font_content);
		la_admin.setFont(font_content);
		la_user.setFont(font_content);
		bt_logout.setFont(font_content);
		
		p_north_west.setAlignmentX(LEFT_ALIGNMENT);
		la_hotel.setAlignmentX(LEFT_ALIGNMENT);
		
		p_north_west.add(la_hotel);
		p_north_west.add(la_room_number);
		p_north_west.add(la_time);
		p_north_east.add(la_admin);
		p_north_east.add(la_user);
		p_north_east.add(bt_logout);
		
		p_north.add(p_north_west);		
		p_north.add(p_north_east);		
		
		for (int i = 0; i < myButtons.length; i++) {
			p_west.add(myButtons[i]);
			p_center.add(p_menus[i]);
		}			
		
		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_west, BorderLayout.WEST);
		p_container.add(p_center);
		
		add(checkAdminPanel);
		add(regMemberPanel);
		add(p_container);
		setPage(0);//처음실행했을때는 로그인패널보이게 한다.
		
		//리스너와의 연결
		bt_logout.addActionListener(this);
		bt_home.addActionListener(this);
		bt_attraction.addActionListener(this);
		bt_resv.addActionListener(this);
		bt_service.addActionListener(this);
		bt_chat.addActionListener(this);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				clientThread.close();
			}
		});

		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setPage(int index) {
		for (int i = 0; i < page.length; i++) {
			if (i==index) {
				page[i].setVisible(true);
			}else{
				page[i].setVisible(false);
			}			
		}
		this.pack();//내용물의 크기만큼 윈도우 크기를 설정, 하나의 윈도우가 여러가지 모습을 보일수 있다.
		this.setLocationRelativeTo(null); //화면중앙에 배치, 여기서 또 안하면 원래있던데서 크기 바뀐다.
	}
	
	//메뉴선택에 따라 패널을 보여주는 메소드
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
	
	//로그인 시도할때 호출(로그인버튼 누를때)
	public boolean connect(){
		try {
			socket = new Socket(ip, port);
			clientThread = new ClientThread(this);
			System.out.println("접속성공");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "서버 접속에 실패하였습니다. 잠시후 다시 시도해주십시오");
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=(Object)e.getSource();
	
		//로그아웃버튼 누르면 checkAdminPanel보이게 설정
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
