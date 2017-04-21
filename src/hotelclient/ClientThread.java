package hotelclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

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
			System.out.println("메시지 도착");
			main.chatPanel.area.append(msg+"\n");		
			
		
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
