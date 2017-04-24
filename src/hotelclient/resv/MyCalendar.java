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
	ArrayList<DateBox> clickedBox=new ArrayList<DateBox>();//클릭한 Datebox가 저장될 ArrayList
	
	
	Calendar cal=Calendar.getInstance();
	
	
	
	//현재날짜를 계산하기 위한 변수
	int yyyy;
	int mm;
	int dd;
	
	public MyCalendar(ResvPanel resvPanel) {
		this.resvPanel=resvPanel;
		int width=resvPanel.WIDTH*2/3-10;
		int height=resvPanel.HEIGHT-10;
		
		p_north=new JPanel();
		p_center=new JPanel();
		bt_prev=new JButton("◀");
		bt_next=new JButton("▶");
		la_title=new JLabel("2017년 4월");
		la_title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		this.setLayout(new BorderLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);		
		
		//System.out.println(cal);
		yyyy=cal.get(Calendar.YEAR);//int field가 인수로 요구되면 상수를 강력하게 생각하자
		mm=cal.get(Calendar.MONTH); //0부터 시작하기 때문에 출력할때만 +1해야한다.
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
	
	//날짜출력 메소드 정의
	public void printDate(){
		System.out.println("호출되나?");
		
		//현재 날짜를 라벨에 출력
		la_title.setText(yyyy+"-"+(mm+1));		
		
		//사각형모두 날리기!
		p_center.removeAll();
		
		//각 월이 무슨 요일부터 시작하나?
		//해당월을 1일로 맞추고, 그 요일이 무슨 요일인지 물어보면 된다.
		cal.set(yyyy, mm, 1);
		int firstDay=cal.get(Calendar.DAY_OF_WEEK);
		//System.out.println(mm+"의 시작요일은"+firstDay); //일요일부터 1부터시작해서 토요일7로 끝난다.
		int num=0;//실제 찍힐 날짜용 변수
		
		//각 월의 마지막 날짜 알아맞추기
		//왜? 반복문의 끝을 알기 위해서,
		cal.set(yyyy, mm+1, 0);//그다음달의 1일의 1일전	
		int lastDay=cal.get(Calendar.DATE);
		
		//라벨만 바꾸거나 제거하고 다시 붙여야 한다.			
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
		
		if (obj==bt_prev) {//이전달
			mm--;
			if(mm<0){
				yyyy--;
				mm=11;
			}			
			printDate();
		}else if (obj==bt_next) {//다음달
			mm++;
			if(mm>11) {
				yyyy++;
				mm=0;
			}			
			printDate();
		}
	}

}
