package hotelclient.network;

import java.util.Calendar;

import org.json.simple.JSONObject;
import hotelclient.resv.DateUtil;
import hotelclient.ClientMain;
import hotelclient.ClientThread;

public class MemberRegistRequest{
	ClientMain main;
	ClientThread clientThread;
	String yyyy;
	String mm;
	String dd;
	String hh24;
	String mi;
	String ss;
	
		
	public MemberRegistRequest(ClientMain main) {
		this.main=main;
		this.clientThread=main.clientThread;
				
		Calendar cal=Calendar.getInstance();
		yyyy=Integer.toString(cal.get(Calendar.YEAR));
		//mm�� 0���� �����ϹǷ� ����Ҷ� +1�� ����� �Ѵ�.
		mm=DateUtil.getDateString(Integer.toString(cal.get(Calendar.MONTH)+1));
		dd=DateUtil.getDateString(Integer.toString(cal.get(Calendar.DATE)));
		hh24=DateUtil.getDateString(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
		mi=DateUtil.getDateString(Integer.toString(cal.get(Calendar.MINUTE)));
		ss=DateUtil.getDateString(Integer.toString(cal.get(Calendar.SECOND)));
		
		
	}
	
	/*//��ȸ�� �濹���� ���		
	//Ŭ���̾�Ʈ ȸ������ ��û
	var msgExResgist={
		"room_number":204,
		"requestType":"membership_regist",
		"requestTime":"2017-04-17-18-19-23",
		"member_nick":"jsklsk",
		"member_pw":"1234",
		"member_name":"�輺��",
		"member_phone":"010-2322-1111"",
		"member_email":"syssk@aewr.com",
		"member_gender":"��",
		"member_birthday":"1987-05-03"
	}*/
	
	
	public void requestJSON(String member_nick, String member_pw, String member_name, String member_phone, String member_email) {						
		JSONObject json=new JSONObject();
		json.put("room_number", main.room_Number);
		json.put("requestType", "membership_regist");
		json.put("requestTime", yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss);
		json.put("member_nick", member_nick);		
		json.put("member_pw", member_pw);		
		json.put("member_name", member_name);		
		json.put("member_phone", member_phone);		
		json.put("member_email", member_email);		
		json.put("member_gender", "��");
		json.put("member_birthday", "1987-05-03");
		
		String JSONRequest=json.toJSONString();
		System.out.println(JSONRequest);
		clientThread.send(JSONRequest);		
	}

}
