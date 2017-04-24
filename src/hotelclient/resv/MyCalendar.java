package hotelclient.resv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyCalendar extends JPanel implements ActionListener{
	public ResvPanel resvPanel;
	int width;
	int  height;
	
	JPanel p_north, p_center;
	JButton bt_prev, bt_next;
	JLabel la_title;
	DateBox[] box=new DateBox[7*6];
	ArrayList<DateBox> clickedBox=new ArrayList<DateBox>();//Ŭ���� Datebox�� ����� ArrayList
	
	
	Calendar cal=Calendar.getInstance();
	
	
	
	//���糯¥�� ����ϱ� ���� ����
	int yyyy;
	int mm;
	int dd;
	
	public MyCalendar(ResvPanel resvPanel) {
		this.resvPanel=resvPanel;
		int width=resvPanel.WIDTH*2/3-10;
		int height=resvPanel.HEIGHT-10;
		
		p_north=new JPanel();
		p_center=new JPanel();
		bt_prev=new JButton("��");
		bt_next=new JButton("��");
		la_title=new JLabel("2017�� 4��");
		la_title.setFont(new Font("���� ���", Font.BOLD, 25));
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		this.setLayout(new BorderLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);		
		
		//System.out.println(cal);
		yyyy=cal.get(Calendar.YEAR);//int field�� �μ��� �䱸�Ǹ� ����� �����ϰ� ��������
		mm=cal.get(Calendar.MONTH); //0���� �����ϱ� ������ ����Ҷ��� +1�ؾ��Ѵ�.
		dd=cal.get(Calendar.DATE);
		
		System.out.println(yyyy+"-"+mm+1+"-"+dd);
						
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		la_title.setPreferredSize(new Dimension(95, 40));
		
		System.out.println(resvPanel.p_center.getWidth()+", "+resvPanel.p_center.getHeight());		
		//setSize(resvPanel.p_center.getWidth()-10, resvPanel.p_center.getHeight()-10);
		setSize(width, height);		
		setBackground(Color.RED);
		setVisible(true);		
		
		printDate();
	
	}
	
	//��¥��� �޼ҵ� ����
	public void printDate(){
		System.out.println("ȣ��ǳ�?");
		
		//���� ��¥�� �󺧿� ���
		la_title.setText(yyyy+"-"+(mm+1));		
		
		//�簢����� ������!
		p_center.removeAll();
		
		//�� ���� ���� ���Ϻ��� �����ϳ�?
		//�ش���� 1�Ϸ� ���߰�, �� ������ ���� �������� ����� �ȴ�.
		cal.set(yyyy, mm, 1);
		int firstDay=cal.get(Calendar.DAY_OF_WEEK);
		//System.out.println(mm+"�� ���ۿ�����"+firstDay); //�Ͽ��Ϻ��� 1���ͽ����ؼ� �����7�� ������.
		int num=0;//���� ���� ��¥�� ����
		
		//�� ���� ������ ��¥ �˾Ƹ��߱�
		//��? �ݺ����� ���� �˱� ���ؼ�,
		cal.set(yyyy, mm+1, 0);//�״������� 1���� 1����	
		int lastDay=cal.get(Calendar.DATE);
		
		//�󺧸� �ٲٰų� �����ϰ� �ٽ� �ٿ��� �Ѵ�.			
		for (int i = 0; i < box.length; i++) {
			box[i]=new DateBox(this);						
			p_center.add(box[i]);						
						
			if(i>=firstDay-1){
				num++;	
			}else {
				num=0;
			}
			
			if (num!=0) {
				if (num<=lastDay) {
					box[i].la.setText(Integer.toString(num));
				}
			}else{
				box[i].la.setText("");					
			}
		}		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj=(Object)e.getSource();
		
		if (obj==bt_prev) {//������
			mm--;
			if(mm<0){
				yyyy--;
				mm=11;
			}			
			printDate();
		}else if (obj==bt_next) {//������
			mm++;
			if(mm>11) {
				yyyy++;
				mm=0;
			}			
			printDate();
		}
	}

}
