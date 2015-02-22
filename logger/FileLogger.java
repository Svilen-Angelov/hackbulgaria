package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FileLogger implements MyLogger {
	
	private final String levelOne = "INFO";
	private final String levelTwo = "WARNING";
	private final String levelThree = "PLSCHECKFFS";
	private File FileLog;
	private FileWriter fw;
	private BufferedWriter bw;
		
	public FileLogger(){
		
		FileLog = new File("C:\\workspace\\HackBulgaria\\src\\logger\\FileLog.txt");
		
		try {
			FileLog.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File already exists.");
		}
		
		try {
			fw = new FileWriter(FileLog);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void log(int level, String message) {
					
			switch(level){
			case 1 :
				try {
					bw.write(levelOne + "::" + getTimestamp() + "::" + message);
					bw.newLine();
					System.out.println("Log successful.");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2 :
				try {
					bw.write(levelTwo + "::" + getTimestamp() + "::" + message);
					bw.newLine();
					System.out.println("Log successful.");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3 :
				try {
					bw.write(levelThree + "::" + getTimestamp() + "::" + message);
					bw.newLine();
					System.out.println("Log successful.");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default :
				System.out.println("Log unsuccessful : " + level + " is an invalid log level.");
						
			}
			
	}
	
	private String getTimestamp(){
		
		TimeZone timezone = TimeZone.getTimeZone("UTC");
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
		dateformat.setTimeZone(timezone);
		String currentTimestamp = dateformat.format(new Date());
		
		return currentTimestamp;
	}
	
	public void closer(){
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
