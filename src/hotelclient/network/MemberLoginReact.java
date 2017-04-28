package hotelclient.network;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.home.HomePanel;
import hotelclient.main.CheckUserPanel;

//게스트로그인 서버 응답
/*	var msgEx1={
	"responseType":"guest_login",
	"result":"yes",
	"hotel_user_id":1,
	"geust_name":"김성현",
	"resv_time":"2017-04-17-18-19-23",
	"stay:1		
}*/

public class MemberLoginReact {
	ClientMain main;
	CheckUserPanel checkUserPanel;
	HomePanel homePanel;
	
	JSONObject json;
	
	String result;
	int hotel_user_id;
	String member_name;
	String resv_id;
	String resv_time;
	String end_time;
	int stay;
	
	String yyyy;
	String mm;
	String dd;
	
	//게스트로그인 서버 응답
		/*var msgEx2={
			"responseType":"membership_login",
			"result":"yes",
			"hotel_user_id":1,
			"member_name":"김민정",
			
			//아래는 예약했을때만 보낸다
			"resv_time":"2017-04-17-18-19-23",
			"end_time":"2017-04-17-18-19-23",
			"stay:1		
		}*/
	
	
	
	public MemberLoginReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.checkUserPanel=main.checkAdminPanel;
		this.homePanel=main.homePanel;
		this.json=json;
		result=json.get("result").toString();
		if (result.equalsIgnoreCase("yes")) {
			hotel_user_id=Integer.parseInt(json.get("hotel_user_id").toString());
			member_name=json.get("member_name").toString();
			if (json.get("resv_id")!=null) {
				resv_id = json.get("resv_id").toString();
				resv_time = json.get("resv_time").toString();
				end_time=json.get("end_time").toString();
				stay = Integer.parseInt(json.get("stay").toString());
			}			
		}
		
		react();
		
	}
	
	public void reset(){
		checkUserPanel.txt_id.setText("");
		checkUserPanel.txt_pw.setText("");
		checkUserPanel.txt_id.requestFocus();		
	}
	
	public void dateProcess(String resv_time){
		//2017-04-28 15:10:10
		String[] date=resv_time.split(" ")[0].split("-");
		this.yyyy=date[0];
		this.mm=date[1];
		this.dd=date[2];
		
	}

	
	public void react() {
		if (result.equalsIgnoreCase("yes")) {
			System.out.println("로그인에 성공하였습니다.");			
						
			
			main.la_user.setText(member_name);	
			main.hotel_user_id=hotel_user_id;
			
			if (resv_id!=null) {
				dateProcess(resv_time);
				homePanel.getResv_id_input().setText(resv_id);
				homePanel.getGuest_name_input().setText(member_name);
				homePanel.getResv_time_input().setText(resv_time);
				//stay가아니라 종료날짜를 따로 db에 입력하자.
				homePanel.getStay_input()
						.setText(end_time);
			}
			
			reset();
			
			for (int i = 0; i < main.resvPanel.guestInfo.size(); i++) {
				main.resvPanel.guestInfo.get(i).setVisible(false);//회원이 맞다면 예약시 비회원 추가란 안보이게 한다.				
			}		
			
			main.setPage(2);
			JOptionPane.showMessageDialog(main, member_name+"님 반갑습니다.");			
		}else if (result.equalsIgnoreCase("no")) {
			System.out.println("로그인에 실패하였습니다.");
			
			reset();
			JOptionPane.showMessageDialog(main, "로그인 정보가 잘못입력되었습니다.");
		}		
		
	}
	
	
	
}
