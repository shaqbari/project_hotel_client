package hotelclient.resv;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
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
	
	
	//Ư����¥���� Ư����¥�� ���� ���� �˰� ���� �� ����Ѵ�.
	public static String getPlusDate(String dateString, int plus){
		//HH�� 0~24�ð��̴�.
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA);
		
		String result=null;
		
		try {
			Date date=formatter.parse(dateString);
			Long plusDate=date.getTime()+(plus*24*60*60*1000);
			Date plusDate2=new Date(plusDate);
			
			//format�� �̿��ϸ� ���ڿ��� ���� �� �ִ�.
			result=formatter.format(plusDate2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	/*public static void main(String[] args) {
		for(int i=1;i<=12;i++){
			System.out.println(getDateString(Integer.toString(i)));
		}
		System.out.println(getDiffDate("2017-04-29-14-00-00", "2017-05-02-12-00-00"));
		System.out.println(getPlusDate("2017-04-29-16-00-00", 3 ));
	
	}*/

}
