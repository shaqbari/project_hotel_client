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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import hotelclient.ClientMain;

public class AttractionPanel extends JPanel implements ActionListener {
	ClientMain main;
	String[][] imgName = { 
			{ "jeju_samsung.jpg", "삼성혈" }, 
			{ "jeju_dragonhead.jpg", "용두암" },
			{ "jeju_mabang.jpg", "마방목지" }, 
			{ "jeju_hanla.jpg", "한라수목원" }, 
			{ "jeju_subji.jpg", "섭지코지" },
			{ "jeju_sungsan.jpg", "성산일출봉" },
			{ "jeju_chunji.jpg", "천지연폭포" },
			{ "jeju_manjang.jpg", "만장굴" },

	};
	JButton bt_samsung, bt_dragon, bt_mabang, bt_hanla, bt_subji, bt_sungsan, bt_chunji, bt_manjang;
	JButton[] buttons = new JButton[8];
	Attraction_View[] attView = new Attraction_View[imgName.length];
	JButton bt_zoomIn, bt_zoomOut;
	
	JPanel p_center, p_map, p_zoom;
	JLabel la_mapInfo, la_zoom, la_zoomIn, la_zoomOut;
	// 지도 기본 위도, 경도, 생성이미지 명
	String latitude = "33.249476";
	String longitude = "126.408080";
	String img ="map.jpg";
	
	//지도 줌인 줌아웃을 위한 변수
	int zoomNum=17;
	
	// 클릭시 링크연결을 위한 변수선언
	String str;

