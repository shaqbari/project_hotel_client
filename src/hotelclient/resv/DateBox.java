/*�� ��¥�� ǥ���ϴ� Ŀ���͸���¡ ������Ʈ*/

package hotelclient.resv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DateBox extends JPanel {
	MyCalendar myCalendar;	
	
	int width;
	int height;
	JLabel la;
	
	int yyyy;
	int mm;
	int dd;
	
	Boolean isClicked=false;//Ŭ���Ǿ����� �ƴ��� �Ǵ��� ����
	
	public DateBox(MyCalendar myCalendar) {
		this.myCalendar=myCalendar;
			
		width=(myCalendar.getWidth()/7)-10;
		height=(myCalendar.getHeight()/6)-20;
		
		System.out.println(width+", "+height);
		setLayout(new BorderLayout());;
		la=new JLabel();
		
		add(la, BorderLayout.NORTH);
		
		//���� ���콺 ������ ����
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pop();
			}
		});		
		
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);		
	}

	
	public void pop(){		
		yyyy=myCalendar.yyyy;
		mm=myCalendar.mm;
		dd=Integer.parseInt(la.getText());
		
		isClicked=!isClicked;
		if (isClicked) {//Ŭ���Ǿ��ٸ� ����ȸ������ �ٲٰ� ArrayList�� �߰�			
			if (myCalendar.resvPanel.count<2) {
				JOptionPane.showMessageDialog(this, yyyy+"�� "+(mm+1)+"�� "+dd+"��");
				setBackground(Color.GRAY);
				if (myCalendar.resvPanel.count==0) {
					myCalendar.resvPanel.la_start_input.setText(yyyy+"-"+(mm+1)+"-"+dd+"-14-00-00");
				}else {
					myCalendar.resvPanel.la_end_input.setText(yyyy+"-"+(mm+1)+"-"+dd+"-12-00-00");
				}
				
				myCalendar.resvPanel.count++;
			}else{
				JOptionPane.showMessageDialog(this, "���̻� ��¥�� ������ �� �����ϴ�.");
				
			}
			
		}else {
			if (myCalendar.resvPanel.count<2) {
				setBackground(Color.LIGHT_GRAY);
								
			}
		}
		
		
	}
	
	
}
