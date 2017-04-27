package hotelclient.network;

import java.util.Calendar;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.ClientThread;
import hotelclient.main.CheckUserPanel;
import hotelclient.resv.DateUtil;

public class GuestLoginRequest{
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
		
	public GuestLoginRequest(ClientMain main, CheckUserPanel checkUserPanel) {
		this.main=main;
		this.clientThread=main.clientThread;
		this.checkUserPanel=checkUserPanel;	
		this.phoneNumber=checkUserPanel.txt_phone1.getText()+"-"+checkUserPanel.txt_phone2.getText()+"-"+checkUserPanel.txt_phone3.getText();
				
		Calendar cal=Calendar.getInstance();
		yyyy=Integer.toString(cal.get(Calendar.YEAR));
		int month=cal.get(Calendar.MONTH)+1;
		mm=DateUtil.getDateString(Integer.toString(month));
		System.out.println("���⼭����mm��?"+mm);
		dd=Integer.toString(cal.get(Calendar.DATE));
		hh24=Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		mi=Integer.toString(cal.get(Calendar.MINUTE));
		ss=Integer.toString(cal.get(Calendar.SECOND));
	}
	
	/*{
	"requestType":"guest_login",
	"room_number":204,
	"requestTime":"2017-04-17-18-19-23", //yyyy-mm-dd-hh24-mi-ss
	"resv_id":2,
	"phone":"010-2222-3333"
}*/	
	public void requestJSON() {						
		JSONObject json=new JSONObject();
		json.put("requestType", "guest_login");
		json.put("room_number", main.room_Number);
		json.put("request_time", yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss);
		json.put("resv_id", checkUserPanel.txt_resv_id.getText());
		json.put("phone", phoneNumber);
		
		String JSONRequest=json.toJSONString();
		clientThread.send(JSONRequest);		
	}

}
