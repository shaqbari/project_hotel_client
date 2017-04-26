/*각 날짜를 표현하는 커스터마이징 컴포넌트*/

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
	
	Boolean isClicked=false;//클릭되었는지 아닌지 판단할 변수
	
	public DateBox(MyCalendar myCalendar) {
		this.myCalendar=myCalendar;
			
		width=(myCalendar.getWidth()/7)-10;
		height=(myCalendar.getHeight()/6)-20;
		
		setLayout(new BorderLayout());;
		la=new JLabel();
		
		add(la, BorderLayout.NORTH);
		
		
		//나와 마우스 리스너 연결//dd값이 있을 때만 pop한다.
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
		if (la.getText().length()!=0) {
			yyyy = myCalendar.yyyy;
			mm = myCalendar.mm;
			dd = Integer.parseInt(la.getText());
			isClicked = !isClicked;
			if (isClicked) {//클릭되었다면 진한회색으로 바꾸고 ArrayList에 추가			
				if (myCalendar.resvPanel.count < 2) {
					JOptionPane.showMessageDialog(this, yyyy + "년 " + (mm + 1) + "월 " + dd + "일");
					setBackground(Color.GRAY);
					//한자리인 달이나 일 앞에 0이 없으면 인식하지 못한다. mydateUtil이용
					if (myCalendar.resvPanel.count == 0) {//첫번째 선택시 해당날과 다음날 출력

						String thisDate = yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1)) + "-"
								+ DateUtil.getDateString(Integer.toString(dd)) + "-14-00-00";
						myCalendar.resvPanel.la_start_input.setText(thisDate);

						String nextDate = DateUtil
								.getPlusDate(yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1)) + "-"
										+ DateUtil.getDateString(Integer.toString(dd)) + "-12-00-00", 1);
						myCalendar.resvPanel.la_end_input.setText(nextDate);

						setStay();
					} else {//두번째 선택시 조건에 따라 출력
								//첫번째 날보다 전의 날짜 선택하면 startday표시수정 다음 날짜 선택하면 endday표시 수정
						System.out.println(compare());
						if (compare() > 0) {//뒤에거 세팅
							myCalendar.resvPanel.la_end_input.setText(
									DateUtil.getPlusDate(yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1))
											+ "-" + DateUtil.getDateString(Integer.toString(dd)) + "-12-00-00", 1));
						} else {//앞에거 세팅						
							myCalendar.resvPanel.la_start_input
									.setText(yyyy + "-" + DateUtil.getDateString(Integer.toString(mm + 1)) + "-"
											+ DateUtil.getDateString(Integer.toString(dd)) + "-14-00-00");
						}

						//달력 앞뒤 버튼 불활성화
						myCalendar.bt_prev.setEnabled(false);
						myCalendar.bt_next.setEnabled(false);

						setStay();
					}

					myCalendar.resvPanel.count++;
				} else {
					JOptionPane.showMessageDialog(this, "더이상 날짜를 선택할 수 없습니다.");

				}

			} else {
				if (myCalendar.resvPanel.count < 2) {
					setBackground(Color.LIGHT_GRAY);

				}
			} 
		}
		
		
	}
	
	
	//선택한 날짜 전후 비교위한 간격 계산
	public int compare(){
		String first=myCalendar.resvPanel.la_start_input.getText();
		String second=yyyy+"-"+DateUtil.getDateString(Integer.toString(mm+1))+"-"+DateUtil.getDateString(Integer.toString(dd))+"-14-00-00";
		
		int interval=DateUtil.getDiffDate(first , second);		
		
		return interval;
	}
	
	
	
	//선택한 날짜간 간격 계산
	public void setStay(){
		String startDay=myCalendar.resvPanel.la_start_input.getText();
		String endDay=myCalendar.resvPanel.la_end_input.getText();
		//14시부터시작해 12시에 끝나므로 하루를 더해줘야 한다.
		int stay=myCalendar.resvPanel.stay=DateUtil.getDiffDate(startDay , endDay)+1;
				
		myCalendar.resvPanel.la_stay_input.setText(Integer.toString(stay)+"박 "+Integer.toString(stay+1)+"일");
	}
	
		
	
	
	
}
