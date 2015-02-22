package logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class HTTPLogger implements MyLogger {
	
	private final String levelOne = "INFO";
	private final String levelTwo = "WARNING";
	private final String levelThree = "PLSCHECKFFS";
	private URL MyURL;
	private HttpURLConnection connection;
	
	public HTTPLogger(){
		try {
			MyURL = new URL("http://www.example.com/page.php");
			String type = "application/x-www-form-urlencoded";
			connection = (HttpURLConnection) MyURL.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty( "Content-Type", type );
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void log(int level, String message){
		
		switch(level){
		case 1 :
			String rawData1 = levelOne + "::" + getTimestamp() + "::" + message;
			String encodedData1 = URLEncoder.encode( rawData1 );
			
			try {
				OutputStream os;
				os = connection.getOutputStream();
				os.write(encodedData1.getBytes());
				os.close();
				System.out.println("Log successful.");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2 :
			String rawData2 = levelTwo + "::" + getTimestamp() + "::" + message;
			String encodedData2 = URLEncoder.encode( rawData2 );
			
			try {
				OutputStream os;
				os = connection.getOutputStream();
				os.write(encodedData2.getBytes());
				os.close();
				System.out.println("Log successful.");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 3 :
			String rawData3 = levelThree + "::" + getTimestamp() + "::" + message;
			String encodedData3 = URLEncoder.encode( rawData3 );
			
			try {
				OutputStream os;
				os = connection.getOutputStream();
				os.write(encodedData3.getBytes());
				os.close();
				System.out.println("Log successful.");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		default :
			System.out.println(level + " is an invalid log level.");				
		}
		
	}
	
	private String getTimestamp(){
		
		TimeZone timezone = TimeZone.getTimeZone("UTC");
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
		dateformat.setTimeZone(timezone);
		String currentTimestamp = dateformat.format(new Date());
		
		return currentTimestamp;
	}

}
