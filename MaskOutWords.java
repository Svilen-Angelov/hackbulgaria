package problem1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskOutWords {

	public static void main(String[] args) {
		
		List<String> words = new ArrayList<String>();
		
		System.out.println("Enter the words you want to be masked out(Enter \"1\" to end):");
		String line = "";
		Scanner sc = new Scanner(System.in);
		
		while(true){
			
			line = sc.nextLine();
			if(line.equals("1")){
				break;
			}
			words.add(line);
		}
		
		System.out.println(words);

		System.out.println("Enter your text(Enter \"1\" to end):");
		String text = "";
		
		while(true){
			
			line = sc.nextLine();
			if(line.equals("1")){
				break;
			}
			text += line;
			text += "\n";
		}
		
		System.out.println(text);
		
		System.out.println(maskOutWords(words,text));
		
		
        sc.close();
	}
	
	public static String maskOutWords(List<String> words, String text){
		
		for(int i = 0; i < words.size(); i++){
			
			//Generates a new word made out of stars with the same length.
			int numOfLetters = words.get(i).length(); 
			String stars = "";
			while(numOfLetters > 0){
				stars += "*";
				numOfLetters--;
			}
			
			//Uses regular expressions to replace all the words with stars.
			Pattern pattern = Pattern.compile("\\b"+ words.get(i)+ "\\b",Pattern.CASE_INSENSITIVE);
			
			Matcher matcher = pattern.matcher(text);
			
			text = matcher.replaceAll(stars);
			
		}
		
		return text;
	}

}
