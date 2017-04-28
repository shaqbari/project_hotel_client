package hotelclient.network;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.home.HomePanel;
import hotelclient.main.CheckUserPanel;

//�Խ�Ʈ�α��� ���� ����
/*	var msgEx1={
	"responseType":"guest_login",
	"result":"yes",
	"hotel_user_id":1,
	"geust_name":"�輺��",
	"resv_time":"2017-04-17-18-19-23",
	"stay:1		
}*/

public class MemberLoginReact {
	ClientMain main;
	CheckUserPanel checkUserPanel;
	HomePanel homePanel;
	
	JSONObject json;
	
	String result;
	int hotel_user_id;
	String member_name;
	String resv_id;
	String resv_time;
	String end_time;
	int stay;
	
	String yyyy;
	String mm;
	String dd;
	
	//�Խ�Ʈ�α��� ���� ����
		/*var msgEx2={
			"responseType":"membership_login",
			"result":"yes",
			"hotel_user_id":1,
			"member_name":"�����",
			
			//�Ʒ��� ������������ ������
			"resv_time":"2017-04-17-18-19-23",
			"end_time":"2017-04-17-18-19-23",
			"stay:1		
		}*/
	
	
	
	public MemberLoginReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.checkUserPanel=main.checkAdminPanel;
		this.homePanel=main.homePanel;
		this.json=json;
		result=json.get("result").toString();
		if (result.equalsIgnoreCase("yes")) {
			hotel_user_id=Integer.parseInt(json.get("hotel_user_id").toString());
			member_name=json.get("member_name").toString();
			if (json.get("resv_id")!=null) {
				resv_id = json.get("resv_id").toString();
				resv_time = json.get("resv_time").toString();
				end_time=json.get("end_time").toString();
				stay = Integer.parseInt(json.get("stay").toString());
			}			
		}
		
		react();
		
	}
	
	public void reset(){
		checkUserPanel.txt_id.setText("");
		checkUserPanel.txt_pw.setText("");
		checkUserPanel.txt_id.requestFocus();		
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
			System.out.println("�α��ο� �����Ͽ����ϴ�.");			
						
			
			main.la_user.setText(member_name);	
			main.hotel_user_id=hotel_user_id;
			
			if (resv_id!=null) {
				dateProcess(resv_time);
				homePanel.getResv_id_input().setText(resv_id);
				homePanel.getGuest_name_input().setText(member_name);
				homePanel.getResv_time_input().setText(resv_time);
				//stay���ƴ϶� ���ᳯ¥�� ���� db�� �Է�����.
				homePanel.getStay_input()
						.setText(end_time);
			}
			
			reset();
			
			for (int i = 0; i < main.resvPanel.guestInfo.size(); i++) {
				main.resvPanel.guestInfo.get(i).setVisible(false);//ȸ���� �´ٸ� ����� ��ȸ�� �߰��� �Ⱥ��̰� �Ѵ�.				
			}		
			
			main.setPage(2);
			JOptionPane.showMessageDialog(main, member_name+"�� �ݰ����ϴ�.");			
		}else if (result.equalsIgnoreCase("no")) {
			System.out.println("�α��ο� �����Ͽ����ϴ�.");
			
			reset();
			JOptionPane.showMessageDialog(main, "�α��� ������ �߸��ԷµǾ����ϴ�.");
		}		
		
	}
	
	
	
}
