package hotelclient.main;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler{
	
	String myRoom;
	String myIp;
	String myPort;
	
	boolean room_number;
	boolean ip;
	boolean port;
		
	public MyHandler() {
		
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("room_number")) {
			room_number=true;
		}else if (qName.equalsIgnoreCase("ip")) {
			ip=true;
		}else if (qName.equalsIgnoreCase("port")) {
			port=true;
		}		
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
	
		if (room_number) {
			myRoom=new String(ch, start, length);
			ip=false;
		}else if (ip) {
			myIp=new String(ch, start, length);
			ip=false;
		}else if (port) {
			myPort=new String(ch, start, length);	
			port=false;
		}
	}
}
