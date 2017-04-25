package hotelclient.network;

import java.util.Calendar;

import org.json.simple.JSONObject;
import hotelclient.resv.DateUtil;
import hotelclient.ClientMain;
import hotelclient.ClientThread;

public class MemberResvRequest{
	ClientMain main;
	ClientThread clientThread;
	String yyyy;
	String mm;
	String dd;
	String hh24;
	String mi;
	String ss;
	
		
	public MemberResvRequest(ClientMain main) {
		this.main=main;
		this.clientThread=main.clientThread;
				
		Calendar cal=Calendar.getInstance();
		yyyy=Integer.toString(cal.get(Calendar.YEAR));
		mm=DateUtil.getDateString(Integer.toString(cal.get(Calendar.MONTH)));
		dd=DateUtil.getDateString(Integer.toString(cal.get(Calendar.DATE)));
		hh24=DateUtil.getDateString(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
		mi=DateUtil.getDateString(Integer.toString(cal.get(Calendar.MINUTE)));
		ss=DateUtil.getDateString(Integer.toString(cal.get(Calendar.SECOND)));
		
		
	}
	
	/*//회원 방예약의 경우
		var msgExResv={
			"room_number":303,
			"reqeustType":"member_resv",
			"requestTime":"2017-04-17-18-19-23",
			"hotel_user_id":8,
			"resv_room_number": 602,
			"resv_time":"2017-04-20-14-00-00",
			"end_time":"2017-04-23-12-00-00",
			"stay":3
		}*/
	public void requestJSON(int hotel_user_id, int resv_room_number, String start, String end, int stay) {						
		JSONObject json=new JSONObject();
		json.put("room_number", main.room_Number);
		json.put("requestType", "member_resv");
		json.put("requestTime", yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss);
		json.put("hotel_user_id", main.hotel_user_id);
		json.put("resv_room_number", resv_room_number);		
		json.put("resv_time", start);		
		json.put("end_time", end);
		json.put("stay", stay);
		
		String JSONRequest=json.toJSONString();
		System.out.println(JSONRequest);
		clientThread.send(JSONRequest);		
	}

}
