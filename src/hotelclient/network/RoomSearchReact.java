package hotelclient.network;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.resv.ResvPanel;
import hotelclient.resv.RoomButton;

//게스트로그인 서버 응답
/*	var msgEx1={
	"responseType":"guest_login",
	"result":"yes",
	"hotel_user_id":1,
	"geust_name":"김성현",
	"resv_time":"2017-04-17-18-19-23",
	"stay:1		
}*/

public class RoomSearchReact {
	ClientMain main;
	ResvPanel resvPanel;
	
	JSONObject json;
	
	String result;
	ArrayList<String> available_room =new ArrayList<String>();
	
	String yyyy;
	String mm;
	String dd;
	

	/*//방검색응답의 경우
	var msgExRommSearchResponse={
		"responseType":"room_search",
		"result":"yes",
		"available_room":[
			"203", "303", "403"
		]
	}
	
	//방이 없는경우
	var msgExRommSearchResponse2={
			"responseType":"room_search",
			"result":"no",			
		}*/	
	
	public RoomSearchReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.resvPanel=main.resvPanel;
		this.json=json;
		
		
		result=json.get("result").toString();
		if (result.equalsIgnoreCase("yes")) {
			JSONArray roomNum=(JSONArray) json.get("available_room");
			for (int i = 0; i < roomNum.size(); i++) {
				available_room.add(roomNum.get(i).toString());
			}
		}
		
		react();
		
	}
	
	public void react() {
		if (result.equalsIgnoreCase("yes")) {
			System.out.println("방이 있습니다.");			
			resvPanel.p_east_room.removeAll();
			resvPanel.p_east_room.updateUI();
			for (int i = 0; i < available_room.size(); i++) {
				resvPanel.p_east_room.add(new RoomButton(main, available_room.get(i)));
			}
			
			
		}else if (result.equalsIgnoreCase("no")) {
			System.out.println("방이 없습니다.");			
			JOptionPane.showMessageDialog(main, "조건을 만족하는 방이 없습니다.");
		}		
		
	}
	
	
	
}
