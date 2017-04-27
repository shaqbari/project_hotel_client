package hotelclient.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hotelclient.ClientMain;
import hotelclient.ClientThread;

public class ChatPanel extends JPanel {
	ClientMain main;			
	
	JPanel p_chat;
	JLabel la_desc;
	public JTextArea area;
	JScrollPane scroll;
	JTextField txt_input;
	
	Calendar cal;
	String yyyy, mm, dd, hh24, mi, ss;

	
	public ChatPanel(ClientMain main) {
		this.main=main;		
		
		p_chat=new JPanel();
		la_desc=new JLabel("�����ڿ� ä��");
		area=new JTextArea(12,16);
		scroll=new JScrollPane(area);
		txt_input=new JTextField(20);
				
		p_chat.setLayout(new BorderLayout());
		p_chat.add(la_desc, BorderLayout.NORTH);
		p_chat.add(scroll);
		p_chat.add(txt_input, BorderLayout.SOUTH);
		
		add(p_chat);
		
		
		txt_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					System.out.println("�����ƴ�>");
					//�����͸��Լ������� ���������� final�� �νİ����ϴ�!!
					//clientThread�� Ŭ���������� �����ϰ� �����ڿ��� new�ϰų�
					//���ΰ� �����Ű�� �̻����ڳ������� ��������� �������� ������
					//�����͸��Լ������� ����� �� ����.
					//main.clientThread.sendChat(txt_input.getText());
					cal=Calendar.getInstance();
					yyyy=Integer.toString(cal.get(Calendar.YEAR));
					int month=cal.get(Calendar.MONTH)+1;
					mm=Integer.toString(month);
					 dd=Integer.toString(cal.get(Calendar.DATE));
					 hh24=Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
					 mi=Integer.toString(cal.get(Calendar.MINUTE));
					 ss=Integer.toString(cal.get(Calendar.SECOND));
					 
					 String time=yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss;
					 
					sendChat(txt_input.getText()+" "+time);
					txt_input.setText("");
					txt_input.requestFocus();
				}
			}
		});
		
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(250,300));
		setVisible(true);	
	}
	
	//ä���� ���
	/*{
		"requestType":"chat",
		"room_number":203,
		"requestTime":"2017-04-17-18-19-23",
		"hotel_user_id":3,
		"content":"�ȳ��ϼ���"
	}*/
	
	public void sendChat(String msg){
		
		 cal=Calendar.getInstance();
		yyyy=Integer.toString(cal.get(Calendar.YEAR));
		int month=cal.get(Calendar.MONTH)+1;
		mm=Integer.toString(month);
		 dd=Integer.toString(cal.get(Calendar.DATE));
		 hh24=Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		 mi=Integer.toString(cal.get(Calendar.MINUTE));
		 ss=Integer.toString(cal.get(Calendar.SECOND));
		
		String time=yyyy+"-"+mm+"-"+dd+"-"+hh24+"-"+mi+"-"+ss;
		
		StringBuffer json=new StringBuffer();
		json.append("{");
		json.append("\"requestType\":\"chat\",");
		json.append("\"room_number\":"+main.room_Number+",");
		json.append("\"requestTime\":\""+time+"\",");
		json.append("\"hotel_user_id\":"+main.hotel_user_id+",");			
		json.append("\"content\":\""+msg+"\"");			
		json.append("}");		
		
		
		
		main.clientThread.send(json.toString());
	}
	
}
