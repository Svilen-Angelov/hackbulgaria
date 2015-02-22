package logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ConsoleLogger implements MyLogger {
	
	private final String levelOne = "INFO";
	private final String levelTwo = "WARNING";
	private final String levelThree = "PLSCHECKFFS";

	@Override
	public void log(int level, String message) {
		
		switch(level){
		case 1 :
			System.out.println(levelOne + "::" + getTimestamp() + "::" + message);			
			break;
		case 2 :
			System.out.println(levelTwo + "::" + getTimestamp() + "::" + message);
			break;
		case 3 :
			System.out.println(levelThree + "::" + getTimestamp() + "::" + message);
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
