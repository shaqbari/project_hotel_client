package hotelclient.resv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import hotelclient.ClientMain;

public class RoomButton extends JButton implements ActionListener{
	ClientMain main;
	ResvPanel resvPanel;
	
	public RoomButton(ClientMain main, String name) {
		this.main=main;
		this.resvPanel=main.resvPanel;
		this.setText(name);
		
		
		this.addActionListener(this);//�ڱ��ڽŰ� ������ ��������� �Ѵ�.
	}
	
	//����Ȯ�ο� ȣ�� ����ϰ�
	//���ݵ� ���������.
	public void actionPerformed(ActionEvent e) {
		main.resvPanel.la_room_number_input.setText(this.getText());
	}

}
