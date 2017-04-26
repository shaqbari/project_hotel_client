package hotelclient.network;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;

public class ServiceReact {
	ClientMain main;
	JSONObject json;
	String result;
	String content;
	
	public ServiceReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.json=json;
		result=json.get("result").toString();
		
		react();
		
	}

	public void react(){
		if (result.equalsIgnoreCase("yes")) {
			content=json.get("content").toString();
			main.chatPanel.area.append(content+"서비스가 주문되었습니다"+"\n");	
			System.out.println("서비스완료");			
			JOptionPane.showMessageDialog(main, "서비스 주문이 완료되었습니다.\n서비스번호 : "+json.get("service_use_id").toString());
			//main.resvPanel.refrash();
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "서비스 주문이 실패되었습니다. 다시 시도해주시기 바랍니다.");
			//main.resvPanel.refrash();
		}	
		
	}
	
}
