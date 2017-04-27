package hotelclient.network;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hotelclient.ClientMain;
import hotelclient.resv.ResvPanel;
import hotelclient.resv.RoomButton;


public class IdCheckReact {
	ClientMain main;
	
	JSONObject json;
	
	String result;
	ArrayList<String> available_room =new ArrayList<String>();
	
	String yyyy;
	String mm;
	String dd;
	


	
	public IdCheckReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.json=json;
		result=json.get("result").toString();
			
		react();
		
	}
	
	/*//id�ߺ�Ȯ�� ����
		var msgExCheck2{		//�ߺ��ȵ�
			"responseType":"idCheck",
			"result":"yes",
			
		}	
		var msgExCheck3{		//�ߺ���
			"responseType":"idCheck",
			"result":"no"
		}*/
	
	public void react() {
		if (result.equalsIgnoreCase("yes")) {	
			JOptionPane.showMessageDialog(main, "id�� �ߺ����� �ʽ��ϴ�.");
			main.regMemberPanel.bt_regist.setEnabled(true);
			
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "id�� �ߺ��˴ϴ�. �ٸ� id�� �Է����ּ���");
			main.regMemberPanel.initialSet();
		}		
		
	}
	
	
	
}
