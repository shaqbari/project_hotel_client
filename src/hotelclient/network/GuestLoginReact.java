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

public class GuestLoginReact {
	ClientMain main;
	CheckUserPanel checkUserPanel;
	HomePanel homePanel;
	
	JSONObject json;
	
	String result;
	int hotel_user_id;
	String guest_name;
	String resv_time;
	int stay;
	
	String yyyy;
	String mm;
	String dd;
	
	public GuestLoginReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.checkUserPanel=main.checkAdminPanel;
		this.homePanel=main.homePanel;
		this.json=json;
		
		
		result=json.get("result").toString();
		if (result.equalsIgnoreCase("yes")) {
			hotel_user_id=Integer.parseInt(json.get("hotel_user_id").toString());
			guest_name=json.get("guest_name").toString();
			resv_time=json.get("resv_time").toString();
			stay=Integer.parseInt(json.get("stay").toString());			
		}
		
		react();
		
	}
	
	public void reset(){
		checkUserPanel.txt_resv_id.setText("");
		checkUserPanel.txt_phone1.setText("");
		checkUserPanel.txt_phone2.setText("");
		checkUserPanel.txt_phone3.setText("");
		checkUserPanel.txt_resv_id.requestFocus();		
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
						
			dateProcess(resv_time);
			System.out.println(resv_time);
			System.out.println(dd);
			
			main.la_user.setText(guest_name);	
			homePanel.getResv_id_input().setText(main.checkAdminPanel.txt_resv_id.getText());
			homePanel.getGuest_name_input().setText(guest_name);
			homePanel.getResv_time_input().setText(resv_time);
			
			//stay가아니라 종료날짜를 따로 db에 입력하자.
			homePanel.getStay_input().setText(yyyy+"-"+mm+"-"+Integer.toString((Integer.parseInt(dd)+stay)));
			
			for (int i = 0; i < main.resvPanel.guestInfo.size(); i++) {
				main.resvPanel.guestInfo.get(i).setVisible(true);//비회원이라면 예약시 비회원 추가란 보이게한다.				
			}	
			
			reset();
			main.setPage(2);
			JOptionPane.showMessageDialog(main, guest_name+"님 반갑습니다.");			
		}else if (result.equalsIgnoreCase("no")) {
			System.out.println("로그인에 실패하였습니다.");
			
			reset();
			JOptionPane.showMessageDialog(main, "로그인 정보가 잘못입력되었습니다.");
		}		
		
	}
	
	
	
}
