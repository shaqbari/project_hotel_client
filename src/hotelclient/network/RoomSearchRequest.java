package hotelclient.network;

import java.util.Calendar;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.ClientThread;
import hotelclient.main.CheckUserPanel;

public class RoomSearchRequest{
	ClientMain main;
	ClientThread clientThread;
	String yyyy;
	String mm;
	String dd;
	String hh24;
	String mi;
	String ss;
	
		
	public RoomSearchRequest(ClientMain main) {
		this.main=main;
		this.clientThread=main.clientThread;
				
		Calendar cal=Calendar.getInstance();
		yyyy=Integer.toString(cal.get(Calendar.YEAR));
		mm=Integer.toString(cal.get(Calendar.MONTH));
		dd=Integer.toString(cal.get(Calendar.DATE));
		hh24=Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		mi=Integer.toString(cal.get(Calendar.MINUTE));
		ss=Integer.toString(cal.get(Calendar.SECOND));
	}
	
	/*//방검색요청의 경우
		var msgExRoomSearch={
			"requestType":"room_search",
			"room_number":403,
			"requestTime":"2017-05-03-18-19-23",
			"start":"2017-05-04",
			"end":"2017-05-05",
			"option":"vip"				
		}*/
	public void requestJSON(String start, String end, String option) {						
		JSONObject json=new JSONObject();
		json.put("requestType", "room_search");
		json.put("room_number", main.room_Number);
		json.put("requestTime", yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss);
		json.put("start", start);
		json.put("end", end);
		json.put("option", option);
		
		String JSONRequest=json.toJSONString();
		System.out.println(JSONRequest);
		clientThread.send(JSONRequest);		
	}

}
