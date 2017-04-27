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
	
	/*//id중복확인 응답
		var msgExCheck2{		//중복안됨
			"responseType":"idCheck",
			"result":"yes",
			
		}	
		var msgExCheck3{		//중복됨
			"responseType":"idCheck",
			"result":"no"
		}*/
	
	public void react() {
		if (result.equalsIgnoreCase("yes")) {	
			JOptionPane.showMessageDialog(main, "id가 중복되지 않습니다.");
			main.regMemberPanel.bt_regist.setEnabled(true);
			
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "id가 중복됩니다. 다른 id를 입력해주세요");
			main.regMemberPanel.initialSet();
		}		
		
	}
	
	
	
}
