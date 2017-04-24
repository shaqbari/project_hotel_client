package hotelclient.attract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hotelclient.ClientMain;

public class AttractionPanel extends JPanel implements ActionListener {
	ClientMain main;
	String[][] imgName = { { "jeju_samsung.jpg", "삼성혈" }, { "jeju_dragonhead.jpg", "용두암" },
			{ "jeju_mabang.jpg", "마방목지" }, { "jeju_hanla.jpg", "한라수목원" }, };
	JButton bt_samsung, bt_dragon, bt_mabang, bt_hanla;
	JButton[] buttons = new JButton[4];
	Attraction_View[] attView = new Attraction_View[imgName.length];

	JPanel p_center, p_map;
	JLabel la_mapInfo;

	// 지도 기본 위도, 경도, 생성이미지 명
	String latitude = "33.249476";
	String longitude = "126.408080";
	String img = "map_main.jpg";

	// 클릭시 링크연결을 위한 변수선언
	String str;

	public AttractionPanel(ClientMain main) {
		this.setLayout(new BorderLayout());
		this.main = main;

		p_center = new JPanel();
		p_map = new JPanel();
		la_mapInfo = new JLabel();
		
		buttons[0] = bt_samsung = new JButton();
		buttons[1] = bt_dragon = new JButton();
		buttons[2] = bt_mabang = new JButton();
		buttons[3] = bt_hanla = new JButton();

		p_center.setBackground(Color.LIGHT_GRAY);
		p_center.setLayout(new FlowLayout());
		p_map.setLayout(new BorderLayout());
		p_map.setPreferredSize(new Dimension(500, 800));

		add(p_center);
		add(p_map, BorderLayout.EAST);
		
		la_mapInfo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		bt_samsung.addActionListener(this);
		bt_dragon.addActionListener(this);
		bt_mabang.addActionListener(this);
		bt_hanla.addActionListener(this);

		p_map.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				rink();
			}
		});

		// 관광지 생성
		view();

		// 지도 생성
		createMap();

		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);
	}

	public void view() {
		for (int i = 0; i < imgName.length; i++) {
			URL url = null;
			try {
				url = new URL("http://pseudoluna.synology.me/experi/images/" + imgName[i][0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			attView[i] = new Attraction_View(imgName[i][1], buttons[i], url);
			p_center.add(attView[i]);
		}
	}
	
	//구글로부터 이미지자료를 끌어와서 다시 읽는 형식. 폴더에 이미지가 생성된다.
	public void createMap() {
		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude
					+ "&zoom=17&size=612x612&scale=2&maptype=roadmap";
			String destinationFile = img;
			
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);
			byte[] b = new byte[2048];		//빨리 읽기 위해 배열선언
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon((new ImageIcon(img)).getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH));
		p_map.add(new JLabel(imageIcon), BorderLayout.NORTH);		//지도 붙이기
		p_map.add(la_mapInfo);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_samsung) { // 삼성혈
			samsung();
		} else if (obj == bt_dragon) { // 용두암
			dragon();
		} else if (obj == bt_mabang) { // 마방목지
			mabang();
		} else if (obj == bt_hanla) { // 한라수목원
			hanla();
		}
	}

	// 지도 클릭시 익스플로러 브라우저로 해당 관광지 지도열기
	public void rink() {
		try {
			String[] path = { "C:/Program Files/Internet Explorer/iexplore.exe", str};
			Runtime run = Runtime.getRuntime();
			Process process = run.exec(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//삼성혈 정보
	public void samsung(){
		String add = "제주특별자치도 제주시 이도1동 삼성로 22";
		String call = "064-722-3315";
		String time = "오전 8:30 ~ 오후 6:30";
		
		p_map.removeAll();
		//지도 설정
		latitude = "33.504496";
		longitude = "126.529190";
		img = "map_" + imgName[0][1] + ".jpg";
		//링크주소
		str = "https://www.google.co.kr/maps/place/%EC%82%BC%EC%84%B1%ED%98%88/@33.5042631,126.5270046,17z/data=!4m5!3m4!1s0x350cfcac72f1bd87:0xfed7c87e51d6a0d4!8m2!3d33.5042631!4d126.5291986";
		//정보 뿌리기
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[0][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵 연결</html>");
	}
	
	//용두암 정보
	public void dragon(){
		String add = "제주특별자치도 제주시 용담2동 용두암길 15";
		String call = "064-728-3918";
		String time = "24시간 영업";
		
		p_map.removeAll();
		latitude = "33.516255";
		longitude = "126.511890";
		img = "map_" + imgName[1][1] + ".jpg";
		str = "https://www.google.co.kr/maps/place/%EC%9A%A9%EB%91%90%EC%95%94%E9%BE%99%E5%A4%B4%E5%B2%A9/@33.5161459,126.5096874,17z/data=!3m1!4b1!4m5!3m4!1s0x350ce4b2810e4315:0x3044bfd3cfaa7bb0!8m2!3d33.5161459!4d126.5118814";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[1][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵 연결</html>");
	}
	
	//마방목지 정보
	public void mabang(){
		String add = "제주특별자치도 제주시 봉개동 516로 2480 ";
		String call = "없음";
		String time = "24시간 영업";
		
		p_map.removeAll();
		latitude = "33.427246";
		longitude = "126.604116";
		img = "map_" + imgName[2][1] + ".jpg";
		str = "https://www.google.co.kr/maps/place/%EB%A7%88%EB%B0%A9%EB%AA%A9%EC%A7%80/@33.4269778,126.6018786,17z/data=!3m1!4b1!4m5!3m4!1s0x350cfd8b880d1fe9:0x6a34ccfe385cc2d9!8m2!3d33.4269778!4d126.6040726";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[2][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵 연결</html>");
	}
	
	//한라수목원 정보
	public void hanla(){
		String add = "제주특별자치도 제주시 연동 수목원길 72";
		String call = "064-710-7575";
		String time = "오전 9:00 ~ 오후 6:00";
		
		p_map.removeAll();
		latitude = "33.470223";
		longitude = "126.493191";
		img = "map_" + imgName[3][1] + ".jpg";
		str = "https://www.google.co.kr/maps/place/%ED%95%9C%EB%9D%BC%EC%88%98%EB%AA%A9%EC%9B%90/@33.4699272,126.4910505,17z/data=!3m1!4b1!4m5!3m4!1s0x350cfbbaf3e89deb:0xeef273c1810424e1!8m2!3d33.4699272!4d126.4932445";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[3][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵 연결</html>");
	}
}







