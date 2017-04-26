package hotelclient.network;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import hotelclient.ClientMain;

public class ServiceReact {
	ClientMain main;
	JSONObject json;
	String result;
	String content;
	
	public ServiceReact(ClientMain main, JSONObject json) {
		this.main=main;
		this.json=json;
		result=json.get("result").toString();
		
		react();
		
	}

	public void react(){
		if (result.equalsIgnoreCase("yes")) {
			content=json.get("content").toString();
			main.chatPanel.area.append(content+"���񽺰� �ֹ��Ǿ����ϴ�"+"\n");	
			System.out.println("���񽺿Ϸ�");			
			JOptionPane.showMessageDialog(main, "���� �ֹ��� �Ϸ�Ǿ����ϴ�.\n���񽺹�ȣ : "+json.get("service_use_id").toString());
			//main.resvPanel.refrash();
		}else if (result.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(main, "���� �ֹ��� ���еǾ����ϴ�. �ٽ� �õ����ֽñ� �ٶ��ϴ�.");
			//main.resvPanel.refrash();
		}	
		
	}
	
}
