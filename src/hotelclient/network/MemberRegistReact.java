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
	

/*	//ȸ������ ����
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
			System.out.println("����Ϸ�");			
		
			JOptionPane.showMessageDialog(resvPanel, "ȸ�����Կ� �����߽��ϴ�.\nȸ����ȣ : "+json.get("membership_id").toString());
			main.setPage(0);
			main.regMemberPanel.initialSet();
			
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "ȸ�����Կ� �����߽��ϴ�. �ٽ� �õ����ֽñ� �ٶ��ϴ�.");
			main.regMemberPanel.initialSet();
		}		
		
	}
	
	
	
}
