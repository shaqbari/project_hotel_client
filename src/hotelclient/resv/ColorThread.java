package hotelclient.resv;

public class ColorThread extends Thread{
	ResvPanel resvPanel;
	DateBox[] box;
	
	public ColorThread(ResvPanel resvPanel) {
		this.resvPanel=resvPanel;
		this.box=resvPanel.myCalendar.box;
		
		
		//this.start();
	}
	
	
	public void coloring(){
		if (resvPanel.count==1) {
			for (int i = 0; i < box.length; i++) {
				/*if (bo) {
					
				}
				*/
			}
		}
		
	}
	
	
	

	public void run() {
		coloring();
		
	}
}
