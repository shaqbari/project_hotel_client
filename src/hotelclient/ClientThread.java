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
import hotelclient.main.GuestLoginReact;

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
		System.out.println("Ŭ���̾�Ʈ ����");
	}
	
	public void listen(){
		try {
			String msg=buffr.readLine();					
			JSONObject json=null;		
			JSONParser parser=new JSONParser();
			
			//���� json msg�� parsing�Ѵ�.
			try {
				json=(JSONObject)parser.parse(msg);					
			} catch (ParseException e) {
				e.printStackTrace();
			}			
			String responseType=json.get("responseType").toString();
			
			//�Ľ̰�� responseType������ �ٸ� ������ �Ѵ�.
			if (responseType.equalsIgnoreCase("chat")) {
				ChatNetwork chatNetwork=new ChatNetwork();
			
			}else if (responseType.equalsIgnoreCase("service")) {
				
				
			}else if (responseType.equalsIgnoreCase("resv")) {
				
				
				
			}else if (responseType.equalsIgnoreCase("guest_login")) {								
				GuestLoginReact  guestLoginReact=new GuestLoginReact(main, json);
				
			}else if (responseType.equalsIgnoreCase("membership_login")) {
						
				
				
			}else if(responseType.equalsIgnoreCase("membership_regist")){
				
				
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

	//��ӵ�� �ִ´�.
	public void run() {
		while(flag){
			listen();
		}
	}

}
