package hotelclient.resv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import hotelclient.ClientMain;
import hotelclient.home.RoomOption;

public class RoomButton extends JButton implements ActionListener{
	ClientMain main;
	ResvPanel resvPanel;
	ArrayList<RoomOption> optionInfo;
	
	int stay;
	String option;
	int price;
	int totalPrice;
	
	public RoomButton(ClientMain main, String name) {
		this.main=main;
		this.resvPanel=main.resvPanel;
		this.optionInfo=resvPanel.optionInfo;		
		this.stay=resvPanel.stay;
		this.option=resvPanel.la_option_input.getText();
		this.setText(name);
		
		setTotalPrice();
		
		this.addActionListener(this);//자기자신과 리스너 연결해줘야 한다.
	}
	
	public void setTotalPrice(){
		for (int i = 0; i < optionInfo.size(); i++) {
			//table정보의 optionname과 선택한 option이름이 같다면 price를 정한다.
			if (optionInfo.get(i).getRoom_option_name().equalsIgnoreCase(option)) {
				price=optionInfo.get(i).getRoom_option_price();
			}
		}
		System.out.println("옵션 가격은 "+price);
		
		this.totalPrice=price*stay;
	}
	
	
	//예약확인에 호수 출력하고
	//가격도 계산해주자.
	public void actionPerformed(ActionEvent e) {
		main.resvPanel.la_room_number_input.setText(this.getText());
		main.resvPanel.la_price_input.setText(Integer.toString(totalPrice));
	}

}
