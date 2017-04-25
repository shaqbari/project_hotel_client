package hotelclient.main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLParser {
	SAXParserFactory factory;
	SAXParser parser;
	URL url, dbUrl;
	public MyHandler handler;
	public MyDbConfigHandler dbHandler;
	
	public XMLParser() {
		factory=SAXParserFactory.newInstance();
		
			try {
				parser=factory.newSAXParser();
				url=this.getClass().getResource("/Config.xml");
				parser.parse(new File(url.toURI()), handler=new MyHandler());
				
				dbUrl=this.getClass().getResource("/dbConfig.xml");
				parser.parse(new File(dbUrl.toURI()), dbHandler=new MyDbConfigHandler());
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			System.out.println(handler.getMyRoom());
	}
	
	/*public static void main(String[] args) {
		XMLParser parser=new XMLParser();
		System.out.println(parser.handler.dbConfig);
	}*/
	
}
