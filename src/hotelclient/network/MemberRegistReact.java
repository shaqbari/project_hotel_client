package hotelclient.network;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.resv.ResvPanel;
import hotelclient.resv.RoomButton;


public class MemberRegistReact {
	ClientMain main;
	ResvPanel resvPanel;
	
	JSONObject json;
	
	String result;
	ArrayList<String> available_room =new ArrayList<String>();
	
	String yyyy;
	String mm;
	String dd;
	

/*	//회원가입 응답
	var msgExMemberResv2{		
		"responseType":"membership_regist",
		"result":"yes"	,
		"membership_id":23
		
	}	
	var msgExMemberResv2{		
		"responseType":"membership_regist",
		"result":"no"
	}*/
	
	public MemberRegistReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.resvPanel=main.resvPanel;
		this.json=json;
		result=json.get("result").toString();
			
		react();
		
	}
	
	public void react() {
		if (result.equalsIgnoreCase("yes")) {
			System.out.println("예약완료");			
		
			JOptionPane.showMessageDialog(resvPanel, "회원가입에 성공했습니다.\n회원번호 : "+json.get("membership_id").toString());
			main.setPage(0);
			main.regMemberPanel.initialSet();
			
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "회원가입에 실패했습니다. 다시 시도해주시기 바랍니다.");
			main.regMemberPanel.initialSet();
		}		
		
	}
	
	
	
}
