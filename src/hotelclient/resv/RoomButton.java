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
		
		
		this.addActionListener(this);//자기자신과 리스너 연결해줘야 한다.
	}
	
	//예약확인에 호수 출력하고
	//가격도 계산해주자.
	public void actionPerformed(ActionEvent e) {
		main.resvPanel.la_room_number_input.setText(this.getText());
	}

}
