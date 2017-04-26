package hotelclient.network;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.resv.ResvPanel;
import hotelclient.resv.RoomButton;


public class MemberResvReact {
	ClientMain main;
	ResvPanel resvPanel;
	
	JSONObject json;
	
	String result;
	ArrayList<String> available_room =new ArrayList<String>();
	
	String yyyy;
	String mm;
	String dd;
	

	/*	//회원 방예약 응답
	var msgExMemberResv2{		
		"responseType":"member_resv",
		"result":"yes",
		"resv_id":"24"
		s
	}	
	var msgExMemberResv2{		
		"responseType":"member_resv",
		"result":"no"
	}
*/	
	public MemberResvReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.resvPanel=main.resvPanel;
		this.json=json;
		result=json.get("result").toString();
			
		react();
		
	}
	
	public void react() {
		if (result.equalsIgnoreCase("yes")) {
			System.out.println("예약완료");			
		
			JOptionPane.showMessageDialog(resvPanel, "예약에 성공했습니다.\n예약번호 : "+json.get("resv_id").toString()
					+"\n예약일 :"+resvPanel.la_start_input.getText()+"~"+resvPanel.la_end_input.getText()
					+"\n옵션 :"+resvPanel.la_option_input.getText()
					+"\n방번호 :" +resvPanel.la_room_number_input.getText()
					+"\n가격 :"+resvPanel.la_price_input.getText());
			main.resvPanel.refrash();
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "예약에 실패했습니다. 다시 시도해주시기 바랍니다.");
			main.resvPanel.refrash();
		}		
		
	}
	
	
	
}
