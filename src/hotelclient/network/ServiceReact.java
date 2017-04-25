package hotelclient.network;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;

public class ServiceReact {
	ClientMain main;
	JSONObject json;
	
	String content;
	
	public ServiceReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.json=json;
		
		react();
		
	}

	public void react(){
		content=json.get("content").toString();
		main.chatPanel.area.append(content+"서비스가 주문되었습니다"+"\n");	
		
	}
	
}
