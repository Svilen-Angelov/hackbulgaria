package queryCSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Queries {

	private BufferedReader br;
	private String fileLocation;

	public Queries(String fileLocation) {

		this.fileLocation = fileLocation;

	}
	
	
	public void selectQuerie(ArrayList<String> params, int level){
		
		try {
			br = new BufferedReader(new FileReader(fileLocation));
			String cvsSplitby = ",";
			String firstLine = br.readLine();
			String line = "";
			
			String columns[] = firstLine.split(cvsSplitby);
			
			for(int i = 0; i < columns.length; i++){
				if(!params.contains(columns[i]))
					columns[i] = "SKIP";
				else
					System.out.print("|\t" + columns[i] + "\t|");
			}
			System.out.println();
			System.out.println();

			while(true){
				line = br.readLine();
				if(line == null || level == 0)
					break;
				String lineparts[] = line.split(cvsSplitby);
				
				for(int i = 0; i < lineparts.length; i++)
					if(!columns[i].equals("SKIP"))
						System.out.print("|\t" + lineparts[i] + "\t|");
				
				System.out.println();
				level--;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void sumQuerie(String column) {

		try {
			br = new BufferedReader(new FileReader(fileLocation));
			int sum = 0;
			String cvsSplitBy = ",";
			String line = br.readLine();

			String[] columns = line.split(cvsSplitBy);

			for (int i = 0; i < columns.length; i++)
				if (columns[i].equals(column)) // Finds the right column
					while (true) {

						line = br.readLine();
						if (line == null)
							break;

						String[] cols = line.split(cvsSplitBy);
						try {
							if(Queries.isInt(cols[i]))
								sum += Integer.parseInt(cols[i]); // Adds the value of that column to sum
						} catch (NumberFormatException e) { // If its not a number catches the exception 
							e.printStackTrace();
						}
					}

			System.out.println("The sum is : " + sum);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void showQuerie(){
		
		try {
			br = new BufferedReader(new FileReader(fileLocation));
			String line = br.readLine();
			System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void findQuerie(String x){
		
		try {
			br = new BufferedReader(new FileReader(fileLocation));
			String line = br.readLine();
			String cvsSplitBy = ",";
			String[] firstLine = line.split(cvsSplitBy);
			
			for(String column : firstLine)
				System.out.print("|\t" + column + "\t|");
			
			System.out.println();
			System.out.println();
				
			
			while(true){
				line = br.readLine();
				if(line == null)
					break;
				
				String[] columns = line.split(cvsSplitBy);
				
				for(int i =0; i < columns.length; i++){
					if(columns[i].contains(x)){
						for(String column : columns)
							System.out.print("|\t" + column + "\t|");
						System.out.println();
						break;
					}		
				}				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static boolean isInt(String isInt){
		return isInt.matches("-?\\d+");
	}

}
