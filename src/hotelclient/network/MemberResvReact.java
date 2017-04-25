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
	

	/*	//ȸ�� �濹�� ����
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
			System.out.println("����Ϸ�");			
		
			JOptionPane.showMessageDialog(main, "���࿡ �����߽��ϴ�.\n�����ȣ : "+json.get("resv_id").toString());
			main.resvPanel.refrash();
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "���࿡ �����߽��ϴ�. �ٽ� �õ����ֽñ� �ٶ��ϴ�.");
			main.resvPanel.refrash();
		}		
		
	}
	
	
	
}
