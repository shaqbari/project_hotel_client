package hotelclient.resv;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	//��¥�� 2�� �̸��ϰ�� 0�տ� ���̱� 
	public static String getDateString(String n){
		String str=null;
		
		if(n.length()<2){
			str="0"+n;
		}else{
			str=n;
		}
		return str;
	}
	
	//Ư�� ��¥ �ΰ����� �� ���̰��� �˰� ���� �� ����Ҽ� �ִ¸޼ҵ�
	public static int getDiffDate(String start, String end){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		//SimpleDateFormat�� �̿��� Date��ü�� �ٲٰ�
		//getTime�޼ҵ带 �̿��ϸ� �ð��� long������ ����� �������ش�.
		long diffDays=0;
		
		try {
			Date startDate= formatter.parse(start);
			Date endDate= formatter.parse(end);
			
			//�ð����̸� �ð�, ��, �ʸ� ���� ������ ������ �Ϸ������ ���´�.
			long diff=endDate.getTime()-startDate.getTime();
			diffDays=diff/(24*60*60*1000);			
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		return (int) diffDays;
	}
	
/*	public static void main(String[] args) {
		for(int i=1;i<=12;i++){
			System.out.println(getDateString(Integer.toString(i)));
		}
		System.out.println(getDiffDate("2017-04-29-14-00-00", "2017-05-02-12-00-00"));
	}*/

}
