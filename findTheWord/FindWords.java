package findTheWord;

import java.util.Random;
import java.util.Scanner;

public class FindWords {

	private static int countsOfWord = 0;
	private static int size;
	private static char[] wordArray;
	private static char[][] wordTable;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("What word should we be looking for ?");
		String word = sc.nextLine();
		wordArray = word.toCharArray();

		System.out.println("Enter the size of your desired wordtable:");
		size = sc.nextInt();

		wordTable = new char[size][size];
		
        // Fills the table with random letters from the alphabet. 
		// If you want to test with a small sample just for abc change r.nextInt(26) to nextInt(3)
		for (int i = 0; i < size; i++) {
			for (int y = 0; y < size; y++) {

				Random r = new Random();
				wordTable[i][y] = (char) (r.nextInt(26) + 'a');
				System.out.print(wordTable[i][y] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < size; i++) {
			for (int y = 0; y < size; y++) {

				if (wordTable[i][y] == wordArray[0]) {
					countsOfWord += checkHorizontal(i, y);
					countsOfWord += checkVertical(i, y);
					countsOfWord += checkAcross(i, y);
				}
			}
		}
		
		System.out.println("The word " + word + " was found " + countsOfWord + " times.");

		sc.close();
	}
	
    // The methods start with the maximum possible word matches(e.g. to the left and to the right)
	//And if a check fails the number is decreased. They return the actual word matches as an int.
	private static int checkHorizontal(int i, int y) {

		int currentLetter = 1;
		int checkRight = y + 1;
		int checkLeft = y - 1;
		int wordMatchTimes = 2;
        
		//Check to the right
		while (currentLetter < wordArray.length) {
			
			if(checkRight < size){
				if (wordTable[i][checkRight] == wordArray[currentLetter]) {
					checkRight++;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}
		
		currentLetter = 1;
		//Check to the left
		while (currentLetter < wordArray.length) {
			
			if(checkLeft >= 0){
				if (wordTable[i][checkLeft] == wordArray[currentLetter]) {
					checkLeft--;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}

		return wordMatchTimes;
	}

	private static int checkVertical(int i, int y) {
		
		int currentLetter = 1;
		int checkUp = i - 1;
		int checkDown = i + 1;
		int wordMatchTimes = 2;
		
		//Check downwards
		while (currentLetter < wordArray.length) {
			
			if(checkDown < size){
				if (wordTable[checkDown][y] == wordArray[currentLetter]) {
					checkDown++;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}
		
		currentLetter = 1;
        // Check upwards
		while (currentLetter < wordArray.length) {
			
			if(checkUp >= 0){
				if (wordTable[checkUp][y] == wordArray[currentLetter]) {
					checkUp--;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}
		
		return wordMatchTimes;
	}
    // Here you can find maximum of 4 matches (one in each diagonal)
	private static int checkAcross(int i, int y) {
		
		int currentLetter = 1;
		int checkUp = i - 1;
		int checkDown = i + 1;
		int checkRight = y + 1;
		int checkLeft = y - 1;
		int wordMatchTimes = 4;
		
		// Check diagonally to the up and left
		while(currentLetter < wordArray.length){
			
			if(checkUp >= 0 && checkLeft >= 0){
				if (wordTable[checkUp][checkLeft] == wordArray[currentLetter]) {
					checkUp--;
					checkLeft--;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}
		
		// Reset
		checkUp = i-1;
		checkLeft = y-1;
		currentLetter = 1;
		
		//Check diagonally to the up and right
		while(currentLetter < wordArray.length){
			
			if(checkUp >= 0 && checkRight < size){
				if (wordTable[checkUp][checkRight] == wordArray[currentLetter]) {
					checkUp--;
					checkRight++;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}
		
		// Reset
		checkUp = i-1;
		checkRight = y+1;
		currentLetter = 1;
		
		//Check diagonally down and left
		while(currentLetter < wordArray.length){
			
			if(checkDown < size && checkLeft >= 0){
				if (wordTable[checkDown][checkLeft] == wordArray[currentLetter]) {
					checkDown++;
					checkLeft--;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}
		
		// Reset
		checkDown = i+1;
		checkLeft = y-1;
		currentLetter = 1;
		
		//Check diagonally down and right
		while(currentLetter < wordArray.length){
			
			if(checkDown < size && checkRight < size){
				if (wordTable[checkDown][checkRight] == wordArray[currentLetter]) {
					checkDown++;
					checkRight++;
					currentLetter++;
					continue;
				}
				wordMatchTimes--;
				break;
			}
			wordMatchTimes--;
			break;
		}
		
		return wordMatchTimes;	
	}
}
