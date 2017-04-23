package hotelclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import hotelclient.chat.ChatNetwork;
import hotelclient.network.GuestLoginReact;
import hotelclient.network.MemberLoginReact;
import hotelclient.network.MemberRegistReact;

public class ClientThread implements Runnable{
	ClientMain main;
	Socket socket;
	Thread thread;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	boolean flag=true;
	
	public ClientThread(ClientMain main) {
		this.main=main;
		this.socket=main.socket;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		thread=new Thread(this);
		thread.start();
		System.out.println("클라이언트 가동");
	}
	
	public void listen(){
		try {
			String msg=buffr.readLine();					
			JSONObject json=null;		
			JSONParser parser=new JSONParser();
			
			//받은 json msg를 parsing한다.
			try {
				json=(JSONObject)parser.parse(msg);					
			} catch (ParseException e) {
				e.printStackTrace();
			}			
			String responseType=json.get("responseType").toString();
			
			//파싱결과 responseType에따라 다른 반응을 한다.
			if (responseType.equalsIgnoreCase("chat")) {
				ChatNetwork chatNetwork=new ChatNetwork();
			
			}else if (responseType.equalsIgnoreCase("service")) {
				
				
			}else if (responseType.equalsIgnoreCase("resv")) {
				
				
				
			}else if (responseType.equalsIgnoreCase("guest_login")) {								
				GuestLoginReact  guestLoginReact=new GuestLoginReact(main, json);
				
			}else if (responseType.equalsIgnoreCase("membership_login")) {
				MemberLoginReact memberLoginReact=new MemberLoginReact(main, json);
				
				
			}else if(responseType.equalsIgnoreCase("membership_regist")){
				MemberRegistReact memberRegistReact=new MemberRegistReact(main, json);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void send(String msg){
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//계속듣고 있는다.
	public void run() {
		while(flag){
			listen();
		}
	}

}
