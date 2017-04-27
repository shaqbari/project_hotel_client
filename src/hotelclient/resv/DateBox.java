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
	
	int stay;
	
	Boolean isClicked=false;//Ŭ���Ǿ����� �ƴ��� �Ǵ��� ����
	
	public DateBox(MyCalendar myCalendar) {
		this.myCalendar=myCalendar;
			
		width=(myCalendar.getWidth()/7)-10;
		height=(myCalendar.getHeight()/6)-20;
		
		setLayout(new BorderLayout());;
		la=new JLabel();
		
		add(la, BorderLayout.NORTH);
		
		
		//���� ���콺 ������ ����//dd���� ���� ���� pop�Ѵ�.
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yyyy = myCalendar.yyyy;
				mm = myCalendar.mm;
				dd = Integer.parseInt(la.getText());
				isClicked = !isClicked;
				if (myCalendar.resvPanel.count<2) {
					myCalendar.dateBuffer[myCalendar.resvPanel.count][0]=yyyy;				
					myCalendar.dateBuffer[myCalendar.resvPanel.count][1]=mm;				
					myCalendar.dateBuffer[myCalendar.resvPanel.count][2]=dd;				
				}
				
				pop();
				
				myCalendar.resvPanel.count++;
			}
		});		
		
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);		
	}
	
	

	
	public void pop(){		
		if (la.getText().length()!=0) {
			
			if (isClicked) {//Ŭ���Ǿ��ٸ� ����ȸ������ �ٲٰ� ArrayList�� �߰�			
				if (myCalendar.resvPanel.count < 2) {
					JOptionPane.showMessageDialog(this, yyyy + "�� " + (mm + 1) + "�� " + dd + "��");
					setBackground(Color.GRAY);
					//���ڸ��� ���̳� �� �տ� 0�� ������ �ν����� ���Ѵ�. mydateUtil�̿�
					if (myCalendar.resvPanel.count == 0) {//ù��° ���ý� �ش糯�� ������ ���

						String thisDate = yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1)) + "-"
								+ DateUtil.getDateString(Integer.toString(dd)) + "-14-00-00";
						myCalendar.resvPanel.la_start_input.setText(thisDate);

						String nextDate = DateUtil
								.getPlusDate(yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1)) + "-"
										+ DateUtil.getDateString(Integer.toString(dd)) + "-12-00-00", 1);
						myCalendar.resvPanel.la_end_input.setText(nextDate);

						setStay();
					} else {//�ι�° ���ý� ���ǿ� ���� ���
								//ù��° ������ ���� ��¥ �����ϸ� startdayǥ�ü��� ���� ��¥ �����ϸ� enddayǥ�� ����
						System.out.println(compare());
						if (compare() > 0) {//�ڿ��� ����
							myCalendar.resvPanel.la_end_input.setText(
									DateUtil.getPlusDate(yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1))
											+ "-" + DateUtil.getDateString(Integer.toString(dd)) + "-12-00-00", 1));
						} else {//�տ��� ����						
							myCalendar.resvPanel.la_start_input
									.setText(yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1)) + "-"
											+ DateUtil.getDateString(Integer.toString(dd)) + "-14-00-00");
						}
						
						
						
						int beforeMM=myCalendar.dateBuffer[0][1];
						int nowMM=myCalendar.dateBuffer[1][1];
						
						int first=myCalendar.dateBuffer[0][2];
						int second=myCalendar.dateBuffer[1][2];
						int large;
						int small;
						if (first>second) {
							large=first;
							small=second;
						}else {
							large=second;
							small=first;		
						}
						
						
						//ù��° �����Ѱſ� �ι�° �����Ѱ� ���̿� coloring
						for (int i = 0; i < myCalendar.box.length; i++) {
							
							if(beforeMM==nowMM){//�������̶��
								if(myCalendar.box[i].la.getText().length()!=0){//���� �ִ� box�� ���� �ִ´�.
									if (Integer.parseInt(myCalendar.box[i].la.getText())>=small&&
											Integer.parseInt(myCalendar.box[i].la.getText())<=large) {
										myCalendar.box[i].setBackground(Color.GRAY);
									}
								}
							}else if (beforeMM>nowMM) {//�������̶��
								if(myCalendar.box[i].la.getText().length()!=0){
									if (Integer.parseInt(myCalendar.box[i].la.getText())>=second) {
										myCalendar.box[i].setBackground(Color.GRAY);
									}
								}							
							}else if (beforeMM<nowMM) {//�������̶��
								if(myCalendar.box[i].la.getText().length()!=0){
									if (Integer.parseInt(myCalendar.box[i].la.getText())<=second) {
										myCalendar.box[i].setBackground(Color.GRAY);
									}
								}	
							}
							
							
						}
						
						

						//�޷� �յ� ��ư ��Ȱ��ȭ
						myCalendar.bt_prev.setEnabled(false);
						myCalendar.bt_next.setEnabled(false);

						setStay();
					}

					//myCalendar.resvPanel.count++;
				} else {
					JOptionPane.showMessageDialog(this, "���̻� ��¥�� ������ �� �����ϴ�.");

				}

			} else {
				if (myCalendar.resvPanel.count < 2) {
					setBackground(Color.LIGHT_GRAY);

				}
			} 
		}
		
		
	}
	
	
	//������ ��¥ ���� ������ ���� ���
	public int compare(){
		String first=myCalendar.resvPanel.la_start_input.getText();
		String second=yyyy+"-"+DateUtil.getDateString(Integer.toString(mm+1))+"-"+DateUtil.getDateString(Integer.toString(dd))+"-14-00-00";
		
		int interval=DateUtil.getDiffDate(first , second);		
		
		return interval;
	}
	
	
	
	//������ ��¥�� ���� ���
	public void setStay(){
		String startDay=myCalendar.resvPanel.la_start_input.getText();
		String endDay=myCalendar.resvPanel.la_end_input.getText();
		//14�ú��ͽ����� 12�ÿ� �����Ƿ� �Ϸ縦 ������� �Ѵ�.
		int stay=myCalendar.resvPanel.stay=DateUtil.getDiffDate(startDay , endDay)+1;
				
		myCalendar.resvPanel.la_stay_input.setText(Integer.toString(stay)+"�� "+Integer.toString(stay+1)+"��");
	}
	
		
	
	
	
}
