/**
 * 
 */
package problem1;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class calculates the balance from the input file until the given date.
 * @author Svilen
 *
 */
public class BalanceCalc {
	public static void main(String[] args) {
		
		final String FILE_PATH = "D:\\JavaProjects\\HackBulgariaPython\\src\\sampleInputs3.txt";
		final String TIME_FORMAT = "dd/MM/yyyy";
		final String DELIMITER = "----";

		int balance = 0;
		Date requestedDate = null;
		ArrayList<String> lineEntries = new ArrayList<String>();
		DateFormat format = new SimpleDateFormat(TIME_FORMAT);
		BufferedReader br = null;
		
		try {
			// Get the requested date (the last entry after the delimiter)		
			br = new BufferedReader(new FileReader(FILE_PATH));
			String line = br.readLine();
			
			while (line.compareTo(DELIMITER) != 0) {
				lineEntries.add(line);
				line = br.readLine();
			}
			
			line = br.readLine();
			requestedDate = format.parse(line);
			
			// Add the correct entries to the balance
			Date entryDate = null;
			String[] splitEntry = null;
			
			for (String entry : lineEntries) {
				
				splitEntry = entry.split(",");
				
				entryDate = format.parse(splitEntry[1]);
				
				if(entryDate.compareTo(requestedDate) <= 0) {
					balance += Integer.parseInt(splitEntry[0]);
				}
			}
			
		}
		catch (ParseException pare) 
		{
		   pare.printStackTrace();
		} 
		catch (IOException ioe) 
		{
		   ioe.printStackTrace();
		} 
		finally {
			   try {
				      if (br != null)
					 br.close();
				   } 
				   catch (IOException ioe) 
			       {
					System.out.println("Error in closing the BufferedReader");
				   }
			   
			   System.out.println("The balance before the date : " + requestedDate.toString() + " is " + balance);
	   }
	}		
}

