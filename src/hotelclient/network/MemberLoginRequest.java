package hotelclient.network;

import java.util.Calendar;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.ClientThread;
import hotelclient.main.CheckUserPanel;

public class MemberLoginRequest{
	ClientMain main;
	ClientThread clientThread;
	String yyyy;
	String mm;
	String dd;
	String hh24;
	String mi;
	String ss;
	
	CheckUserPanel checkUserPanel;
	String phoneNumber;
		
	public MemberLoginRequest(ClientMain main, CheckUserPanel checkUserPanel) {
		this.main=main;
		this.clientThread=main.clientThread;
		this.checkUserPanel=checkUserPanel;	
		this.phoneNumber=checkUserPanel.txt_phone1.getText()+"-"+checkUserPanel.txt_phone2.getText()+"-"+checkUserPanel.txt_phone3.getText();
				
		Calendar cal=Calendar.getInstance();
		yyyy=Integer.toString(cal.get(Calendar.YEAR));
		mm=Integer.toString(cal.get(Calendar.MONTH));
		dd=Integer.toString(cal.get(Calendar.DATE));
		hh24=Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		mi=Integer.toString(cal.get(Calendar.MINUTE));
		ss=Integer.toString(cal.get(Calendar.SECOND));
	}
	
	/*
		var msgEx2={
			"requestType":"membership_login",
			"room_number":204,
			"requestTime":"2017-04-17-18-19-23",
			"id_to_nick":"minjung",
			"password":1234
		}*/
	public void requestJSON() {						
		JSONObject json=new JSONObject();
		json.put("requestType", "membership_login");
		json.put("room_number", main.room_Number);
		json.put("request_time", yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss);
		json.put("id_to_nick", checkUserPanel.txt_id.getText());
		json.put("password", Integer.parseInt(new String(checkUserPanel.txt_pw.getPassword())));
		
		System.out.println(json.toJSONString());
		String JSONRequest=json.toJSONString();
		clientThread.send(JSONRequest);		
	}

}