	public AttractionPanel(ClientMain main) {
		this.setLayout(new BorderLayout());
		this.main = main;

		p_center = new JPanel();
		p_map = new JPanel();
		p_zoom = new JPanel();
		la_mapInfo = new JLabel();
		la_zoom = new JLabel("지도 크기조정");
		
		buttons[0] = bt_samsung = new JButton();
		buttons[1] = bt_dragon = new JButton();
		buttons[2] = bt_mabang = new JButton();
		buttons[3] = bt_hanla = new JButton();
		buttons[4] = bt_subji = new JButton();
		buttons[5] = bt_sungsan = new JButton();
		buttons[6] = bt_chunji = new JButton();
		buttons[7] = bt_manjang = new JButton();
		
		//지도 줌인, 줌아웃
		bt_zoomIn = new JButton();
		bt_zoomOut = new JButton();
		la_zoomIn = new JLabel("+");
		la_zoomOut = new JLabel("-");
		
		la_zoomIn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		la_zoomOut.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		p_center.setBackground(Color.DARK_GRAY);
		p_center.setLayout(new FlowLayout());
		
		p_map.setLayout(new BorderLayout());
		p_map.setPreferredSize(new Dimension(500, 800));
		p_map.setBackground(Color.LIGHT_GRAY);
				
		bt_zoomOut.add(la_zoomOut);
		bt_zoomIn.add(la_zoomIn);
		
		p_zoom.add(bt_zoomOut);
		p_zoom.add(la_zoom);
		p_zoom.add(bt_zoomIn);
		
		add(p_center);
		add(p_map, BorderLayout.EAST);
		
		la_mapInfo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		la_mapInfo.setForeground(Color.BLACK);
		la_mapInfo.setText("<html><br> 4조 호텔에 오신 것을 환영합니다. <br><br> ㆍ 주소 : 제주특별자치도 서귀포시 색달동 중문관광로72번길 75 <br> ㆍ 전화번호 : 064-735-5114 <br>ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
		
		str = "https://www.google.co.kr/maps/place/%ED%98%B8%ED%85%94%EC%8B%A0%EB%9D%BC%EC%A0%9C%EC%A3%BC/@33.2473409,126.4058993,17z/data=!4m8!1m2!2m1!1z7Iug65287Zi47YWUIOygnOyjvA!3m4!1s0x350c5acf5f97fff1:0x1a4e339a880f3558!8m2!3d33.2472877!4d126.4081496";
		
		la_zoom.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		la_zoom.setForeground(Color.BLACK);
		
		bt_samsung.addActionListener(this);
		bt_dragon.addActionListener(this);
		bt_mabang.addActionListener(this);
		bt_hanla.addActionListener(this);
		bt_subji.addActionListener(this);
		bt_sungsan.addActionListener(this);
		bt_chunji.addActionListener(this);
		bt_manjang.addActionListener(this);
		bt_zoomIn.addActionListener(this);
		bt_zoomOut.addActionListener(this);

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
					+ "&zoom="+zoomNum+"&size=612x612&scale=2&maptype=roadmap";
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
		JLabel imgIcon = new JLabel(imageIcon);
		p_map.add(imgIcon, BorderLayout.NORTH);		//지도 붙이기
		p_map.add(la_mapInfo);
		p_map.add(p_zoom, BorderLayout.SOUTH);
		
		//지도가 바뀌면 imgIcon이 새로 생성되니 매번 마우스 리스너도 다시 달아준다.
		imgIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				rink();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_samsung) {
			samsung();
		} else if (obj == bt_dragon) { 
			dragon();
		} else if (obj == bt_mabang) { 
			mabang();
		} else if (obj == bt_hanla) {
			hanla();
		} else if (obj == bt_subji) {
			subji();
		} else if (obj == bt_sungsan) { 
			sungsan();
		} else if (obj == bt_chunji) { 
			chunji();
		} else if (obj == bt_manjang) { 
			manjang();
		} else if (obj == bt_zoomIn) { 
			zoomNum++;
			p_map.removeAll();
			createMap();
		} else if (obj == bt_zoomOut) { 
			zoomNum--;
			p_map.removeAll();
			createMap();
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
		//링크주소
		str = "https://www.google.co.kr/maps/place/%EC%82%BC%EC%84%B1%ED%98%88/@33.5042631,126.5270046,17z/data=!4m5!3m4!1s0x350cfcac72f1bd87:0xfed7c87e51d6a0d4!8m2!3d33.5042631!4d126.5291986";
		//정보 뿌리기
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[0][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
	
	//용두암 정보
	public void dragon(){
		String add = "제주특별자치도 제주시 용담2동 용두암길 15";
		String call = "064-728-3918";
		String time = "24시간 영업";
		
		p_map.removeAll();
		latitude = "33.516255";
		longitude = "126.511890";
		str = "https://www.google.co.kr/maps/place/%EC%9A%A9%EB%91%90%EC%95%94%E9%BE%99%E5%A4%B4%E5%B2%A9/@33.5161459,126.5096874,17z/data=!3m1!4b1!4m5!3m4!1s0x350ce4b2810e4315:0x3044bfd3cfaa7bb0!8m2!3d33.5161459!4d126.5118814";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[1][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
	
	//마방목지 정보
	public void mabang(){
		String add = "제주특별자치도 제주시 봉개동 516로 2480 ";
		String call = "없음";
		String time = "24시간 영업";
		
		p_map.removeAll();
		latitude = "33.427246";
		longitude = "126.604116";
		str = "https://www.google.co.kr/maps/place/%EB%A7%88%EB%B0%A9%EB%AA%A9%EC%A7%80/@33.4269778,126.6018786,17z/data=!3m1!4b1!4m5!3m4!1s0x350cfd8b880d1fe9:0x6a34ccfe385cc2d9!8m2!3d33.4269778!4d126.6040726";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[2][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
	
	//한라수목원 정보
	public void hanla(){
		String add = "제주특별자치도 제주시 연동 수목원길 72";
		String call = "064-710-7575";
		String time = "오전 9:00 ~ 오후 6:00";
		
		p_map.removeAll();
		latitude = "33.470223";
		longitude = "126.493191";
		str = "https://www.google.co.kr/maps/place/%ED%95%9C%EB%9D%BC%EC%88%98%EB%AA%A9%EC%9B%90/@33.4699272,126.4910505,17z/data=!3m1!4b1!4m5!3m4!1s0x350cfbbaf3e89deb:0xeef273c1810424e1!8m2!3d33.4699272!4d126.4932445";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[3][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
	
	//섭지코지 정보
	public void subji(){
		String add = "제주특별자치도 서귀포시 성산읍 섭지코지로 262";
		String call = "064-782-0080";
		String time = "오전 9:00 ~ 오후 6:00";
		
		p_map.removeAll();
		latitude = "33.423792";
		longitude = "126.929312";
		str = "https://www.google.co.kr/maps/place/%EC%84%AD%EC%A7%80%EC%BD%94%EC%A7%80/@33.4235416,126.9271379,17z/data=!4m12!1m6!3m5!1s0x350d131682df90fb:0x3e20058eb88a16f1!2z7ISt7KeA7L2U7KeA!8m2!3d33.4235416!4d126.9293266!3m4!1s0x350d131682df90fb:0x3e20058eb88a16f1!8m2!3d33.4235416!4d126.9293266";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[4][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
		
	//성산일출봉 정보
	public void sungsan(){
		String add = "제주특별자치도 서귀포시 성산읍 일출로 284-12";
		String call = "064-783-0959";
		String time = "오전 7:30 ~ 오후 6:00";
			
		p_map.removeAll();
		latitude = "33.470223";
		longitude = "126.493191";
		str = "https://www.google.co.kr/maps/place/%EC%84%B1%EC%82%B0+%EC%9D%BC%EC%B6%9C%EB%B4%89/@33.458056,126.940306,17z/data=!3m1!4b1!4m5!3m4!1s0x350d14b9f6e3789f:0x555132053a23b64b!8m2!3d33.458056!4d126.9425";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[5][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
	
	//천지연폭포 정보
	public void chunji(){
		String add = "제주특별자치도 서귀포시 천지동 667-7";
		String call = "064-733-1528";
		String time = "오전 8:00 ~ 오후 10:00";
			
		p_map.removeAll();
		latitude = "33.247204";
		longitude = "126.554417";
		str = "https://www.google.co.kr/maps/place/%EC%B2%9C%EC%A7%80%EC%97%B0%ED%8F%AD%ED%8F%AC/@33.246944,126.552223,17z/data=!4m5!3m4!1s0x350c5397e5d18685:0xdb873e6da9b01b6!8m2!3d33.246944!4d126.554417";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[6][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
	
	//만장굴 정보
	public void manjang(){
		String add = "제주특별자치도 제주시 구좌읍 만장굴길 182";
		String call = "064-710-7903";
		String time = "오전 9:00 ~ 오후 6:00";
			
		p_map.removeAll();
		latitude = "33.528772";
		longitude = "126.771470";
		str = "https://www.google.co.kr/maps/place/%EB%A7%8C%EC%9E%A5%EA%B5%B4/@33.528486,126.769287,17z/data=!3m1!4b1!4m5!3m4!1s0x350d19c496216a99:0xbb66abf00c2b95a6!8m2!3d33.528486!4d126.771481";
		createMap();
		la_mapInfo.setText("<html><br> ㆍ 명소명 : "+imgName[7][1]+"<br> ㆍ 주소 : "+add+"<br> ㆍ 전화번호 : "+call+"<br> ㆍ 영업시간 : "+time+"<br> ㆍ 지도 클릭시 구글 맵으로 연결됩니다.</html>");
	}
}







