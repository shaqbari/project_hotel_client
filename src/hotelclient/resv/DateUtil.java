package hotelclient.resv;

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
	
/*	public static void main(String[] args) {
		for(int i=1;i<=12;i++){
			System.out.println(getDateString(Integer.toString(i)));
		}
	}
*/	
}
