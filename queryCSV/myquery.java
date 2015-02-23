package queryCSV;

import java.util.ArrayList;
import java.util.Scanner;

public class myquery {

	public static void main(String[] args) {
 
                //Works fine but I need to make the formatting more pretty.

		Scanner sc = new Scanner(System.in);
		Queries myquery = new Queries(
				"C:\\workspace\\HackBulgaria\\src\\queryCSV\\csvDB.csv");

		String line = "";
		while (!line.equals("exit")) {

			System.out.println("Enter a query(Enter \"exit\" to end): ");
			line = sc.nextLine();
			String input[] = line.split(" |,"); // Splits with space or
							    // commas(because of SELECT
							    // id,name example)

			// SELECT
			if (input[0].equals("SELECT")) {

				ArrayList<String> params = new ArrayList<String>();
				int limit = -1;

				for (int i = 1; i < input.length; i++) {
					if (!input[i].equals("LIMIT")) {
						params.add(input[i]);
					} else {
						if (i + 1 < input.length)
							if (Queries.isInt(input[i + 1]))
								limit = Integer.parseInt(input[i + 1]);
							else
								System.out.println("Invalid limit.");
						else
							System.out.println("You forgot to enter a LIMIT");
					}
				}

				myquery.selectQuerie(params, limit);// If the method gets sent a
								    // -1 limit it works like
								    // without limit.

				// SUM
			} else if (input[0].equals("SUM")) {

				if (input.length == 2) // Prevents NullPointer with bad input.
					myquery.sumQuerie(input[1]);
				else
					System.out.println("Invalid sum input.");

				// SHOW
			} else if (input[0].equals("SHOW")) {

				myquery.showQuerie();

				// FIND
			} else if (input[0].equals("FIND")) {
				if (input.length == 2)
					myquery.findQuerie(input[1]);
				else
					System.out.println("Invalid search input.");

				// INVALID
			} else {
				System.out.println("Invalid query. The available queries are SELECT,FIND,SUM,SHOW.");
			}
		}

		sc.close();

	}

}
