/**
 * 
 */
package problem3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class calculates the number of clicks needed to detonate all connected bombs in a field.
 * @author Svilen
 *
 */
public class BombField {

    final static char RED = 'R';

    final static char GREEN = 'G';

    final static char BLUE = 'B';
    
    final static char DETONATED = 'D';

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<char[]> initialMatrix = new ArrayList<char[]>();
        int numberOfDetonations = 0;

        do {
            System.out.println("Please keep entering the rows of the bomb field matrix (press 'x' when finished)");
            if (input.hasNext("x")) {
                break;
            } else {
                initialMatrix.add(input.next().toCharArray());
            }
        } while (true);

        //Transfer the initial inputs into a two dimentional character array.
        char[][] bombField = new char[initialMatrix.size()][];

        for (int i = 0; i < initialMatrix.size(); i++) {
            bombField[i] = initialMatrix.get(i);
        }
        
        for(int i = 0; i< bombField.length; i++) {
        	
        	for(int j = 0; j < bombField[i].length; j++) {
        		
        		if(bombField[i][j] == DETONATED) {
        			continue;
        		}else {
        			chainDetonation(bombField, i, j, bombField[i][j]);
        			numberOfDetonations++;
        		}
        		
        	}
        }
        
        System.out.println("The number of detonations needed to clear the field was : " + numberOfDetonations);
        input.close();
    }
    
    /**
     * This method detonates all connected bombs of the given color searching to right, down and the diagonal.
     *
     * @param bombfield The two dimentional array that we will be working on
     * @param i Column position
     * @param j Row position
     * @param color The color we are detonating
     */
    private static void chainDetonation(char[][] bombfield, int i, int j, char color) {
    	
    	if (i < 0) {
    		return;
    	}
    	
    	if (j < 0) {
    		return;
    	}
    	
    	if (i == bombfield.length) {
    		return;
    	}
    	
    	if (j == bombfield[i].length) {
    		return;
    	}
    	
    	if (bombfield[i][j] != color) {
    		return;
    	}
    	
    	bombfield[i][j] = DETONATED;
    	
    	//Send it in all directions
    	chainDetonation(bombfield, i, j + 1, color);
    	
    	chainDetonation(bombfield, i + 1, j + 1, color);
    	
    	chainDetonation(bombfield, i + 1, j, color);
    	
    	chainDetonation(bombfield, i + 1, j - 1, color);
    	
    	chainDetonation(bombfield, i, j - 1, color);
    	
    	chainDetonation(bombfield, i - 1, j - 1, color);
    	
    	chainDetonation(bombfield, i - 1, j, color);
    	
    	chainDetonation(bombfield, i - 1, j + 1, color);
    		
    }

}
