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
	
	 // The HTTPLogger is questionable since at this point i haven't done any web programming.
	
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


	@Override
	public void log(int level, String message){
		
		switch(level){
		case 1 :
			writeLog(levelOne, message);
			break;
		case 2 :
			writeLog(levelTwo, message);
			break;
		case 3 :
			writeLog(levelThree, message);
			break;
			
		default :
			System.out.println(level + " is an invalid log level.");				
		}
		
	}
	
	private void writeLog(String loglvl, String message){
		
		String rawData = loglvl + "::" + getTimestamp() + "::" + message;
		String encodedData1 = URLEncoder.encode( rawData );
		
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
		
	}
	
	private String getTimestamp(){
		
		TimeZone timezone = TimeZone.getTimeZone("UTC");
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
		dateformat.setTimeZone(timezone);
		String currentTimestamp = dateformat.format(new Date());
		
		return currentTimestamp;
	}

}
