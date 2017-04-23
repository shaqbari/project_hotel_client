package hotelclient.network;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;

public class ChatReact {
	ClientMain main;
	JSONObject json;
	
	String content;
	
	public ChatReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.json=json;
		
		react();
		
	}

	public void react(){
		content=json.get("content").toString();
		main.chatPanel.area.append(content+"\n");		
		
	}
	
}
