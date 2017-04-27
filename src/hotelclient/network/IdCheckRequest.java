package hotelclient.network;

import java.util.Calendar;

import org.json.simple.JSONObject;
import hotelclient.resv.DateUtil;
import hotelclient.ClientMain;
import hotelclient.ClientThread;

public class IdCheckRequest{
	ClientMain main;
	ClientThread clientThread;
	String yyyy;
	String mm;
	String dd;
	String hh24;
	String mi;
	String ss;
	
		
	public IdCheckRequest(ClientMain main) {
		this.main=main;
		this.clientThread=main.clientThread;
				
		Calendar cal=Calendar.getInstance();
		yyyy=Integer.toString(cal.get(Calendar.YEAR));
		//mm�� 0���� �����ϹǷ� ����Ҷ� +1�� ����� �Ѵ�.
		int month=cal.get(Calendar.MONTH)+1;
		mm=DateUtil.getDateString(Integer.toString(month));
		dd=DateUtil.getDateString(Integer.toString(cal.get(Calendar.DATE)));
		hh24=DateUtil.getDateString(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
		mi=DateUtil.getDateString(Integer.toString(cal.get(Calendar.MINUTE)));
		ss=DateUtil.getDateString(Integer.toString(cal.get(Calendar.SECOND)));
		
		
	}
	
	/*//id�ߺ�Ȯ�� ��û
		var msgExcheck1={
			"requestType":"idCheck",
			"room_number":204,
			"requestTime":"2017-04-17-18-19-23",
			"id_to_nick":"jsklsk",
		}*/
	
	public void requestJSON(String id) {						
		JSONObject json=new JSONObject();
		json.put("room_number", main.room_Number);
		json.put("requestType", "idCheck");
		json.put("requestTime", yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss);
		json.put("id_to_nick", id);
		
		String JSONRequest=json.toJSONString();
		System.out.println(JSONRequest);
		clientThread.send(JSONRequest);		
	}

}
