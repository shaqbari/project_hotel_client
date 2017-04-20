package hotelclient.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hotelclient.ClientMain;
import hotelclient.ClientThread;

public class ChatPanel extends JPanel {
	ClientMain main;			
	Connection con;	
	
	JPanel p_chat;
	JLabel la_desc;
	public JTextArea area;
	JScrollPane scroll;
	JTextField txt_input;
	
	public ChatPanel(ClientMain main) {
		this.main=main;		
		
		this.con=main.con;		
		
		p_chat=new JPanel();
		la_desc=new JLabel("�����ڿ� ä��");
		area=new JTextArea(15, 20);
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
					//clientThread�� ��������� �����ϰ� �����ڿ��� new�ϰų�
					//���ΰ� �����Ű�� �̻����ڳ������� ���������� �������� ������
					//�����͸��Լ������� ����� �� ����.
					
					main.clientThread.send(txt_input.getText());
					txt_input.setText("");
					txt_input.requestFocus();
				}
			}
		});
		
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);	
	}
}
