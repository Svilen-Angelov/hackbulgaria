/**
 * 
 */
package problem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Svilen
 *
 */
public class HackCoin {

    final static String BUY = "buy";

    final static String SELL = "sell";

    final static String HOLD = "hold";
    
    /**
     * This class performs HackCoin transactions to maximize profit given a list of future price positions. 
     * @param args
     */
    public static void main(String[] args) {
        

        Scanner input = new Scanner(System.in);
        double amountOfMoney = 0;
        double amountOfHackCoin = 0;
        ArrayList<Double> hackCoinPrices = new ArrayList<Double>();
        int numberOfTransactions = 0;

        System.out.println("Please enter the starting amount of money");
        amountOfMoney = input.nextInt();

        do {
            System.out.println("Please keep entering the future HackCoin prices (press 'x' when finished)");
            if (input.hasNext("x")) {
                break;
            } else {
                hackCoinPrices.add(input.nextDouble());
                numberOfTransactions++;
            }
        } while (true);

        String[] dailyPositions = new String[numberOfTransactions];

        for (int i = 0; i < numberOfTransactions; i++) {

            if (i == 0) { // First day
                
                if (getAction(hackCoinPrices.get(i), hackCoinPrices.get(i + 1)) == BUY) {
                    amountOfHackCoin = amountOfMoney / hackCoinPrices.get(i);
                    amountOfMoney = 0;
                    dailyPositions[i] = BUY;
                } else {
                    dailyPositions[i] = HOLD;
                }
                
            } else if (i == numberOfTransactions - 1) { // Last day

                if (amountOfHackCoin > 0) {
                    amountOfMoney = amountOfHackCoin * hackCoinPrices.get(numberOfTransactions - 1);
                    amountOfHackCoin = 0;
                    dailyPositions[numberOfTransactions - 1] = SELL;
                } else {
                    dailyPositions[numberOfTransactions - 1] = HOLD;
                }

            } else { // Any other day

                if (amountOfMoney > 0) {

                    if (getAction(hackCoinPrices.get(i), hackCoinPrices.get(i + 1)) == BUY) {
                        amountOfHackCoin = amountOfMoney / hackCoinPrices.get(i);
                        amountOfMoney = 0;
                        dailyPositions[i] = BUY;
                    } else {
                        dailyPositions[i] = HOLD;
                    }

                } else {

                    if (getAction(hackCoinPrices.get(i), hackCoinPrices.get(i + 1)) == SELL) {
                        amountOfMoney = amountOfHackCoin * hackCoinPrices.get(i);
                        amountOfHackCoin = 0;
                        dailyPositions[i] = SELL;
                    } else {
                        dailyPositions[i] = HOLD;
                    }

                }
            }
        }

        // Display the amount of money made after all the insider trading.
        System.out.println(String.format("%.2f", amountOfMoney));
        System.out.println(Arrays.toString(dailyPositions));
        input.close();
    }

    private static String getAction(double todaysPrice, double tomorrowsPrice) {
        
        if (todaysPrice < tomorrowsPrice) {
            return BUY;
        } else if (todaysPrice > tomorrowsPrice) {
            return SELL;
        } else {
            return HOLD;
        }
    }
}